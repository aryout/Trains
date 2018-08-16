package com.faceyee.entity;

/**
 * City object, and it owns itself cycleCitys set
 *
 */
import java.util.ArrayList;

public class City {

    private boolean wasVisited;
    public char name;

    /** each city owns itself will-visit citis list */
    private ArrayList<Integer> allVisitedList;// All value will default to 0

    public void setAllVisitedList(ArrayList<Integer> allVisitedList) {
        this.allVisitedList = allVisitedList;
    }

    public ArrayList<Integer> getAllVisitedList() {
        return allVisitedList;
    }

    public boolean WasVisited() {
        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    public char getName() {
        return name;
    }

    /**
     * The city on graph with city-name
     *
     *@param name city-name
     *
     */
    public City(char name)
    {
        this.name = name;
        wasVisited = false;
    }

    /**
     * From this city had visitied over city(j), marked city(j) in allVisitedList
     *
     *@param j city(j)
     *
     */
    public void setVisited(int j) {
        allVisitedList.set(j, 1);
    }
}