package br.ecosystems;

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
	
	// Na sua vez voc� deve escolher entre tr�s marchas ou parar o carro [0,1,2,3]
	// Retorna a for�a da marcha escolhida
	public int[] play(int marcha) {
		if (finished) return new int[3];

		// A for�a da marcha � determinada pela posi��o do carro na pista
		int[] force = {0,3,1,2};
		if (players[activePlayer] < track/2) { // At� a metade da pista
			force[1] = 1; force[2] = 2; force[3] = 3;
		} else {
			if (players[activePlayer] < track*3/4) { // Se for menor que 3/4 da pista
				force[1] = 2; force[2] = 3; force[3] = 1;
			}
		}
		
		// Soma a for�a da marcha � posicao do carro.
		players[activePlayer] += force[marcha];

		// preenche o resultado da a��o
		int[] results = {marcha, force[marcha], players[activePlayer]};

		if (activePlayer == 0) lap++;
		
		if (log) {
			System.out.println("LAP=" + lap + "   Marcha= " + results[0] + "  For�a= " + results[1] + "  Carro= " + activePlayer + "  Posi��o= " + results[2]);
		}
		

		// Verifica se ganhou
		if (players[activePlayer] >= track) {
			winner = activePlayer;
			finished = true;
			if (log) System.out.println("Final de jogo: Jogador " + winner + " ganhou.");
		} else {
			activePlayer++;
			if (activePlayer >= players.length) activePlayer = 0;			
		}
		
		return results;
	}
	
	// A partir daqui s�o m�todos que fazem a integra��o com a rede neural

	@Override
	public Ecosystem getInstance() {
		Ecosystem race = new Race(players.length, track);
		if (log) ((Race)race).logon();
		return race;
	}
	
	@Override
	public int[] interaction(int[] inputs) {
		if (finished) return new int[3];
		
		// Transforma a decis�o da RN na escolha de marcha
		int marcha = 0;
		if (inputs[0] == 0 && inputs[1] > 0) marcha = 1;
		if (inputs[0] > 0 && inputs[1] == 0) marcha = 2;
		if (inputs[0] > 0 && inputs[1] > 0) marcha = 3;
		
		// Executa o jogo e recebe o resultado
		int[] results = play(marcha);
		
		return results;
	}

	@Override
	public boolean finished() {
		return finished;
	}

	@Override
	public int winner() {
		System.out.println("Winner: " + winner + "   Lap: " + lap);
		return winner;
	}
	
	public void logon() {
		log = !log;
	}
}
