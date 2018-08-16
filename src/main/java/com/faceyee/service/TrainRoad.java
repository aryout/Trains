package com.faceyee.service;

import com.faceyee.entity.Graph;
import com.faceyee.utils.RexText;

import java.util.*;

/**
 * Base graph object, help the railroad provide its customers with information about the routes.
 * particular some Typical route
 */
public class TrainRoad {
    private StraightRoad straightRoad ;
    private Graph graph ;

    private HashSet<String> roads ;
    private HashSet<String> cycleCitys ;

    /* nDRWDres, nDRWDweight, nDRWDroads are for numberOfDifferentRoadsWithDistance function */
    private int nDRWDres = 0; // result of numberOfDifferentRoadsWithDistance function
    private int nDRWDweight = 30; // parameter of numberOfDifferentRoadsWithDistance function
    private HashSet<String> nDRWDroads = new HashSet<>(); // store the road of numberOfDifferentRoadsWithDistance function, provide to print

    /**
     * Solution base graph, both are single case
     *@param graphConstruct  graph-structure input string
     */
    public  TrainRoad (String graphConstruct){
        this.graph = new Graph(graphConstruct);
        this.straightRoad = new StraightRoad(graph);
        this.cycleCitys = graph.getCycleCitys();
    }

    /**
     * Provide a public callable function that handling 10 typical routes on homework.
     * But, you can add or alter the City in given route in function.
     * Or, call specific function directly.
     */
    public void  solution(){
        String[] outPut = new String[10];
        outPut[0] = "1.The distance of the route A-B-C.";
        distanceOfSomeCity(outPut[0]);
        outPut[1] = "2.The distance of the route A-D.";
        distanceOfSomeCity(outPut[1]);
        outPut[2] = "3.The distance of the route A-D-C.";
        distanceOfSomeCity(outPut[2]);
        outPut[3] = "4.The distance of the route A-E-B-C-D.";
        distanceOfSomeCity(outPut[3]);
        outPut[4] = "5.The distance of the route A-E-D.";
        distanceOfSomeCity(outPut[4]);

        outPut[5] = "6.The number of trips starting at C and ending at C with a maximum of 3 stops.";
        numberOfDifferentRoadsWithStops(outPut[5]);
        outPut[6] = "7.The number of trips starting at A and ending at C with exactly 4 stops.";
        numberOfDifferentRoadsWithStops(outPut[6]);

        outPut[7] = "8.The length of the shortest route (in terms of distance to travel) from A to C.";
        lengthOfShortestRoute(outPut[7]);
        outPut[8] = "9.The length of the shortest route (in terms of distance to travel) from B to B.";
        lengthOfShortestRoute(outPut[8]);

        outPut[9] = "10.The number of different routes from C to C with a distance of less than 30.";
        numberOfDifferentRoadsWithDistance(outPut[9]);
    }


