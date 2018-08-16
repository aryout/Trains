package com.faceyee.utils;

import com.faceyee.service.TrainRoad;

import java.io.*;

/**
 * Util to collect-in graph input data
 *
 */
public class InputGraph {

    private String inputFile;
    public InputGraph(String inputFile){
        this.inputFile = inputFile;
    }

    /**
     * One graph object per line input, transfer to TrainRoad handle
     * @exception  if graph-input file not found
     */
    public void exec() throws IOException {
        try {
            File f= new File(inputFile);
            BufferedReader bufread;
            String read;
            bufread = new BufferedReader(new FileReader(f));
            TrainRoad trainRoad;
            while ((read = bufread.readLine()) != null) {
                System.out.println(read);
                trainRoad = new TrainRoad(read);
                trainRoad.solution();
            }
            bufread.close();
        }catch (FileNotFoundException e){
            throw e;
        }
    }
}
