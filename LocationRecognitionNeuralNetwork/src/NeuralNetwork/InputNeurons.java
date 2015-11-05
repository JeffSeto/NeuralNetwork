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
public class InputNeurons implements Neuron{
    int y;
    double input;
    NeuralNetwork neuralNetwork;
    
    public InputNeurons(int y, double input){
        this.input = input;
        this.y = y;
        neuralNetwork = NeuralNetwork.getInstance();
    }

    @Override
    public double getOutput() {
        return input;
    }

    @Override
    public double calculateInputs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
