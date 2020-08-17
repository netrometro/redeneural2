package br;

import java.util.Arrays;

import br.neuralnetwork.Layer;
import br.neuralnetwork.NeuralNetwork;
import br.neuralnetwork.Neuron;

public class Main {

	public static void main(String[] args) {
		
		//*/ Testando a rede neural
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
