package com.elite.tools.catcher.client.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wjc133
 * DATE: 16/5/29
 * TIME: 下午8:41
 */
public class ProcessBarForm {
    private JProgressBar progressBar;
    private JLabel processLabel;
    private JPanel processPanel;
    private JFrame frame;

    public ProcessBarForm() {
        init();
    }

    public ProcessBarForm(Component parent) {
        init();
        setCenter(parent);
    }

    private void setCenter(Component parent) {
        frame.setLocationRelativeTo(parent);
    }

    private void init() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setContentPane(processPanel);
    }

    public void show() {
        if (frame == null) {
            System.err.println("ProcessBar should inited before show it!");
            return;
        }
        frame.pack();
        frame.setVisible(true);
    }

    public void hide() {
        if (frame == null) {
            System.err.println("ProcessBar should inited before hide it!");
            return;
        }
        frame.setVisible(false);
        frame.dispose();
        frame = null;
    }

    public void setProcess(int process, String info) {
        progressBar.setValue(process);
        processLabel.setText(info);
    }

    public void setProcessInfo(String info) {
        processLabel.setText(info);
    }

    public void setProcess(int process) {
        progressBar.setValue(process);
    }
}
