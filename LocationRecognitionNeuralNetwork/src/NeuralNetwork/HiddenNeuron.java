/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork;

/**
 *
 * @author Jeffrey
 */
public class HiddenNeuron implements Neuron {

    private final int x, y;
    private double output;
    WeightManager weightManager;
    NeuralNetwork neuralNetwork;
    
    //Constructor for HiddenNeuron
    public HiddenNeuron(int x, int y) {
        this.x = x;
        this.y = y;
        //Save WeightManager and NeuralNetwork instances
        weightManager = WeightManager.getInstance();
        neuralNetwork = NeuralNetwork.getInstance();
    }
    
    //Get output of this neuron
    @Override
    public double getOutput() {
        return output;
    }
    
    //Calculate output of this neuron
    public void calculateOutput() {
        output = 1 / (1 - Math.pow(Math.E, -calculateInputs()));
    }
    
    //Calculate the sum of all inputs and weights leading to this neuron
    @Override
    public double calculateInputs() {
        double inputs = 0;
        double[] weights = weightManager.getWeights(x, y);
        Neuron[] feederNeurons = neuralNetwork.getNeuronList(x - 1);
        for (int i = 0; i < weights.length; i++) {
            inputs += feederNeurons[i].getOutput() * weights[i];
        }
        return inputs;
    }
}
