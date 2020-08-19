package br.ecosystems;

import java.util.Random;

import br.evolution.Ecosystem;

public class SuperSenha implements Ecosystem {
	
	private int[] pass;
	
	public SuperSenha() {
		Random r = new Random();
		// Sorteia uma senha sem números repetidos
		int i = 0;
		while (true) {
			int number = r.nextInt(8) + 1; // De 1 até 8
			
		}
	}

	@Override
	public Ecosystem getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] interaction(int[] inputs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int winner() {
		// TODO Auto-generated method stub
		return 0;
	}

}
