package com.me.projects.game.gui;
import kotlin.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI {

    private final PointPanel pointPanel;

    public GUI() {
        JFrame frame = new JFrame("Evolution (The zero-player game)");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        pointPanel = new PointPanel();
        pointPanel.setBackground(Color.decode(BACKGROUND_COLOR));

        frame.add(pointPanel);
        frame.setVisible(true);
    }

    public void draw(ArrayList<int[]> pointsList) {
        pointPanel.setPointsList(pointsList);
    }

    static class PointPanel extends JPanel {
        private ArrayList<int[]> pointsList;

        public void setPointsList(ArrayList<int[]> pointsList) {
            this.pointsList = pointsList;
            this.repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GREEN);
            if (pointsList != null) {
                for (int[] point : pointsList) {
                    int x = point[0];
                    int y = point[1];
                    g.fillOval(x - POINT_SIZE / 2, y - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
                }
            }
        }
    }

    final static int POINT_SIZE = 5;
    final static int FRAME_HEIGHT = 700;
    final static int FRAME_WIDTH = 1000;
    final private static String BACKGROUND_COLOR = "#333333";
}
