package com.atal;

import com.atal.project.game.Game;

public class Main {
  public static void main(String[] args) {
		System.out.println("");
		int totalRuns = 100;
		
		for(int i = 0; i < totalRuns; i ++) {
			Game g = new Game();
			g.run();
			if(i == totalRuns - 1) {
				g.testGame(totalRuns);
			}
		}
	}
}

