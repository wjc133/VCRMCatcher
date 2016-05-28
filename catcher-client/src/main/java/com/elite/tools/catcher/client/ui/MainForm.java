package com.elite.tools.catcher.client.ui;

import com.elite.tools.catcher.client.domain.ContentVo;
import com.elite.tools.catcher.client.domain.PhoneDataVo;
import com.elite.tools.catcher.client.export.ExcelExportService;
import com.elite.tools.catcher.client.mapper.PhoneDataMapper;
import com.elite.tools.catcher.core.catcher.DataCache;
import com.elite.tools.catcher.core.catcher.InfoGetter;
import com.elite.tools.catcher.core.domain.Content;
import com.elite.tools.catcher.core.domain.PhoneData;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by df on 16/5/16.
 */
public class MainForm {
    private static InfoGetter infoGetter;
    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(500);
    private static List<Future> futures = Lists.newArrayList();
    private static ExcelExportService exportService = new ExcelExportService();
    private JPanel mainPanel;
    private JTextField casIdEdit;
    private JTextField casStEdit;
    private JTextField tokenEdit;
    private JButton startButton;
    private JTextField operatorIdEdit;

    public static void main(String[] args) {
        JFrame frame = new JFrame("百度VCRM工具");
        final MainForm form = new MainForm();
        frame.setContentPane(form.mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        form.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String casId = form.casIdEdit.getText();
                String casSt = form.casStEdit.getText();
                String token = form.tokenEdit.getText();
                String operatorId = form.operatorIdEdit.getText();

                if (infoGetter == null) {
                    infoGetter = new InfoGetter(casId, casSt, token, operatorId);
                }
                List<Content> contents = infoGetter.getIndexInfo();
                if (contents != null && contents.size() > 0) {
                    final List<ContentVo> contentVos = Lists.newArrayList();
                    for (final Content content : contents) {
                        Future future = executor.submit(new Callable<Object>() {
                            public Object call() throws Exception {
                                ContentVo contentVo = new ContentVo();
                                contentVo.setAcctId(content.getAcctId());
                                contentVo.setCompanyName(content.getCompanyName());
                                contentVo.setRechargeDate(content.getRechargeDate());
                                contentVo.setSiteUrl(content.getSiteUrl());
                                contentVo.setAcctName(content.getAcctName());
                                contentVo.setAddressDetail(content.getAddressDetail());

                                List<PhoneData> phoneDatas = infoGetter.getDetile(content.getAcctId());
                                List<PhoneDataVo> phoneDataVos = PhoneDataMapper.INSTANCE.bosToVos(phoneDatas);
                                contentVo.setPhoneDataVos(phoneDataVos);
                                System.out.println(contentVo);
                                contentVos.add(contentVo);
                                return null;
                            }
                        });
                        futures.add(future);
                    }
                    watingForFinish();
                    System.out.println(toJson(contentVos));
                    System.out.println("error accts:" + DataCache.getErrorAccts());
                    FileOutputStream out = null;
                    try {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.addChoosableFileFilter(new FileFilter() {//添加文件过滤，保存文件格式为.txt格式的文件
                            public boolean accept(File f) {//这个的意思是说，如果是目录或者为.txt文件格式的文件就显示出来
                                if (f.isDirectory()) {//如果是目录就可以访问
                                    return true;
                                }
                                return f.getName().endsWith(".xls");
                            }

                            public String getDescription() {
                                return "*.xls";
                            }
                        });
                        fileChooser.showSaveDialog(form.mainPanel);
                        File file = fileChooser.getSelectedFile();
                        out = new FileOutputStream(file);
                        exportService.export(contentVos, out);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    System.out.println("no data!");
                }
            }
        });
    }

    private static void watingForFinish() {
        for (int i = 0; i < futures.size(); i++) {
            Future future = futures.get(i);
            if (future != null) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String toJson(List<ContentVo> contentVos) {
        Gson gson = new Gson();
        return gson.toJson(contentVos);
    }
}
