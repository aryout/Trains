package com.faceyee.entity;

/**
 * Created by 97390 on 8/13/2018.
 */
import java.util.ArrayList;

public class City {

    private boolean wasVisited; // 是否遍历过
    public char name;
    private ArrayList<Integer> allVisitedList;// 每个节点维护自己 已访问过的顶点，元素值为1，0表示初始化，未被访问

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

    public City(char name)
    {
        this.name = name;
        wasVisited = false;
    }

    public void setVisited(int j) {
        allVisitedList.set(j, 1); // 已访问列表的已访问城市标记为1
    }
}