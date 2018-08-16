package com.faceyee.service;

import com.faceyee.entity.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * Determine if there are any pathways between two given cities and find out all direct pathways
 *
 */
public class StraightRoad {

    private boolean isSR = true;
    private Graph graph;
    private int n;
    private int start, end;
    private Stack<Integer> theStack = new Stack<>(); // Temp stack for find road
    private HashSet<String> s2eRoads = new HashSet<String>(); // Temp direct access store between two citis, will update after next call
    private ArrayList<Integer> tempList;

    /**
     * StraightRoad base given Graph
     *@param graph
     */
    public StraightRoad(Graph graph) {
        this.graph = graph;
        this.n = graph.getN();
    }

    /**
     * Return the road set form startCity to endCity
     * @param startCity
     * @param endCity
     * @return
     */
    public HashSet<String> getRoads(char startCity, char endCity) {
        start = startCity - 'A';
        end = endCity - 'A';
        s2eRoads.clear();
        theStack.clear();
        isSR = true;

        if (!isConnectable(start, end)) {
            isSR = false;
        } else {
            for (int j = 0; j < n; j++) { // init each city's allVisitedList
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

    /**
     * Return if this is atleast one road between startCity to endCity, if they're connectable
     * @param start
     * @param end
     * @return
     */
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

    /**
     * Start to find straightRoad when they're connectable
     * @param start  startCity
     * @param end  endCity
     */
    private void straightRoad(int start, int end) { // DFS
        graph.getcityList()[start].setWasVisited(true);
        theStack.push(start);

        while (!theStack.isEmpty()) { // next to its neighbor city
            int v = getUnvisitedCity(theStack.peek());
            if (v == -1) {
                tempList = new ArrayList<Integer>();
                for (int j = 0; j < n; j++) {
                    tempList.add(0);
                }
                graph.getcityList()[theStack.peek()]
                        .setAllVisitedList(tempList);
                theStack.pop();

            } else {
                theStack.push(v);
            }

            if (!theStack.isEmpty() && end == theStack.peek()) { // yet get arrived to endCity, print one road
                graph.getcityList()[end].setWasVisited(false);
                printTheStackRoad(theStack);
                theStack.pop();
            }
        }
    }

    /**
     * Store each straightRoad to s2eRoads
     * @param theStack  traceStack in find straightRoad
     */
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

    /**
     * Return  the next city which neighbor to currentCity(v) and, is unvisited, not place in the traceStack
     * @param v  currentCity(v)
     * @return
     */
    private int getUnvisitedCity(int v) {
        ArrayList<Integer> arrayList = graph.getcityList()[v]
                .getAllVisitedList();

        for (int j = 0; j < n; j++) {
            if (graph.getCityMat()[v][j] != 0 && arrayList.get(j) == 0
                    && !theStack.contains(j)) {

                graph.getcityList()[v].setVisited(j);
                return j; // return the goal city
            }
        }
        return -1;
    }

}