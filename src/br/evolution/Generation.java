package br.evolution;

import br.neuralnetwork.NeuralNetwork;

public class Generation {
	
	private NeuralNetwork[] population;
	private Ecosystem ecosystem;
	private float mutation;
	
	public Generation(NeuralNetwork[] population, Ecosystem ecosystem, float mutation) {
		this.population = population;
		this.ecosystem = ecosystem;
		this.mutation = mutation;
	}
	
	public int[][][] generate(int generations) {
		
		// A quantidade de vezes que o Ecosystema é jogado.
		for (int i = 0; i < generations; i++) {
			// Registra o resultado de cada indivíduo
			//int[][] outputs = new int[population.length][output.length];
			
		}
		
		return null;
	}
}
