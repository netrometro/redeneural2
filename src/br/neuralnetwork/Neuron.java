package br.neuralnetwork;

import java.util.Arrays;
import java.util.Random;

public class Neuron {
	
	private int[] weights;
	private boolean log = false;
	
	// Cria um neurônio novo
	public Neuron(int quantInputs) {
		weights = new int[quantInputs];
		mutation();
	}
	
	// Cria uma cópia de um neurônio a partir de um DNA
	public Neuron(int[] dna) {
		weights = dna.clone();
	}
	
	public int interaction(int[] inputs) {
		int output = 0;
		
		int[] results = new int[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			results[i] = inputs[i] * weights[i];
			output += results[i];
		}
		
		if (log) {
			System.out.println("Neuron ---------------------------------------------------------------------------");
			System.out.println("      W=" + Arrays.toString(weights) + 
			                 "  I=" + Arrays.toString(inputs) + 
			                 "  R=" + Arrays.toString(results) + 
			                 "  O=" + output);
		}
		
		if (output < 0) output = 0;
		return output;
	}
	
	public void mutation() {
		Random r = new Random();
		for (int i = 0; i < weights.length; i++) {
			weights[i] = r.nextInt(2001)-1000;
		}
	}
	
	public void clone(int[] dna) {
		weights = dna.clone();
	}
	
	// Um DNA é o estado do neurônio.
	public int[] getDNA() {
		return weights;
	}
	
	public void log() {
		System.out.print(Arrays.toString(weights));
	}
	
	public void logon() {
		log = !log;
	}
}
