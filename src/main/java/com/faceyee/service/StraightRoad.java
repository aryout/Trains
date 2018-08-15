package com.faceyee.service;

import com.faceyee.entity.Graph;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by 97390 on 8/13/2018.
 */
public class StraightRoad { // 判断两个给定城市间有没有通路，并找出所有直达通路

    private boolean isSR = true;
    private Graph graph;
    private int n;
    private int start, end;
    private Stack<Integer> theStack = new Stack<>(); // 访问过程栈
    private HashSet<String> s2eRoads = new HashSet<String>(); // 所有直达通路
    private ArrayList<Integer> tempList;

    public StraightRoad(Graph graph) {
        this.graph = graph;
        this.n = graph.getN();
    }

    public HashSet<String> getRoads(char startCity, char endCity) {
        start = startCity - 'A';
        end = endCity - 'A';
        s2eRoads.clear();
        theStack.clear();
        isSR = true;

        if (!isConnectable(start, end)) {
            isSR = false;
        } else {
            for (int j = 0; j < n; j++) { // 初始化每个节点的已访问节点列表
                tempList = new ArrayList<Integer>();
                for (int i = 0; i < n; i++) {
                    tempList.add(0);
                }
                graph.getcityList()[j].setAllVisitedList(tempList);
            }
            straightRoad(start, end);
        }

        if (s2eRoads.size() < 1){
            return null;
        }
        return s2eRoads;
    }
    // 判断两个非相邻节点是否能连通
    private boolean isConnectable(int start, int end) {
        ArrayList<Integer> queue = new ArrayList<Integer>();
        ArrayList<Integer> visited = new ArrayList<Integer>();
        queue.add(start);
        while (!queue.isEmpty()) {
            for (int j = 0; j < n; j++) {
                if (graph.getCityMat()[start][j] != 0 && !visited.contains(j)) {
                    queue.add(j);
                }
            }
            if (queue.contains(end)) {
                return true;
            } else {
                visited.add(queue.get(0));
                queue.remove(0);
                if (!queue.isEmpty()) {
                    start = queue.get(0);
                }
            }
        }
        return false;
    }

    private void straightRoad(int start, int end) { // 深度优先
        graph.getcityList()[start].setWasVisited(true); // 从start城市开始，它已经被访问过
        theStack.push(start); // 加入栈

        while (!theStack.isEmpty()) { // 开始探测下一个城市
            int v = getUnvisitedCity(theStack.peek());
            if (v == -1) { // 无下一个城市
                tempList = new ArrayList<Integer>();
                for (int j = 0; j < n; j++) {
                    tempList.add(0);
                }
                graph.getcityList()[theStack.peek()]
                        .setAllVisitedList(tempList);// 把栈顶节点访问过的节点链表清空，这个节点在这一轮下探中，已经结束使命
                theStack.pop();

            } else {
                theStack.push(v);
            }

            if (!theStack.isEmpty() && end == theStack.peek()) { // 下探到终点城市才答应路径
                graph.getcityList()[end].setWasVisited(false);
                printTheStackRoad(theStack); // 将之加入到s2eRoads中
                theStack.pop();
            }
        }
    }
    private void printTheStackRoad(Stack<Integer> theStack) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : theStack) {
            sb.append(graph.getCity(integer));
        }
        String road = sb.toString();
        if (road.length() > 1){
            s2eRoads.add(road);
        }
    }

    // 与节点v相邻，并且这个节点没有被访问到，并且这个节点不在栈中
    private int getUnvisitedCity(int v) {
        ArrayList<Integer> arrayList = graph.getcityList()[v]
                .getAllVisitedList();

        for (int j = 0; j < n; j++) {
            if (graph.getCityMat()[v][j] != 0 && arrayList.get(j) == 0
                    && !theStack.contains(j)) { //相邻，没访问过，不在栈中

                graph.getcityList()[v].setVisited(j);
                return j; // 返回该城市
            }
        }
        return -1;
    }

}