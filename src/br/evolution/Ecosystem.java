package br.evolution;

public interface Ecosystem {

	public Ecosystem getInstance(); // Cria um novo ambiente
	public int[] interaction(int[] inputs); // Manipula��o do sistema
	public boolean finished(); // Condi��o de parada do sistema
	public int winner(); // Posicao do vencedor
	
}
