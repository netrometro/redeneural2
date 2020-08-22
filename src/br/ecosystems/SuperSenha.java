package br.ecosystems;

import java.util.Arrays;
import java.util.Random;

import br.evolution.Ecosystem;

public class SuperSenha implements Ecosystem {
	
	private int[] pass = new int[5];
	private int round = 0;
	private boolean finished = false;
	private int winner = 0;
	
	private boolean log = false;
	
	public SuperSenha() {
		Random r = new Random();
		int i = 0;
		while (true) {
			int number = r.nextInt(8) + 1;
			boolean exist = false;
			for (int j = 0; j < pass.length; j++) {
				if (pass[j] == number) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				pass[i] = number;
				i++;
			}
			if (i == pass.length) break;
		}
	}
	
	// O jogador entrega uma senha e recebe o resultado daquela jogada
	public int[] play(int[] chance) {
		int[] results = new int[8]; // Os 5 valores da tentativa mais a quantidade de acertos(cor e local) e acertos (cor)
		
		int perfect = 0;
		int almost = 0;
		int[] passpass = pass.clone();
		
		for (int i = 0; i < chance.length; i++) {
			if (passpass[i] == chance[i]) {
				perfect++;
				passpass[i] = -1;
			}
		}
		for (int i = 0; i < passpass.length; i++) {
			if (passpass[i] == -1) continue;
			for (int j = 0; j < chance.length; j++) {
				if (passpass[i] == chance[j]) {
					almost++;
					passpass[i] = -1;
				}
			}
		}
		
		// Verifica o quão bem foi
		if ((perfect * 2) + almost > winner) winner = (perfect * 2) + almost;
		
		round++;
		
		results[0] = round;
		for (int i = 0; i < chance.length; i++) {
			results[i+1] = chance[i];
		}
		results[6] = perfect;
		results[7] = almost;
		
		if (log) System.out.println("Jogada: " + round + "  Tentativa: " + Arrays.toString(chance) + "  Acertou: " + perfect + "  Quase: " + almost);

		// Condição de parada se ganhou
		if (perfect == 5) {
			System.out.println("Você ganhou!"  + "   Pontuação: " + winner + "  Round: " + round);
			finished = true;
		}
		
		// Condição de parada se perdeu
		// O máximo são 12 tentativas
		if (round >= 12) {
			//System.out.println("Perdeu! " + "   Pontuação: " + winner);
			finished = true;
		}
		
		return results;
	}

	@Override
	public Ecosystem getInstance() {
		return new SuperSenha();
	}

	@Override
	public int[] interaction(int[] inputs) {
		if (finished) return new int[96];
	
		// Transforma a decisão da RN na escolha de marcha. A senha de cinco digitos.
		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i] > 0) inputs[i] = 1;
		}
		
		int[] pass = new int[5];
		int j = 0;
		for (int i = 0; i < pass.length; i++) {
			pass[i] = inputs[j++] * 1 + inputs[j++] * 2 + inputs[j++] * 4 + 1;
		}
		// Executa o jogo e recebe o resultado
		int[] results = play(pass);
		
		return results;
	}

	@Override
	public boolean finished() {
		return finished;
	}

	@Override
	public int winner() {
		return winner;
	}
	
	public void logon() {
		System.out.println("Senha: " + Arrays.toString(pass));
		log = !log;
	}
}
