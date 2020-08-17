package br;

import java.util.Arrays;

import br.neuralnetwork.Layer;
import br.neuralnetwork.Neuron;

public class Main {

	public static void main(String[] args) {
		
		//*/ Testando a camada de neur�nios
		Layer l1 = new Layer(4, 3); // quatro neur�nios com tr�s inputs cada.
		l1.log();
		int[][] dna = l1.getDNA();
		System.out.println(Arrays.deepToString(dna));
		
		Layer l2 = new Layer(dna);
		l2.log();
		l2.mutation(0.5f);
		l2.log();
		
		int[] input = {1,2,3};
		System.out.println("\n" + Arrays.toString(l2.interaction(input)));
		l2.logon();
		System.out.println(Arrays.toString(l2.interaction(input)));
		//*/
		
		
		/*/ Testando um neur�nio
		int[] input = {1,2,3};
		
		Neuron n1 = new Neuron(input.length);
		n1.log();
		int[] dna = n1.getDNA();
		System.out.println(Arrays.toString(dna));
		
		Neuron n2 = new Neuron(dna); // Copia o neur�nio.
		n2.log();
		n2.mutation(); // Modifica o neur�nio
		n2.log();
		
		
		System.out.println("\n" + n2.interaction(input));
		n2.logon();
		System.out.println(n2.interaction(input));
		//*/
	}

}
