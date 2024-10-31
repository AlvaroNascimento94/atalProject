package com.atal.project.game;

import com.atal.project.game.map.Map;
import com.atal.project.game.map.Point;
import com.atal.project.game.map.TreasureChest;
import com.atal.project.game.strategies.Teste;

public class Game {
	private Map map;
	private Player player;
	public Game() {
		this.map = new Map(8, 8);
		//this.player = new Player(new FewerObstaclesAndShorterDistance());
		//this.player = new Player(new FewerObstacles());
		//this.player = new Player(new Sort());
		//this.player = new Player(new ShortestDistance());
		this.player = new Player(new Teste());
	}
	
	public void run() {
		this.map.print();
		System.out.println();
		while(true) {
			Point nextPoint = this.player.evaluatePossbileNextStep(map);
			if (nextPoint == null) {
				break;
			} else {
				String space = this.map.get(nextPoint);
				if (space != null && space.equals(TreasureChest.CHARACTER)) {
					this.map.openTreasureChest(nextPoint);
					this.map.print();
					break;
				} else {
					this.map.moveRobot(nextPoint);
				}
			}
			this.map.print();
			System.out.println();
		}
	}

}
