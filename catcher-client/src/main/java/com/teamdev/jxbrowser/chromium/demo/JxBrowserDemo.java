package com.teamdev.jxbrowser.chromium.demo;

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
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.swing.DefaultNetworkDelegate;
import org.apache.commons.lang3.StringUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by df on 16/5/16.
 */
public class JxBrowserDemo {
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
    private JButton successButton;
    private JPanel broswerPanel;
    private JTextArea infoArea;

    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("百度VCRM工具");
        final JxBrowserDemo form = new JxBrowserDemo();
        frame.setContentPane(form.mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        final Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        form.broswerPanel.setLayout(new BoxLayout(form.broswerPanel, BoxLayout.PAGE_AXIS));
        form.broswerPanel.add(browserView);
        browser.loadURL("http://vcrm.baidu.com");

        String info = "使用说明：\n1.使用左侧浏览器登陆，登陆成功后点击\"成功登陆\";\n2.进入账户列表-->查询-->详情；\n3.检查下列四个参数是否正确；\n4.开始拉取数据\n\n" +
                "注意：初始版本，很多非法操作还未能做正确处理，请一定按照上述步骤进行操作；BTW，关于漏数据的问题，下一版本即将修复，敬请期待……";
        form.infoArea.setText(info);

        form.successButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                CookieStorage cookieStorage = browser.getCookieStorage();
                List<Cookie> cookies = cookieStorage.getAllCookies();
                int cookieHit = 0;
                for (Cookie cookie : cookies) {
                    System.out.println("cookie-->" + cookie);
                    if ("__cas__id__309".equals(cookie.getName())) {
                        form.casIdEdit.setText(cookie.getValue());
                        cookieHit++;
                    }
                    if ("__cas__st__309".equals(cookie.getName())) {
                        form.casStEdit.setText(cookie.getValue());
                        cookieHit++;
                    }
                    if (cookieHit == 2) {
                        break;
                    }
                }

                BrowserContext context = browser.getContext();
                NetworkService networkService = context.getNetworkService();
                networkService.setNetworkDelegate(new DefaultNetworkDelegate() {
                    @Override
                    public void onBeforeURLRequest(BeforeURLRequestParams params) {
                        if (StringUtils.isEmpty(form.operatorIdEdit.getText()) || StringUtils.isEmpty(form.tokenEdit.getText())) {
                            if ("POST".equalsIgnoreCase(params.getMethod())) {
                                PostData postData = params.getPostData();
                                PostDataContentType contentType = postData.getContentType();
                                if (contentType.equals(PostDataContentType.FORM_URL_ENCODED)) {
                                    FormData formData = (FormData) postData;
                                    Set<String> pairKeys = formData.getPairKeys();
                                    if (pairKeys != null) {
                                        for (String pairKey : pairKeys) {
                                            if (StringUtils.isEmpty(pairKey)) {
                                                continue;
                                            }
                                            System.out.println("pairKey = " + pairKey);
                                            if ("operatorId".equals(pairKey) && StringUtils.isEmpty(form.operatorIdEdit.getText())) {
                                                List<String> pairValues = formData.getPairValues(pairKey);
                                                if (pairValues != null && pairValues.size() > 0) {
                                                    form.operatorIdEdit.setText(pairValues.get(0));
                                                }
                                            }
                                            if ("token".equals(pairKey) && StringUtils.isEmpty(form.tokenEdit.getText())) {
                                                List<String> pairValues = formData.getPairValues(pairKey);
                                                if (pairValues != null && pairValues.size() > 0) {
                                                    form.tokenEdit.setText(pairValues.get(0));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            super.onBeforeURLRequest(params);
                        }
                    }
                });
            }
        });

        form.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String casId = form.casIdEdit.getText();
                String casSt = form.casStEdit.getText();
                String token = form.tokenEdit.getText();
                String operatorId = form.operatorIdEdit.getText();

                JFileChooser fileChooser = new JFileChooser();
                XlsFileFilter fileFilter = new XlsFileFilter();
                fileChooser.addChoosableFileFilter(fileFilter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.showSaveDialog(form.mainPanel);
                File file = fileChooser.getSelectedFile();
                if (file == null) {
                    return;
                }
                String ext = fileFilter.getExtension();
                if (!file.getAbsolutePath().toUpperCase().endsWith(ext.toUpperCase())) {
                    file = new File(file.getAbsolutePath() + "." + ext);
                }

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
                    FileOutputStream out;
                    try {
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(1, 2, new Insets(5, 10, 5, 10), -1, -1));
        mainPanel.setMaximumSize(new Dimension(1024, 768));
        mainPanel.setMinimumSize(new Dimension(300, 200));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("cas_id");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("cas_st");
        panel2.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("token");
        panel2.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        casIdEdit = new JTextField();
        panel2.add(casIdEdit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        casStEdit = new JTextField();
        panel2.add(casStEdit, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tokenEdit = new JTextField();
        panel2.add(tokenEdit, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        startButton = new JButton();
        startButton.setText("开始拉取数据");
        panel2.add(startButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("operatorId");
        panel2.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        operatorIdEdit = new JTextField();
        panel2.add(operatorIdEdit, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setEnabled(true);
        infoArea.setLineWrap(true);
        infoArea.setText("");
        panel3.add(infoArea, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        successButton = new JButton();
        successButton.setText("成功登陆");
        panel3.add(successButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        broswerPanel = new JPanel();
        broswerPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(broswerPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(1000, 650), null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    static class XlsFileFilter extends FileFilter {//添加文件过滤，保存文件格式为.txt格式的文件
        private String extension = "xls";

        public boolean accept(File f) {//这个的意思是说，如果是目录或者为.txt文件格式的文件就显示出来
            String extension = getExtension(f);
            return extension != null && extension.equalsIgnoreCase(this.extension);
        }

        public String getDescription() {
            return "*.xls";
        }

        public String getExtension() {
            return this.extension;
        }

        public String getExtension(File f) {
            if (f != null) {
                String filename = f.getName();
                int i = filename.lastIndexOf('.');
                if (i > 0 && i < filename.length() - 1) {
                    return filename.substring(i + 1).toLowerCase();
                }
            }
            return null;
        }
    }
}
