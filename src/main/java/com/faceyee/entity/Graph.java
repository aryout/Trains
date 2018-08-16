package com.faceyee.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Graph object, and it owns itself cycleCitys set
 *
 */
public class Graph {

    private  City cityList[];
    private  int cityMat[][]; // Distance between two citis
    private  HashSet<String> cycleCitys = new HashSet<String>(); // Graph owns all cycle roads, cuz it's final
    private ArrayList<Integer> trace = new ArrayList<Integer>(); // Temp stack for find cycle road
    private int nCitys;
    private boolean hasCycle;

    /**
     * Graph constructor by given data
     *
     *@param graph graph-structure input string
     *
     */
    public Graph(String graph) {
        ArrayList<City> citis =  new ArrayList<City>();
        if (graph.startsWith("Graph:")){
            String v = graph.substring(6);
            String[] vs = v.split(",");
            for (String v1 : vs) {
                addCity(v1.trim().charAt(0), citis);
                addCity(v1.trim().charAt(1), citis);
            }
            cityList = citis.toArray(new City[citis.size()]);
            cityMat = new int[nCitys][nCitys];

            for (String v1 : vs) {
                int distance = Integer.parseInt(v1.trim().substring(2));
                addEdge(v1.trim().charAt(0), v1.trim().charAt(1), distance);
            }
        }
        findCycle(nCitys-1);

    }

    public City[] getcityList() {
        return cityList;
    }
    public HashSet<String> getCycleCitys() {
        return cycleCitys;
    }
    public int[][] getCityMat() {
        return cityMat;
    }
    public int getN() {
        return nCitys;
    }
    public boolean isHasCycle() {
        return hasCycle;
    }
    public char getCity(int i) {
        return cityList[i].getName();
    }
    private void addEdge(char startCity, char endCity, int distance) {
        int start = (int) startCity - 'A';
        int end = (int) endCity - 'A';
        cityMat[start][end] = distance;
    }
    private void addCity(char lab, List<City> citis) {
        int index = lab - 'A';
        try {
            if (citis.get(index) != null){
                return;
            }
        }catch (IndexOutOfBoundsException e){
            nCitys++;
            citis.add(index, new City(lab));
        }
    }

    /**
     * Find all cycle road tin his Graph, by DFS
     *
     *@param v form this city(v) to start recursion
     *
     */
    private void findCycle(int v)  {
        int j = 0;
        if((j=trace.indexOf(v))!=-1) {
            hasCycle=true;
            StringBuilder sb = new StringBuilder();
            while(j<trace.size())
            {
                sb.append(getCity(trace.get(j)));
                j++;
            }
            String cycleCity = sb.toString();
            cycleCitys.add(cycleCity);
            return;
        }


        trace.add(v);

        for(int i=0;i<nCitys;i++) {
            if( v != i  && cityMat[v][i] != 0 )
              // System.out.println((char) (v+'A')+""+(char)(i+'A'));
                findCycle(i);
        }
        trace.remove(trace.size()-1);
    }
}