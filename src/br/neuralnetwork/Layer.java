package br.neuralnetwork;

import java.util.Random;

// Uma camada de neurônios é uma coleção/grupo de neurônios.
// Uma rede de neural é uma pilha de camadas de neurônios.
public class Layer {

	private Neuron[] neurons;
	private boolean log = false;
	
	// Cria uma nova camada
	public Layer(int amountOfNeurons, int quantInputs) {
		neurons = new Neuron[amountOfNeurons];
		for (int i = 0; i < neurons.length; i++) 
			neurons[i] = new Neuron(quantInputs);
	}

	// Cria uma camada a partir de um DNA
	public Layer(int[][] dna) {
		neurons = new Neuron[dna.length];
		for (int i = 0; i < neurons.length; i++) 
			neurons[i] = new Neuron(dna[i]);
	}
	
	public int[] interaction(int[] inputs) {
		int[] output = new int[neurons.length];
		for (int i = 0; i < output.length; i++) {
			if (log) {
				System.out.print("   " + (i+1) + "- ");
				neurons[i].logon();
			}
			output[i] = neurons[i].interaction(inputs);
		}
		return output;
	}
	
	// Force vai de 0.0 até 1.0 (porcentagem de mudança no DNA de uma layer).
	// 0.5 executa a mutação em metade dos neurônios.
	// 1.0 executa em todos.
	// Pode ser que seja menos porque ele pode sortear o mesmo neurônio mais vezes.
	public void mutation(float force) {
		Random r = new Random();
		for (int i = 0; i < neurons.length * force; i++) {
			neurons[r.nextInt(neurons.length)].mutation();
		}
	}
	
	public int[][] getDNA() {
		int[][] dna = new int[neurons.length][neurons[0].getDNA().length];
		for (int i = 0; i < neurons.length; i++) {
			dna[i] = neurons[i].getDNA();
		}
		return dna;
	}
	
	public void log() {
		System.out.println("LAYER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for (int i = 0; i < neurons.length; i++) {
			System.out.print("   " + (i+1) + "- ");
			neurons[i].log();
		}
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	public void logon() {
		log = !log;
	}
}
