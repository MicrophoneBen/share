package com.suntek.log4j2.test;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @author： wuyuhao
 * @version： 2019/03/ 09
 * @since： 2019/03/09
 */
public class ImageViewer {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ImageViewerFrame frame = new ImageViewerFrame();
            frame.setTitle("吴宇浩的图片查看器");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * imageViewerFrame
 */
class ImageViewerFrame extends JFrame {
    private JLabel label;
    private JFileChooser chooser;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    public ImageViewerFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        label = new JLabel();
        add(label);

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/Users/wuyuhao/Pictures"));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("文件");
        JMenu helpMenu = new JMenu("帮助");
        JMenu loveMenu = new JMenu("姜佳敏爱吴宇浩吗");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(loveMenu);


        JMenuItem openItem = new JMenuItem("打开");
        fileMenu.add(openItem);
        openItem.addActionListener(event -> {
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                label.setIcon(new ImageIcon(name));
            }
        });
        JMenuItem loveItem = new JMenuItem("爱");
        JMenuItem noLoveItem = new JMenuItem("不爱");
        JMenuItem exitItem = new JMenuItem("退出");
        fileMenu.add(exitItem);
        loveMenu.add(loveItem);
        loveMenu.add(noLoveItem);
        noLoveItem.addActionListener(event -> {
            label.setText("不对,重新选");
        });
        loveItem.addActionListener(event ->{
            label.setText("对了,真聪明");
        });

        exitItem.addActionListener(event -> System.exit(0));
    }
}