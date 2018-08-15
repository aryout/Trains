package com.faceyee.service;

import com.faceyee.entity.Graph;

import java.util.*;

/**
 * Created by 97390 on 8/13/2018.
 */
public class TrainRoad {
    private StraightRoad straightRoad ;
    private Graph graph ;

    private HashSet<String> roads ;
    private HashSet<String> cycleCitys ;

    private int func4res = 0;
    private int fun4weight = 30;
    private HashSet<String> func4roads = new HashSet<>();

    public  TrainRoad (String graphConstruct){
        this.graph = new Graph(graphConstruct);
        this.straightRoad = new StraightRoad(graph);
        this.cycleCitys = graph.getCycleCitys();
    }

    public void  fun(){
        String[] outPut = new String[10];
        outPut[0] = "1.The distance of the route A-B-C.";
        fun1(outPut[0]);
        outPut[1] = "2.The distance of the route A-D.";
        fun1(outPut[1]);
        outPut[2] = "3.The distance of the route A-D-C.";
        fun1(outPut[2]);
        outPut[3] = "4.The distance of the route A-E-B-C-D.";
        fun1(outPut[3]);
        outPut[4] = "5.The distance of the route A-E-D.";
        fun1(outPut[4]);
        outPut[5] = "6.The number of trips starting at C and ending at C with a maximum of 3 stops.";
        fun2(outPut[5]);
        outPut[6] = "7.The number of trips starting at A and ending at C with exactly 4 stops.";
        fun2(outPut[6]);
        outPut[7] = "8.The length of the shortest route (in terms of distance to travel) from A to C.";
        fun3(outPut[7]);
        outPut[8] = "9.The length of the shortest route (in terms of distance to travel) from B to B.";
        fun3(outPut[8]);
        outPut[9] = "10.The number of different routes from C to C with a distance of less than 30.";
        fun4(outPut[9]);
    }



