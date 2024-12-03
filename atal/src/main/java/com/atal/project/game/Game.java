package com.atal.project.game;

import com.atal.project.game.map.GameMap;
import com.atal.project.game.map.Point;
import com.atal.project.game.map.TreasureChest;
import com.atal.project.game.strategies.Voting;


public class Game {
	
	private final GameMap map;
	private final Player player;
	private static double errorMap;
    private static double sucess;
    private static double empty;
    private static double trap;
    
	public Game() {
		this.map = new GameMap(8, 8);
		//this.player = new Player(new FewerObstaclesAndShorterDistance());
		//this.player = new Player(new FewerObstacles());
		//this.player = new Player(new Sort());
		//this.player = new Player(new ShortestDistance());
		this.player = new Player(new Voting());
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
					switch (this.map.get(nextPoint)) {
						case TreasureChest.CHEST_TRESURE_CHARACTER -> sucess++;
						case TreasureChest.CHEST_TRAP_CHARACTER -> trap++;
						case TreasureChest.CHEST_EMPTY_CHARACTER -> empty++;
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