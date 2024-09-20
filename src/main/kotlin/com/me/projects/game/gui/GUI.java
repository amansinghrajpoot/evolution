package com.me.projects.game.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    public void drawPredator(ArrayList<PrintablePredator> printablePredatorsList) {
        ((PointPanel) mainPanel.getComponent(0)).setPrintablePredatorsList(printablePredatorsList);
    }

    static class PointPanel extends JPanel {
        private ArrayList<PrintableCell> printableCellsList;
        private ArrayList<PrintablePredator> printablePredatorsList;

        public void setPrintableCellsList(ArrayList<PrintableCell> printableCellsList) {
            this.printableCellsList = printableCellsList;
            this.repaint();
        }

        public void setPrintablePredatorsList(ArrayList<PrintablePredator> printablePredatorsList) {
            this.printablePredatorsList = printablePredatorsList;
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
            if (printablePredatorsList != null) {
                Random random = new Random();  // Create a Random object for generating random angles

                for (PrintablePredator printablePredator : printablePredatorsList) {
                    Color color = printablePredator.getGender() == 1 ? Color.CYAN : Color.RED;
                    g.setColor(color);

                    int x = printablePredator.getCoordinates()[0];
                    int y = printablePredator.getCoordinates()[1];

                    Graphics2D g2d = (Graphics2D) g;

                    // Randomize the startAngle to make Pac-Man face different directions
                    int startAngle = random.nextInt(360);  // Random angle between 0 and 360 degrees
                    int arcAngle = 300;   // Keep the "mouth" open to 300 degrees

                    // Draw the Pac-Man shape with the randomized startAngle
                    g2d.fillArc(x - PREDATOR_POINT_SIZE / 2, y - PREDATOR_POINT_SIZE / 2, PREDATOR_POINT_SIZE, PREDATOR_POINT_SIZE, startAngle, arcAngle);
                }
            }
        }
    }
}