    public int fun2(String line){
        // The number of trips starting at C and ending at C with a maximum of 3 stops
        char startCity = line.charAt(line.indexOf("starting at ")+12);
        char endCity = line.charAt(line.indexOf("ending at ")+10);
        int start = startCity - 'A';
        int end = endCity - 'A';
        if (start != end){
            roads = straightRoad.getRoads(startCity, endCity);
        }
        int res = 0;
        if (line.contains("maximum")){
            int times = Integer.parseInt(line.substring(line.indexOf("maximum of ")+10, line.indexOf("stops")).trim());
            for (int i = 2; i <= times; i++) {
                res += fun2actual(times, start, end);
            }
        }else {
            int times = Integer.parseInt(line.substring(line.indexOf("exactly ")+7, line.indexOf("stops")).trim());
            res = fun2actual(times, start, end);
        }
        int index = Integer.parseInt(line.substring(0,line.indexOf(".The")));
        System.out.printf("Output #%s: "+res, index);
        System.out.println();
        return res;
    }
    private int fun2actual(int times, int start, int end){
        int res = 0;
        if (start == end){ // 直接循环
            String sta = String.valueOf((char) (start+'A'));
            for (String s: cycleCitys
                 ) {
                if (s.contains(sta)){
                    if (s.length() == times){
                        res++;
                    }
                }
            }
        }else {
            for (String s: roads // 每条直达+可能的循环
                    ) {
                if (s.length()-1 == times){// 寻找边数s.length()-1小于times
                    res++;
                }else if (s.length()-1 < times){
                    for (String cs:cycleCitys
                         ) {
                        if ((times - (s.length() - 1) )% cs.length() == 0){
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    public int fun3(String line){
        // The length of the shortest route (in terms of distance to travel) from A to C.
        char startCity = line.charAt(line.indexOf("from ")+5);
        char endCity = line.charAt(line.lastIndexOf("to ")+3);
        int res = Integer.MAX_VALUE;
        int start = startCity - 'A';
        int end = endCity - 'A';
        if (start == end){ // 直接循环
            String sta = String.valueOf((char) (start+'A'));
            for (String s: cycleCitys
                    ) {
                int temp = 0;
                if (s.contains(sta)){
                    s = s + s.charAt(0);
                    for (int i = 0; i < s.length()-1; i++) {
                        temp += graph.getCityMat()[s.charAt(i)-'A'][s.charAt(i+1)-'A'];
                    }
                    if (temp < res){
                        res = temp;
                    }
                }
            }
        }else {
            roads = straightRoad.getRoads(startCity, endCity);
            for (String s: roads
                    ) {
                int temp = 0;
                for (int i = 0; i < s.length()-1; i++) {
                    temp += graph.getCityMat()[s.charAt(i)-'A'][s.charAt(i+1)-'A'];
                }
                if (temp < res){
                    res = temp;
                }
            }
        }
        int index = Integer.parseInt(line.substring(0,line.indexOf(".The")));
        System.out.printf("Output #%s: "+res, index);
        System.out.println();
        return res;
    }

    public String fun1(String line){
        // The distance of the route A-B-C.
        int res = 0;
        boolean isRoad = true;
        String s = line.substring(line.indexOf("route ")+6).trim();
        int index = Integer.parseInt(line.substring(0,line.indexOf(".The")));
        for (int i = 0; i < s.length()-2; i+=2) {
            if (graph.getCityMat()[s.charAt(i)-'A'][s.charAt(i+2)-'A'] > 0){
                res += graph.getCityMat()[s.charAt(i)-'A'][s.charAt(i+2)-'A'];
            }else {
                System.out.printf("Output #%s: NO SUCH ROUTE", index);
                System.out.println();
                isRoad = false;
                break;
            }
        }
        if (!isRoad){
            return "NO SUCH ROUTE";
        }
        System.out.printf("Output #%s: "+res, index);
        System.out.println();
        return String.valueOf(res);
    }

    public int fun4(String line){
        // The number of different routes from C to C with a distance of less than 30.
        char cycleStartCity = line.charAt(line.indexOf("from ")+5);
        char cycleEndCity = line.charAt(line.indexOf("to ")+3);
        int weight = Integer.parseInt(line.substring(line.indexOf("than ")+5,line.length()-2));
        int res = 0;
        List<String> str = new ArrayList<String>();
        if (cycleStartCity == cycleEndCity){ // 直接循环
            String sta = String.valueOf((char) cycleStartCity);
            for (String s: cycleCitys
                    ) {
                int temp = 0;
                if (s.contains(sta)){
                    s = s.substring(s.indexOf(sta)) + s.substring(0, s.indexOf(sta)) + sta;
                    str.add(s);
                }
            }
            //String str[] = {"CDC","CEBC","CDEBC"};
            // CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
            res = preCircu((String[]) str.toArray(new String[str.size()]));
        }
        int index = Integer.parseInt(line.substring(0,line.indexOf(".The")));
        System.out.printf("Output #%s: "+res, index);
        System.out.println();
        return res;
    }

    private int preCircu(String[] s) {
        int n = s.length;
        Map<String, Integer> roadWeight = new HashMap<String, Integer>();
        Stack<Integer> temp = new Stack<Integer>();
        Stack<String> func4ResRoad = new Stack<String>();
        int weight;
        String road;
        for (String value : s) {
            road = value;
            weight = 0;
            for (int j = 0; j < road.length() - 1; j++) {
                weight += graph.getCityMat()[road.charAt(j) - 'A'][road.charAt(j + 1) - 'A'];
            }
            roadWeight.put(road, weight);
        }

        circu(roadWeight, s, temp, func4ResRoad);
        return func4res;
    }

    private void circu(Map roadWeight, String[] s, Stack<Integer> temp, Stack<String> func4ResRoad){
        for (String value : s) {
            if ((int) roadWeight.get(value) < fun4weight) {
                func4res++;
                fun4weight -= (int) roadWeight.get(value);
                func4ResRoad.push(value);
                temp.push((int) roadWeight.get(value));
                circu(roadWeight, s, temp, func4ResRoad);
            }
        }
        if (!temp.empty()){
            fun4weight += temp.pop();
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(String path:func4ResRoad){
            if (first){
                sb.append(path);
                first = false;
            }else {
                sb.append(path.substring(1));
            }
        }
        func4roads.add(sb.toString());
        if (!func4ResRoad.empty()){
            func4ResRoad.pop();
        }
    }

    private void displayFunc4Roads(){
       for (String s:func4roads
             ) {
            System.out.println(s);
        }
    }
}
