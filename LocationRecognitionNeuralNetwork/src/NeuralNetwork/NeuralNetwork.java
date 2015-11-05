/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jeffrey
 */
public class NeuralNetwork {
    
    public Neuron neurons[][];
    public int neuronMap[];
    public static NeuralNetwork instance = null;
    
    public int numberOfLayers;
    public int maxNeuronsInLayer;
    
    WeightManager weightManager;
    public NeuralNetwork(){
        //Get neuron map
        getNeuronMap();
        //Save numberOfLayers
        numberOfLayers = neuronMap.length;
        //Save maxNeuronsInLayer
        int max = neuronMap[0];
        for(int i = 1; i < neuronMap.length; i++){
            if(max < neuronMap[i]){
                max = neuronMap[i];
            }
        }
        maxNeuronsInLayer = max;
        //Get instance of WeightManager
        weightManager = WeightManager.getInstance();
        //Reset and recreate weights if required
        if(Constants.RESET){
            weightManager.resetWeights();
        }
        //Initialize array of neurons
        neurons = new Neuron[maxNeuronsInLayer][numberOfLayers];
        //Initialize all hidden neurons
        for(int i = 1; i < numberOfLayers-1; i++){
            for(int j = 0; j < neuronMap[i]; j++){
                neurons[i][j] = new HiddenNeuron(i,j);
            }
        }
        //Initialize output neurons
        for(int i = 0; i < neuronMap[numberOfLayers]; i++){
            neurons[numberOfLayers-1][i] = new OutputNeuron();
        }
        
    }    
    
    //Get list of neurons in specified layer
    public Neuron[] getNeuronList(int layer){
        assert(layer > 0);
        return neurons[layer];
    }
    
    //Get number of neurons in specified layer
    public int getNumNeurons(int layer){
        assert(layer > 0 && layer < neuronMap.length);
        return neuronMap[layer];
    }
    
    //Get numberOfLayers
    public int getNumberOfLayers(){
        return numberOfLayers;
    }
    
    //Get maxNeuronsInLayer
    public int getMaxNeuronsInLayer(){
        return maxNeuronsInLayer;
    }
    
    //Get saved neuron map from files
    private void getNeuronMap(){
        FileReader reader;
        Scanner sc;
        try{
            reader = new FileReader(Constants.NEURON_MAP_DIRECTORY);
            sc = new Scanner(reader);
            String mapString = sc.nextLine();
            String rawMap[] = mapString.split(",");
            for(int i = 0; i < rawMap.length; i++){
                neuronMap[i] = Integer.parseInt(rawMap[i]);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found:  Neuron Map Directory");
        }
    }
    
    //Get intance of neural network
    public static NeuralNetwork getInstance(){
        if(instance == null){
            instance = new NeuralNetwork();
        }
        return instance;
    }
}
