package br;

import java.util.Arrays;
import java.util.Scanner;

import br.ecosystems.Race;
import br.ecosystems.SuperSenha;
import br.evolution.Ecosystem;
import br.evolution.Generation;
import br.neuralnetwork.Layer;
import br.neuralnetwork.NeuralNetwork;
import br.neuralnetwork.Neuron;

public class Main {

	public static void main(String[] args) {
		//*/ Testando o algoritmo generativo
		// 1. Cria popula��o
		int players = 1000;
		int[] structure = {20,15};
		NeuralNetwork[] populacao = new NeuralNetwork[players]; 
		for (int i = 0; i < populacao.length; i++) {
			populacao[i] = new NeuralNetwork(structure, 96);
		}
		
		// 2. Cria o Ecosistema
		Ecosystem ecosystema = new SuperSenha();
		//((SuperSenha) ecosystema).logon();
		
		// 3. Cria e executa o sistema evolucion�rio
		Generation g = new Generation(populacao, ecosystema, 0.0f);
		
		// 4. Determina-se os inputs e outputs para o ecosistema
		int[][] outputSys = new int[populacao.length][96];
		int[][] inputSys = new int[populacao.length][15];
		
		// 5. Configura��es iniciais para os outputs (O estado inicial do ambiente)
		for (int i = 0; i < inputSys.length; i++) { 
			inputSys[i] = new int[15];
		}
		
		int[][][] dnaWinner = g.generateMulti(10000, inputSys, outputSys);
		
		System.out.println("Winner " + Arrays.deepToString(dnaWinner));
		
		//*/
		
		
		/*/ Testando outro ambiente (SuperSenha https://www.youtube.com/watch?v=pzhKaYnN6Vc)
		// Rob� jogando
		
		// 1. Criando rede neural
		// A entrada s�o as 5 posi��es da senha, como cada posicao pode ter uma das 8 "cores", temos 8x5, 40 poss�veis c�digos. 2 elevado a 6, ent�o 6 neur�nios de sa�da
		// A senha precisa ser de 1 at� 8 (0 para vazio para o estado de sa�da)
		// A sa�da � todo o tabuleiro, as senhas utilizadas (60 posi��es preenchidas), os resultados dela (60 posi��es) e a �ltima pontua��o. 121 sensores
		// O estado vai sendo alimentado (preenchido) com o tempo.
		int players = 1;
		int[] structure = {20,15};
		NeuralNetwork robo = new NeuralNetwork(structure, 96);
		
		// 2. Criando a primeira entrada e a primeira sa�da
		// O outputSys � o estado do jogo (12 jogadas, cada uma com 8 estados ([a jogada],[a tentativa],[acertos],[quase acertos])
		int[] outputSys = new int[96]; // No come�o o tabuleiro est� zerado, vazio.
		// O inputSys � a decis�o para o jogo. Que precisa ser convertida para uma sequ�ncia de cinco n�meros.
		int[] inputSys = new int[15];

		// 3. Criando o ambiente
		SuperSenha ss = new SuperSenha();
		ss.logon();
		
		// 4. Jogadas
		int range = 0;
		while (true) {
			
			if (ss.finished()) break;

			System.out.println(Arrays.toString(outputSys));
			inputSys = robo.interaction(outputSys);
			
			// 5. Converte a sa�da do neur�nio na entrada aceita pelo ecosistema. A senha de cinco digitos.
			for (int i = 0; i < inputSys.length; i++) {
				if (inputSys[i] > 0) inputSys[i] = 1;
			}
			
			int[] pass = new int[5];
			int j = 0;
			for (int i = 0; i < pass.length; i++) {
				pass[i] = inputSys[j++] * 1 + inputSys[j++] * 2 + inputSys[j++] * 4 + 1;
			}
			System.out.println(Arrays.toString(inputSys));
			System.out.println(Arrays.toString(pass));
			
			int[] state = ss.play(pass); // [8]
			for (int i = 0; i < state.length; i++) {
				outputSys[range++] = state[i];
			}
			
			// winner, ao inv�s de retornar quem ganhou de v�rios jogadores, retorna a pontua��o do jogador.
		}
		//*/
		
		
		/*/ Testando outro ambiente (SuperSenha https://www.youtube.com/watch?v=pzhKaYnN6Vc)
		// Humando jogando
		
		// 1. Criando o ambiente
		SuperSenha ss = new SuperSenha();
		//ss.logon();

		// 2. Entrada de dados para o jogador humano
		Scanner s = new Scanner(System.in);
		
		// 3. Jogadas
		while (true) {
			System.out.print("Escolha uma senha de cinco n�meros de 1 at� 8: ");
			String pass = s.nextLine();
			
			int[] passpass = new int[5];
			for (int i = 0; i < passpass.length; i++) {
				passpass[i] = Integer.parseInt(pass.charAt(i) + "");
			}
			
			int[] outputs = ss.play(passpass);
			System.out.println(Arrays.toString(outputs));
			
			if (ss.finished()) break;
		}
		
		s.close();
		
		System.out.println("Pontua��o final: " + ss.winner());
		//*/
		
		
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
		int[] inputs = {1,1}; // Necess�rio para que o carro n�o fique parado eternamente
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
		
		/*/ Testando o algoritmo generativo
		// 1. Cria popula��o
		int players = 100;
		int[] structure = {4,2};
		NeuralNetwork[] populacao = new NeuralNetwork[players]; 
		for (int i = 0; i < populacao.length; i++) {
			populacao[i] = new NeuralNetwork(structure, 3);
		}
		
		// 2. Cria o Ecosistema
		Ecosystem race = new Race(populacao.length, 20);
		((Race) race).logon();
		
		// 3. Cria e executa o sistema evolucion�rio
		Generation g = new Generation(populacao, race, 0.5f);
		
		// 4. Determina-se os inputs e outputs para o ecosistema
		int[][] outputSys = new int[populacao.length][3];
		int[][] inputSys = new int[populacao.length][2];
		
		// 5. Configura��es iniciais para os outputs (O estado inicial do ambiente) no caso de todos os outputs serem zeros, se faz necess�rio que os inputs sejam uns.
		// Se for {0,0} o carro ficar� parado para sempre. Porque todas as entradas s�o zeradas no come�o.
		for (int i = 0; i < inputSys.length; i++) { 
			inputSys[i][0] = 1;
			inputSys[i][1] = 1;
		}
		
		int[][][] dnaWinner = g.generate(100000, inputSys, outputSys); // O melhor de 100000 gera��es.
		
		System.out.println("Winner " + Arrays.deepToString(dnaWinner));
		//*/
		
		/*/ Testando o Ecosistema (Race)
		
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
		Ecosystem race = new Race(populacao.length, 1000); // A dist�ncia da pista � 1000 unidades.
		
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
		
		nn1.clone(nn2.getDNA());
		nn1.log();
		
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
		
		Neuron n1 = new Neuron(inputs.length);
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
