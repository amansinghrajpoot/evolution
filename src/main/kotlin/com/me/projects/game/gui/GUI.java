package com.me.projects.game.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.me.projects.game.util.ApplicationConstants.*;

public class GUI {

    private final JPanel mainPanel;

    public GUI() {
        JFrame frame = new JFrame(JFRAME_TITLE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        mainPanel = new JPanel(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(FRAME_WIDTH, BOTTOM_PANEL_HEIGHT));
        bottomPanel.setBackground(Color.decode(BOTTOM_PANEL_BACKGROUND_COLOR));

        JPanel pointPanel = new PointPanel();
        pointPanel.setBackground(Color.decode(BACKGROUND_COLOR));

        JLabel label = new JLabel();
        label.setForeground(Color.decode(FONT_COLOR));
        label.setHorizontalAlignment(JLabel.CENTER);

        bottomPanel.add(label);

        mainPanel.add(pointPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void draw(ArrayList<PrintableCell> printableCellsList, String text) {
        ((JLabel)((JPanel) mainPanel.getComponent(1)).getComponent(0)).setText(text);
        ((PointPanel) mainPanel.getComponent(0)).setPrintableCellsList(printableCellsList);
    }

    static class PointPanel extends JPanel {
        private ArrayList<PrintableCell> printableCellsList;

        public void setPrintableCellsList(ArrayList<PrintableCell> printableCellsList) {
            this.printableCellsList = printableCellsList;
            this.repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (printableCellsList != null) {
                for (PrintableCell printableCell : printableCellsList) {
                    Color color = printableCell.getGender() == 1 ? Color.YELLOW : Color.GREEN;
                    g.setColor(color);
                    int x = printableCell.getCoordinates()[0];
                    int y = printableCell.getCoordinates()[1];
                    g.fillOval(x - POINT_SIZE / 2, y - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
                }
            }
        }
    }
}