    /**
     * Return the number of trips base given startCity and endCity even with stops-number(maximum or exactly) in the trip
     *@param line  demand about the routes.like "5.The number of trips starting at C and ending at C with a maximum of 3 stops.".
     */
    public int numberOfDifferentRoadsWithStops (String line){
        char startCity = RexText.returnChar(line)[0];
        char endCity = RexText.returnChar(line)[1];
        int start = startCity - 'A';
        int end = endCity - 'A';
        if (start != end){
            roads = straightRoad.getRoads(startCity, endCity);
        }
        int res = 0;
        if (line.contains("maximum")){
            int times = RexText.returnDig(line);
            for (int i = 2; i <= times; i++) {
                res += nDRWSActual(i, start, end);
            }
        }else {
            int times = RexText.returnDig(line);
            res = nDRWSActual(times, start, end);
        }
        int index = Integer.parseInt(line.substring(0,line.indexOf(".The")));
        System.out.printf("Output #%s: "+res, index);
        System.out.println();
        return res;
    }
    /**
     * The actual of numberOfDifferentRoadsWithStops function
     */
    private int nDRWSActual(int times, int start, int end){
        int res = 0;
        if (start == end){
            String sta = String.valueOf((char) (start+'A'));
            int temp = 0; // 含有该城市的环的个数
            for (String s: cycleCitys
                 ) {
                if (s.contains(sta) && s.length() < times){
                    temp++;
                }else if (s.contains(sta) && s.length() == times){
                    res++;
                }
            }
            int[] cycleLength = new int[temp];
            int i = 0;
            for (String s: cycleCitys
                    ) {
                if (s.contains(sta)){
                    if (s.contains(sta) && s.length() < times){
                        cycleLength[i] = s.length();
                    }
                }
            }
            res += count(times, cycleLength);

        }else {
            for (String s: roads
                    ) {
                if (s.length()-1 == times){
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

    private int count(int n, int[]v) {
        int[] d= new int[n+1];
        d[0]=1;
        for (int aV : v) {
            for (int j = aV; j <= n; j++) {
                d[j] += d[j - aV];
            }
        }
        return d[n];
    }

    /**
     * Return the length of the shortest route(in terms of distance to travel) base given startCity and endCity
     *@param line  demand about the routes.like "7.The length of the shortest route (in terms of distance to travel) from A to C.".
     */
    public int lengthOfShortestRoute(String line){
        char startCity = RexText.returnChar(line)[0];
        char endCity = RexText.returnChar(line)[1];
        int res = Integer.MAX_VALUE;
        int start = startCity - 'A';
        int end = endCity - 'A';
        if (start == end){
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

    /**
     * Return the distance of the given route
     *@param line  demand about the routes.like "0.The distance of the route A-B-C.".
     */
    public String distanceOfSomeCity(String line){
        int res = 0;
        boolean isRoad = true;
        String road = RexText.returnString(line);
        int index = Integer.parseInt(line.substring(0,line.indexOf(".The")));
        for (int i = 0; i < road.length()-2; i+=2) {
            if (graph.getCityMat()[road.charAt(i)-'A'][road.charAt(i+2)-'A'] > 0){
                res += graph.getCityMat()[road.charAt(i)-'A'][road.charAt(i+2)-'A'];
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

    /**
     * Return the number of different routes base given startCity and endCity even with a maximum distance
     *@param line  demand about the routes.like "9.The number of different routes from C to C with a distance of less than 30.".
     */
    public int numberOfDifferentRoadsWithDistance(String line){
        char cycleStartCity = RexText.returnChar(line)[0];
        char cycleEndCity = RexText.returnChar(line)[1];
        nDRWDweight = RexText.returnDig(line);
        int res = 0;
        List<String> str = new ArrayList<String>();
        if (cycleStartCity == cycleEndCity){
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

    /**
     * Calculate the total distance of each cycle road
     *@param s  cycleRoad set
     */
    private int preCircu(String[] s) {
        int n = s.length;
        Map<String, Integer> roadWeight = new HashMap<String, Integer>();
        Stack<Integer> temp = new Stack<Integer>();
        Stack<String> nDRWDresRoad = new Stack<String>();
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

        circu(roadWeight, s, temp, nDRWDresRoad);
        return nDRWDres;
    }

    /**
     * DFS base cycle road
     *@param roadWeight  distance of road
     *@param s  cycleRoad set
     *@param temp  trace of valid roadWeight
     *@param nDRWDresRoad  trace of valid road
     */
    private void circu(Map roadWeight, String[] s, Stack<Integer> temp, Stack<String> nDRWDresRoad){
        for (String value : s) {
            if ((int) roadWeight.get(value) < nDRWDweight) {
                nDRWDres++;
                nDRWDweight -= (int) roadWeight.get(value);
                nDRWDresRoad.push(value);
                temp.push((int) roadWeight.get(value));
                circu(roadWeight, s, temp, nDRWDresRoad);
            }
        }
        if (!temp.empty()){
            nDRWDweight += temp.pop();
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(String path:nDRWDresRoad){
            if (first){
                sb.append(path);
                first = false;
            }else {
                sb.append(path.substring(1));
            }
        }
        nDRWDroads.add(sb.toString());
        if (!nDRWDresRoad.empty()){
            nDRWDresRoad.pop();
        }
    }

    private void displaynDRWDroads(){
       for (String s:nDRWDroads
             ) {
            System.out.println(s);
        }
    }
}
