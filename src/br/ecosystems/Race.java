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
	
	// Na sua vez você deve escolher entre três marchas ou parar o carro [0,1,2,3]
	// Retorna a força da marcha escolhida
	public int[] play(int marcha) {
		if (finished) return new int[3];

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

		if (activePlayer == 0) lap++;
		
		if (log) {
			System.out.println("LAP=" + lap + "   Marcha= " + results[0] + "  Força= " + results[1] + "  Carro= " + activePlayer + "  Posição= " + results[2]);
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
	
	// A partir daqui são métodos que fazem a integração com a rede neural

	@Override
	public Ecosystem getInstance() {
		Ecosystem race = new Race(players.length, track);
		if (log) ((Race)race).logon();
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
