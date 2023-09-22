package com.me.projects.game.gui;
import kotlin.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI {

    private JFrame frame;
    private PointPanel pointPanel;

    public GUI() {
        frame = new JFrame("Evolution (The zero-player game)");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        pointPanel = new PointPanel();
        pointPanel.setBackground(Color.decode(BACKGROUND_COLOR));

        frame.add(pointPanel);
        frame.setVisible(true);
    }

    public void draw(ArrayList<Pair<Integer, Integer>> pointsList) {
        pointPanel.setPointsList(pointsList);
    }

    class PointPanel extends JPanel {
        private ArrayList<Pair<Integer, Integer>> pointsList;

        public void setPointsList(ArrayList<Pair<Integer, Integer>> pointsList) {
            this.pointsList = pointsList;
            this.repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GREEN);
            if (pointsList != null) {
                for (Pair<Integer, Integer> point : pointsList) {
                    int x = point.getFirst();
                    int y = point.getSecond();
                    g.fillOval(x - POINT_SIZE / 2, y - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
                }
            }
        }
    }

    final private static int POINT_SIZE = 5;
    final private static int FRAME_HEIGHT = 700;
    final private static int FRAME_WIDTH = 1000;
    final private static String BACKGROUND_COLOR = "#333333";
}
