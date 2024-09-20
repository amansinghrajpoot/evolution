package com.me.projects.game.gui;

import java.util.Arrays;

public class PrintablePredator {

    private int[] coordinates;
    private double gender;

    public PrintablePredator(){

    }

    public PrintablePredator(int[] coordinates, double gender) {
        this.coordinates = coordinates;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "PrintablePredator{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", gender=" + gender +
                '}';
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public double getGender() {
        return gender;
    }

    public void setGender(double gender) {
        this.gender = gender;
    }
}
