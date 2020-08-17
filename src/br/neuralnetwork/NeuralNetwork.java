package br.neuralnetwork;

public class NeuralNetwork {
	
	private Layer[] layers;
	private boolean log = false;
	
	// Exemplo: tr�s camadas com 3,4,2 neur�nios respectivamente, recebendo 8 inputs.
	// A sa�da � a quantidade de neur�nios final, no caso 2 outputs, correspondendo a 4 poss�veis decis�es.
	// Os par�metros seriam ([3,4,2], 8) para este exemplo.
	// O m�nimo para uma rede neural s�o duas camadas.
	public NeuralNetwork(int[] amountOfNeuronsPerLayer, int quantInputs) {
		layers = new Layer[amountOfNeuronsPerLayer.length];
		for (int i = 0; i < layers.length; i++) {
			layers[i] = new Layer(amountOfNeuronsPerLayer[i], quantInputs);
			quantInputs = amountOfNeuronsPerLayer[i];
		}
	}
	
	public NeuralNetwork(int[][][] dna) {
		layers = new Layer[dna.length];
		for (int i = 0; i < layers.length; i++) {
			layers[i] = new Layer(dna[i]);
		}
	}

	// Quando uma muta��o � aplicada em uma rede neural, cada camada sofre uma porcentagem de muta��o.
	public void mutation(float force) {
		for (int i = 0; i < layers.length; i++) {
			layers[i].mutation(force);
		}
	}

	// Os inputs passam pela camada, s�o transformados em outputs, que ser�o os inputs da pr�xima camada.
	public int[] interaction(int[] inputs) {
		int[] output = inputs.clone();
		for (int i = 0; i < layers.length; i++) {
			if (log) {
				System.out.println((i+1) + "- Layer ");
				layers[i].logon();
			}
			output = layers[i].interaction(output);
		}
		return output;
	}
	
	public int[][][] getDNA() {
		int[][][] dna = new int[layers.length][layers[0].getDNA().length][layers[0].getDNA()[0].length];
		for (int i = 0; i < layers.length; i++) {
			dna[i] = layers[i].getDNA();
		}
		return dna;
	}
	
	public void log() {
		System.out.println("Neural Network ######################################################################");
		for (int i = 0; i < layers.length; i++) {
			System.out.print((i+1) + "- ");
			layers[i].log();
		}
		System.out.println("#####################################################################################");
	}
	
	public void logon() {
		log = !log;
	}
	
}
