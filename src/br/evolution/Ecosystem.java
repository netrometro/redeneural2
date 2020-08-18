package br.evolution;

public interface Ecosystem {

	public int[] interaction(int[] inputs); // Manipulação do sistema
	public boolean finished(); // Condição de parada do sistema
	public int winner(); // Posicao do vencedor
	
}
