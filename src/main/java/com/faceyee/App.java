package com.faceyee;

import com.faceyee.utils.InputGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        String  inputFile;
        if (args.length == 0)
        {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                for (;;) {
                    System.out.println("please type graph data path: ");
                    String line = in.readLine();
                    if (line != null){
                        inputFile = line;
                        break;
                    }
                }
                in.close();
                //System.out.println(inputFile);
                InputGraph inputGraph = new InputGraph(inputFile);
                inputGraph.exec();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(args.length >= 1)
        {
            inputFile = args[0];
            InputGraph inputGraph = new InputGraph(inputFile);
            inputGraph.exec();
        }
    }
}
