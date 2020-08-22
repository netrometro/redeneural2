package br.evolution;

import java.util.Arrays;

import br.neuralnetwork.NeuralNetwork;

public class Generation {
	
	private NeuralNetwork[] population;
	private Ecosystem ecosystem;
	private Ecosystem[] world;
	private float mutation;
	
	private int gen = 1;
	
	public Generation(NeuralNetwork[] population, Ecosystem ecosystem, float mutation) {
		this.population = population;
		this.ecosystem = ecosystem;
		this.mutation = mutation;
	}
	
	public int[][][] generateMulti(int generations, int[][] inputss, int[][] outputss) {
		NeuralNetwork winner = null;
		
		// 1. O ecosistema será rodado a quantidade de gerações determinada
		for (int i = 0; i < generations; i++) {
			// Cria um grupo de Ecosistemas, um para cara indivíduo. Porque neste caso, temos um ecosistema para cada indivíduo.
			world = new Ecosystem[population.length];
			for (int j = 0; j < population.length; j++) {
				world[j] = ecosystem.getInstance();
			}
			
			// Apaga a memória residual dos neurônios.
			int[][] inputs = inputss.clone();
			for (int j = 0; j < inputss.length; j++) {
				inputs[j] = inputss[j].clone();
			}
			int[][] outputs = outputss.clone();
			for (int j = 0; j < outputss.length; j++) {
				outputs[j] = outputss[j].clone();
			}
			
			// 2. Executa o ambiente determinando o melhor dentre a população.
			stop:
			while (true) {
				for (int j = 0; j < population.length; j++) {
					if (!world[j].finished()) {
						outputs[j] = world[j].interaction(inputs[j]);
						inputs[j] = population[j].interaction(outputs[j]);
					} else {
						if (world[j].winner() == 10) {
							System.out.println("Melhor: " + j + " Pontuação: " + world[j].winner());
							System.out.println(Arrays.deepToString(population[j].getDNA()));
						}
						break stop; // ponto de parada
					}
				}
			}

			System.out.print("Generation: " + (gen++) + "   ");
			
			// A cada geração coletamos os pontos de cada indivíduo e comparamos para ver quem é o maior. Determinando assim o vencedor.
			int maior = Integer.MIN_VALUE;
			int best = -1;
			int media = 0;
			for (int j = 0; j < world.length; j++) {
			    if (world[j].winner() > maior) {
			        maior = world[j].winner();
			        best = j;
			    }
			    media += world[j].winner();
			}
			media = media / world.length;
			System.out.println("Média: " + media );
			
			winner = population[best];
			
			
			// O vencedor é clonado na geração seguinte com mutação.
			// E participa da competição na nova geração sem alterações.
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
	
	public int[][][] generate(int generations, int[][] inputss, int[][] outputss) {
		NeuralNetwork winner = null;

		// 1. O ecosistema será rodado a quantidade de gerações determinada
		for (int i = 0; i < generations; i++) {
			
			// Apaga a memória residual dos neurônios.
			int[][] inputs = inputss.clone();
			for (int j = 0; j < inputss.length; j++) {
				inputs[j] = inputss[j].clone();
			}
			int[][] outputs = outputss.clone();
			for (int j = 0; j < outputss.length; j++) {
				outputs[j] = outputss[j].clone();
			}

			// 2. Executa o ambiente determinando o melhor dentre a população.
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

			
		}
		
		return winner.getDNA();
	}
}
