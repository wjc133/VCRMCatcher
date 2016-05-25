package com.elite.tools.catcher.client.ui;

import com.elite.tools.catcher.client.domain.ContentVo;
import com.elite.tools.catcher.client.domain.PhoneDataVo;
import com.elite.tools.catcher.client.mapper.PhoneDataMapper;
import com.elite.tools.catcher.core.catcher.InfoGetter;
import com.elite.tools.catcher.core.domain.Content;
import com.elite.tools.catcher.core.domain.PhoneData;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by df on 16/5/16.
 */
public class MainForm {
    private static InfoGetter infoGetter;
    private JPanel mainPanel;
    private JTextField casIdEdit;
    private JTextField casStEdit;
    private JTextField tokenEdit;
    private JLabel casIdLabel;
    private JLabel casStLabel;
    private JLabel tokenLabel;
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
                    List<ContentVo> contentVos = Lists.newArrayList();
                    for (Content content : contents) {
                        ContentVo contentVo = new ContentVo();
                        contentVo.setAcctId(content.getAcctId());
                        contentVo.setCompanyName(content.getCompanyName());
                        contentVo.setRechargeDate(content.getRechargeDate());
                        contentVo.setSiteUrl(content.getSiteUrl());

                        List<PhoneData> phoneDatas = infoGetter.getDetile(content.getAcctId());
                        List<PhoneDataVo> phoneDataVos = PhoneDataMapper.INSTANCE.bosToVos(phoneDatas);
                        contentVo.setPhoneDataVos(phoneDataVos);
                        System.out.println(contentVo);
                        contentVos.add(contentVo);
                    }
                    System.out.println(toJson(contentVos));
                } else {
                    System.out.println("no data!");
                }
            }
        });
    }

    private static String toJson(List<ContentVo> contentVos) {
        Gson gson = new Gson();
        return gson.toJson(contentVos);
    }
}
