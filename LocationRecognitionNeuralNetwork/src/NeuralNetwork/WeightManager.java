/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jeffrey
 */
public class WeightManager {
    
    public static final int NO_NEURON = 1000000;
    public static final int INPUT_NEURON = -9999;
    public static final int OUTPUT_NEURON = 9999;

    public static WeightManager instance;
    public double[][][] weights;
    
    NeuralNetwork neuralNetwork;

    public WeightManager() {
        //Initialize ArrayList that will hold raw weight info
        ArrayList<String> lines = new ArrayList();
        //Initialize FileReader and Scanner
        FileReader reader = null;
        Scanner sc = null;
        //Get instance of NeuralNetwork
        neuralNetwork = NeuralNetwork.getInstance();
        try {
            //Set FileReader and Scanner
            reader = new FileReader(Constants.WEIGHT_DIRECTORY);
            sc = new Scanner(reader);
            //Read all lines in weight.csv file
            boolean run = true;
            while (run) {
                try {
                    lines.add(sc.nextLine());
                } catch (Exception e) {
                    run = false;
                }
            }
            
            //I was too lazy to type shit
            int maxX = neuralNetwork.getNumberOfLayers();
            int maxY = neuralNetwork.getMaxNeuronsInLayer();
            //Declare 3-D array of doubles
            weights = new double[maxX][maxY][maxY];
            //Transfer and translate raw weight data into 3-D array "weights"
            for (int i = 0; i < maxY; i++) {
                String[] temp = lines.get(i).split(",");
                for (int j = 0; j < maxX; j++) {
                    String[] weightString = temp[j].split(".");
                    for (int k = 0; k < weightString.length; k++) {
                        weights[j][i][k] = Double.parseDouble(weightString[k]);
                    }
                }
            }
            
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
    
    //Reset and recreate all weights;
    public void resetWeights(){
        for(int i = 0; i < neuralNetwork.getNumberOfLayers(); i++){
            
            for(int j = 1; j < neuralNetwork.getNumNeurons(i);j++){
                
            }
        }
    }
    
    //Save new weight values
    public void saveWeights(){
        
        
    }

    //Get weights of specified node
    public double[] getWeights(int x, int y) {
        double[] weights = this.weights[x][y];
        return weights;
    }

    //Set weights of specified node
    public void setWeights(int x, int y, double[] weights) {
        this.weights[x][y] = weights;
    }

    //Get WeightManager instance
    public static WeightManager getInstance() {
        if (instance == null) {
            instance = new WeightManager();
        }
        return instance;
    }
}
