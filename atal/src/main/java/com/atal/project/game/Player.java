package com.atal.project.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.atal.project.game.map.GameMap;
import com.atal.project.game.map.Point;
import com.atal.project.game.strategies.Strategy;

public class Player {
	public static final String CHARACTER = "W";
	private final Strategy strategy;
	public Player(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public Point evaluatePossbileNextStep(GameMap map) {
		Point robotLocation = map.getRobotLocation();
		List<Point> possibleNextSteps = new ArrayList<>();
		possibleNextSteps.add(new Point(robotLocation.getPositionX(), robotLocation.getPositionY() + 1));
		possibleNextSteps.add(new Point(robotLocation.getPositionX() + 1, robotLocation.getPositionY()));
		possibleNextSteps.add(new Point(robotLocation.getPositionX() - 1, robotLocation.getPositionY()));
		possibleNextSteps.add(new Point(robotLocation.getPositionX(), robotLocation.getPositionY() - 1));
		
		List<Point> result = new LinkedList<>();
		// Filter impossible next steps
		for (int i = 0; i < possibleNextSteps.size(); i++) {
			Point p = possibleNextSteps.get(i);
			int[] scenarioSize = map.getScenarioSize();
			if (p.getPositionX() >= 0 && p.getPositionY() >= 0 &&
					p.getPositionX() < scenarioSize[0] && p.getPositionY() < scenarioSize[1]) {
				result.add(p);
			}
		}
		return this.strategy.evaluatePossbileNextStep(result, map);
	}

}
