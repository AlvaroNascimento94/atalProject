package com.atal;

import com.atal.project.game.Game;

public class Main {
  public static void main(String[] args) {
		
		int totalRuns = 100;
		
		for(int i = 0; i < totalRuns; i ++) {
			Game g = new Game();
			g.run();
			if(i == 99) {
				g.testGame(totalRuns);
			}
		}
	}
}

