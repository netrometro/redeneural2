package br;

import java.util.Arrays;
import java.util.Scanner;

import br.ecosystems.Race;
import br.evolution.Ecosystem;
import br.evolution.Generation;
import br.neuralnetwork.Layer;
import br.neuralnetwork.NeuralNetwork;
import br.neuralnetwork.Neuron;

public class Main {

	public static void main(String[] args) {
		//*/ Testando outro ambiente (SuperSenha https://www.youtube.com/watch?v=pzhKaYnN6Vc)
		
		
		/*/ Testando ecosistema com um jogador humano e uma rede neural
		// Criando a RN
		int players = 1;
		int[] structure = {4,2};
		NeuralNetwork robo = new NeuralNetwork(structure, 3);
		

		// 2. Cria o Ecosistema
		Race race = new Race(2, 20);
		race.logon();
		
		// 3. Entrada de dados para o jogador humano
		Scanner s = new Scanner(System.in);
		
		// 4. Jogo em si
		int[] inputs = {1,1}; // Necessário para que o carro não fique parado eternamente
		while (true) {
			System.out.print("Escolha a marcha [0,1,2,3]: ");
			int marcha = Integer.parseInt(s.nextLine());
			System.out.println();
			
			int[] outputs = race.play(marcha);

			race.interaction(inputs);
			inputs = robo.interaction(outputs);
			
			if (race.finished()) break;
		}
		
		s.close();
		//*/
		
		//*/ Testando o algoritmo generativo
		// 1. Cria população
		int players = 100;
		int[] structure = {4,2};
		NeuralNetwork[] populacao = new NeuralNetwork[players]; 
		for (int i = 0; i < populacao.length; i++) {
			populacao[i] = new NeuralNetwork(structure, 3);
		}
		
		// 2. Cria o Ecosistema
		Ecosystem race = new Race(populacao.length, 20);
		//((Race) race).logon();
		
		// 3. Cria e executa o sistema evolucionário
		Generation g = new Generation(populacao, race, 0.5f);
		
		// 4. Determina-se os inputs e outputs para o ecosistema
		int[][] outputSys = new int[populacao.length][3];
		int[][] inputSys = new int[populacao.length][2];
		
		// 5. Configurações iniciais para os outputs (O estado inicial do ambiente) no caso de todos os outputs serem zeros, se faz necessário que os inputs sejam uns.
		// Se for {0,0} o carro ficará parado para sempre. Porque todas as entradas são zeradas no começo.
		for (int i = 0; i < inputSys.length; i++) { 
			inputSys[i][0] = 1;
			inputSys[i][1] = 1;
		}
		
		int[][][] dnaWinner = g.generate(100000, inputSys, outputSys); // O melhor de 100000 gerações.
		
		System.out.println("Winner " + Arrays.deepToString(dnaWinner));
		//*/
		
		/*/ Testando o Ecosistema (Race)
		
		// Cria população
		int players = 3;
		int[] structure = {4,2};
		NeuralNetwork[] populacao = new NeuralNetwork[players]; 
		for (int i = 0; i < populacao.length; i++) {
			populacao[i] = new NeuralNetwork(structure, 3); // O output do sistema é o input da RN
		}
		
		// A saída do ecosistema é a entrada na rede neural. É como a RN "ver" o que está acontecendo pra tomar uma decisão.
		// Ex.: ["a marcha atual", "a força da marcha escolhida naquela posicao", "posição do carro na pista"]
		int[][] outputSys = new int[populacao.length][3];
		
		// A entrada no ecosistema consiste na decisão tomada, que é a saída da RN. No jogo de corrida é a marcha escolhida.
		// Ex.: [0,0] parado, [0,1] marcha 1, [1,0] marcha 2, [1,1] marcha 3 (o 1 é qualquer número diferente de 0 positivo)
		int[][] inputSys = new int[populacao.length][2]; 
		for (int i = 0; i < inputSys.length; i++) { // inicialmente todos os carros começam na menor marcha
			inputSys[i][0] = 0;
			inputSys[i][1] = 1;
		}
		
		// Cria o sistema
		Ecosystem race = new Race(populacao.length, 1000); // A distância da pista é 1000 unidades.
		
		// Interage com o sistema até um ponto de parada, no caso de um jogo, até termos um vencedor.
		while (true) {
			// todos jogam cada um na sua vez
			for (int i = 0; i < populacao.length; i++) {
				outputSys[i] = race.interaction(inputSys[i]); // A primeira interação na corrida é predeterminada na marcha 1
				inputSys[i] = populacao[i].interaction(outputSys[i]); // O resultado disso é dado para a RN que toma uma decisão.
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
		
		nn1.clone(nn2.getDNA());
		nn1.log();
		
		System.out.println("\n" + Arrays.toString(nn2.interaction(inputs)));
		nn2.logon();
		System.out.println(Arrays.toString(nn2.interaction(inputs)));
		//*/
		
		/*/ Testando a camada de neurônios
		Layer l1 = new Layer(4, 3); // quatro neurônios com três inputs cada.
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
		
		
		/*/ Testando um neurônio
		int[] inputs = {1,2,3};
		
		Neuron n1 = new Neuron(input.length);
		n1.log();
		int[] dna = n1.getDNA();
		System.out.println(Arrays.toString(dna));
		
		Neuron n2 = new Neuron(dna); // Copia o neurônio.
		n2.log();
		n2.mutation(); // Modifica o neurônio
		n2.log();
		
		
		System.out.println("\n" + n2.interaction(inputs));
		n2.logon();
		System.out.println(n2.interaction(inputs));
		//*/
	}

}
