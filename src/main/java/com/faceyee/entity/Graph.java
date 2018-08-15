package com.faceyee.entity;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by 97390 on 8/13/2018.
 */

public class Graph {

    private  City cityList[];  // 城市
    private  int cityMat[][];
    private  HashSet<String> cycleCitys = new HashSet<String>(); // 保存环
    private ArrayList<Integer> trace = new ArrayList<Integer>(); // 寻找环
    private int nCitys;
    private boolean hasCycle;

    public Graph(String graph) {
        cityList = new City[26]; // 顶点数组
        if (graph.startsWith("Graph:")){
            String v = graph.substring(6);
            String[] vs = v.split(",");
            for (String v1 : vs) {
                addCity(v1.trim().charAt(0));
                addCity(v1.trim().charAt(1));
            }
            cityMat = new int[nCitys][nCitys]; // 邻接矩阵

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
    private void addEdge(char startCity, char endCity, int distance) {// 有向图，添加边
        int start = (int) startCity - 'A';
        int end = (int) endCity - 'A';
        cityMat[start][end] = distance;
    }
    private void addCity(char lab) {
        if (cityList[lab - 'A'] == null){
            nCitys++;
            cityList[lab - 'A'] = new City(lab);// 添加点
        }
    }
    private void findCycle(int v)  {
        int j = 0;
        if((j=trace.indexOf(v))!=-1) { // 还没有元素在里面，index=-1
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


        trace.add(v); // 没访问过，加入

        for(int i=0;i<nCitys;i++) {
            if( v != i  && cityMat[v][i] != 0 ) // 找直达，可以就以它为点递归
              // System.out.println((char) (v+'A')+""+(char)(i+'A'));
                findCycle(i);
        }
        trace.remove(trace.size()-1);
    }
}