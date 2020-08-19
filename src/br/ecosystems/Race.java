package br.ecosystems;

import java.util.Arrays;

import br.evolution.Ecosystem;

public class Race implements Ecosystem {
	
	private int[] players;
	private int activePlayer = 0;
	private int track;
	private int winner = -1;
	private boolean finished = false;
	private int lap = 0;
	private boolean log = false;

	public Race(int players, int track) {
		this.players = new int[players];
		this.track = track;
	}

	@Override
	public Ecosystem getInstance() {
		Ecosystem race = new Race(players.length, track);
		return race;
	}
	
	@Override
	public int[] interaction(int[] inputs) {
		if (finished) return new int[3];
		
		// Transforma a decisão da RN na escolha de marcha
		int marcha = 0;
		if (inputs[0] == 0 && inputs[1] > 0) marcha = 1;
		if (inputs[0] > 0 && inputs[1] == 0) marcha = 2;
		if (inputs[0] > 0 && inputs[1] > 0) marcha = 3;
		
		// A força da marcha é determinada pela posição do carro na pista
		int[] force = {0,3,1,2};
		if (players[activePlayer] < track/2) { // Até a metade da pista
			force[1] = 1; force[2] = 2; force[3] = 3;
		} else {
			if (players[activePlayer] < track*3/4) { // Se for menor que 3/4 da pista
				force[1] = 2; force[2] = 3; force[3] = 1;
			}
		}
			
		// Soma a força da marcha à posicao do carro.
		players[activePlayer] += force[marcha];
		
		// preenche o resultado da ação
		int[] results = {marcha, force[marcha], players[activePlayer]};
		
		if (log) {
			System.out.println("LAP=" + lap + "   Marcha= " + marcha + "  Força= " + force[marcha] + "  Carro= " + activePlayer + "  Posição= " + players[activePlayer] + "    " + Arrays.toString(inputs));
		}
		
		// Verifica se ganhou
		if (players[activePlayer] >= track) {
			winner = activePlayer;
			finished = true;
		} else {
			activePlayer++;
			if (activePlayer >= players.length) activePlayer = 0;			
		}
		
		return results;
	}

	@Override
	public boolean finished() {
		lap++;
		return finished;
	}

	@Override
	public int winner() {
		System.out.println("Winner: " + winner + "   Lap: " + (lap-1));
		return winner;
	}
	
	public void logon() {
		log = !log;
	}
}
