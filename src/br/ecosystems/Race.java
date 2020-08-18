package br.ecosystems;

import java.util.Arrays;

import br.evolution.Ecosystem;

public class Race implements Ecosystem {
	
	private int[] players;
	private int activePlayer = 0;
	private int track;
	private int winner = -1;
	private boolean finished = false;

	public Race(int players, int track) {
		this.players = new int[players];
		this.track = track;
	}
	
	@Override
	public int[] interaction(int[] inputs) {
		// Transforma a decis�o da RN na escolha de marcha
		int marcha = 0;
		if (inputs[0] == 0 && inputs[1] > 0) marcha = 1;
		if (inputs[0] > 0 && inputs[1] == 0) marcha = 2;
		if (inputs[0] > 0 && inputs[1] > 0) marcha = 3;
		
		// A for�a da marcha � determinada pela posi��o do carro na pista
		int[] force = {0,3,1,2};
		if (players[activePlayer] < track/2) { // At� a metade da pista
			force[1] = 1; force[2] = 2; force[3] = 3;
		} else
			if (players[activePlayer] < track*3/4) { // Se for menor que 3/4 da pista
				force[1] = 2; force[2] = 3; force[3] = 1;
			}
		
		// Soma a for�a da marcha � posicao do carro.
		players[activePlayer] += force[marcha];
		if (players[activePlayer] >= track) {
			finished = true;
			winner = activePlayer;
		}

		System.out.print("Marcha= " + marcha + "  For�a= " + force[marcha] + "  Carro= " + activePlayer + "  Posi��o= " + players[activePlayer] + "    " + Arrays.toString(inputs));
		System.out.println();
		
		activePlayer++;
		if (activePlayer >= players.length) activePlayer = 0;
		
		
		int[] results = {marcha, force[marcha], players[activePlayer]};
		return results;
	}

	@Override
	public boolean finished() {
		return finished;
	}

	@Override
	public int winner() {
		System.out.println("Winner: " + winner);
		return winner;
	}
}
