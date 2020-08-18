package br;

import java.util.Arrays;

import br.ecosystems.Race;
import br.evolution.Ecosystem;
import br.neuralnetwork.Layer;
import br.neuralnetwork.NeuralNetwork;
import br.neuralnetwork.Neuron;

public class Main {

	public static void main(String[] args) {
		
		//*/ Testando o Ecosistema (Race)
		
		// Cria popula��o
		int players = 3;
		int[] structure = {4,2};
		NeuralNetwork[] populacao = new NeuralNetwork[players]; 
		for (int i = 0; i < populacao.length; i++) {
			populacao[i] = new NeuralNetwork(structure, 3); // O output do sistema � o input da RN
		}
		
		// A sa�da do ecosistema � a entrada na rede neural. � como a RN "ver" o que est� acontecendo pra tomar uma decis�o.
		// Ex.: ["a marcha atual", "a for�a da marcha escolhida naquela posicao", "posi��o do carro na pista"]
		int[][] outputSys = new int[populacao.length][3];
		
		// A entrada no ecosistema consiste na decis�o tomada, que � a sa�da da RN. No jogo de corrida � a marcha escolhida.
		// Ex.: [0,0] parado, [0,1] marcha 1, [1,0] marcha 2, [1,1] marcha 3 (o 1 � qualquer n�mero diferente de 0 positivo)
		int[][] inputSys = new int[populacao.length][2]; 
		for (int i = 0; i < inputSys.length; i++) { // inicialmente todos os carros come�am na menor marcha
			inputSys[i][0] = 0;
			inputSys[i][1] = 1;
		}
		
		// Cria o sistema
		Ecosystem race = new Race(populacao.length, 1000);
		
		// Interage com o sistema at� um ponto de parada, no caso de um jogo, at� termos um vencedor.
		while (true) {
			// todos jogam cada um na sua vez
			for (int i = 0; i < populacao.length; i++) {
				outputSys[i] = race.interaction(inputSys[i]); // A primeira intera��o na corrida � predeterminada na marcha 1
				inputSys[i] = populacao[i].interaction(outputSys[i]); // O resultado disso � dado para a RN que toma uma decis�o.
			}
			if (race.finished()) break; // ponto de parada
		}
		
		int winner = race.winner();
		int[][][] dnaWinner = populacao[winner].getDNA();
		System.out.println("Winner " + Arrays.deepToString(dnaWinner));
		//*/
		
		/*/ Testando a rede neural
		int[] inputs = {1,2,3};
		
		int[] structure = {3,4,2};
		NeuralNetwork nn1 = new NeuralNetwork(structure, inputs.length);
		nn1.log();
		int[][][] dna = nn1.getDNA();
		System.out.println(Arrays.deepToString(dna));
		
		NeuralNetwork nn2 = new NeuralNetwork(dna);
		nn2.log();
		nn2.mutation(0.5f);
		nn2.log();
		
		System.out.println("\n" + Arrays.toString(nn2.interaction(inputs)));
		nn2.logon();
		System.out.println(Arrays.toString(nn2.interaction(inputs)));
		//*/
		
		/*/ Testando a camada de neur�nios
		Layer l1 = new Layer(4, 3); // quatro neur�nios com tr�s inputs cada.
		l1.log();
		int[][] dna = l1.getDNA();
		System.out.println(Arrays.deepToString(dna));
		
		Layer l2 = new Layer(dna);
		l2.log();
		l2.mutation(0.5f);
		l2.log();
		
		int[] inputs = {1,2,3};
		System.out.println("\n" + Arrays.toString(l2.interaction(inputs)));
		l2.logon();
		System.out.println(Arrays.toString(l2.interaction(inputs)));
		//*/
		
		
		/*/ Testando um neur�nio
		int[] inputs = {1,2,3};
		
		Neuron n1 = new Neuron(input.length);
		n1.log();
		int[] dna = n1.getDNA();
		System.out.println(Arrays.toString(dna));
		
		Neuron n2 = new Neuron(dna); // Copia o neur�nio.
		n2.log();
		n2.mutation(); // Modifica o neur�nio
		n2.log();
		
		
		System.out.println("\n" + n2.interaction(inputs));
		n2.logon();
		System.out.println(n2.interaction(inputs));
		//*/
	}

}
