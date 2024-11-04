package com.atal.project.game;

import com.atal.project.game.map.Map;
import com.atal.project.game.map.Point;
import com.atal.project.game.map.TreasureChest;
import com.atal.project.game.strategies.FewerObstacles;


public class Game {
	
	private Map map;
	private Player player;
	private static double errorMap;
    private static double sucess;
    private static double empty;
    private static double trap;
    
	public Game() {
		this.map = new Map(8, 8);
		//this.player = new Player(new FewerObstaclesAndShorterDistance());
		this.player = new Player(new FewerObstacles());
		//this.player = new Player(new Sort());
		//this.player = new Player(new ShortestDistance());
	}
	
	public void run() {
		
		this.map.print();
		System.out.println();
		while(true) {
			Point nextPoint = this.player.evaluatePossbileNextStep(map);
			if (nextPoint == null) {
				System.out.println("Sem jogadas válidas");
				errorMap++;
				break;
			} else {
				String space = this.map.get(nextPoint);
				if (space != null && space.equals(TreasureChest.CHARACTER)) {
					this.map.openTreasureChest(nextPoint);
					this.map.print();
					if (this.map.get(nextPoint).equals(TreasureChest.CHEST_TRESURE_CHARACTER)) {
                        sucess++;
                    } else if (this.map.get(nextPoint).equals(TreasureChest.CHEST_TRAP_CHARACTER)) {
                        trap++;
                    } else if (this.map.get(nextPoint).equals(TreasureChest.CHEST_EMPTY_CHARACTER)) {
                        empty++;
                    }
					break;
				} else {
					this.map.moveRobot(nextPoint);
				}
			}
			this.map.print();
			System.out.println();
		}
	}
	
	public void testGame(int totalRuns) {
		 System.out.println("Resultados em " + totalRuns + " execuções:");
	     System.out.printf("Sucesso: %.2f%% \n", ((double) sucess / totalRuns) * 100);
	     System.out.printf("Tesouro vazio: %.2f%% \n", ((double) empty / totalRuns) * 100);
	     System.out.printf("Armadilha: %.2f%% \n", ((double) trap / totalRuns) * 100);
	     System.out.printf("Sem jogadas válidas: %.2f%% \n", ((double) errorMap / totalRuns) * 100);
	}
	
}