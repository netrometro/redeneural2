package br.evolution;

import br.neuralnetwork.NeuralNetwork;

public class Generation {
	
	private NeuralNetwork[] population;
	private Ecosystem ecosystem;
	private float mutation;
	
	private int gen = 1;
	
	public Generation(NeuralNetwork[] population, Ecosystem ecosystem, float mutation) {
		this.population = population;
		this.ecosystem = ecosystem;
		this.mutation = mutation;
	}
	
	public int[][][] generate(int generations, int[][] inputss, int[][] outputss) {
		NeuralNetwork winner = null;
		
		// 1. O ecosistema ser� rodado a quantidade de gera��es determinada
		for (int i = 0; i < generations; i++) {
			// Apaga a mem�ria residual dos neur�nios.
			int[][] inputs = inputss.clone();
			for (int j = 0; j < inputss.length; j++) {
				inputs[j] = inputss[j].clone();
			}
			int[][] outputs = outputss.clone();
			for (int j = 0; j < outputss.length; j++) {
				outputs[j] = outputss[j].clone();
			}
			
			// 2. Executa o ambiente determinando o melhor dentre a popula��o.
			stop:
			while (true) {
				for (int j = 0; j < population.length; j++) {
					if (!ecosystem.finished()) {
						outputs[j] = ecosystem.interaction(inputs[j]);
						inputs[j] = population[j].interaction(outputs[j]);
					} else {
						break stop; // ponto de parada
					}
				}
			}

			System.out.print("Generation: " + (gen++) + "   ");
			
			// A cada gera��o teremos um vencedor.
			int positionWinner = ecosystem.winner();
			winner = population[positionWinner];
			
			// O vencedor � clonado na gera��o seguinte com muta��o.
			// E participa da competi��o na nova gera��o sem altera��es.
			for (int j = 0; j < population.length; j++) {
				population[j].clone(winner.getDNA());
			}
			for (int j = 1; j < population.length; j++) {
				population[j].mutation(mutation);
			}
			
			// Cria um novo ambiente
			ecosystem = ecosystem.getInstance();
		}
		
		return winner.getDNA();
	}
}
