package com.faceyee.utils;

import com.faceyee.service.TrainRoad;

import java.io.*;

/**
 * Created by 97390 on 8/14/2018.
 */
public class InputGraph {

    private  String inputFile;
    public InputGraph(String inputFile){
        this.inputFile = inputFile;
    }

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
                trainRoad.fun();
            }
            bufread.close();
        }catch (FileNotFoundException e){
            throw e;
        }
    }
}
