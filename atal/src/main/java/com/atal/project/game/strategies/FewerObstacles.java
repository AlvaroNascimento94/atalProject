package com.atal.project.game.strategies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.atal.project.game.map.GameMap;
import com.atal.project.game.map.Monster;
import com.atal.project.game.map.Point;
import com.atal.project.game.map.Rock;
import com.atal.project.game.map.TreasureChest;

public class FewerObstacles implements Strategy {

	private final List<Point> pointsObstacles = new ArrayList<>();
	private final List<Point> pointTreasure = new ArrayList<>();

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, GameMap map) {
		pointsObstacles.add(new Point(0, 0));
		Iterator<Point> it = possibleNextSteps.iterator();
		int min = Integer.MAX_VALUE;
		Point pointSelected = null;
		while (it.hasNext()) {
			Point p = it.next();
			int count = evaluatePoint(p, map);
			if(isNotValidPoint(p)) {
				continue;
			} else if (isPointTreasure(p)){
				pointSelected = p;
				break;
			} else if (isPointBack(p, map)) {
				continue;
			} else if (count <= min) {
				min = count;
				pointSelected = p;
			}
		}
		
		pointsObstacles.add(pointSelected);
		return pointSelected;

	}

	public int evaluatePoint(Point possibleNextSteps, GameMap map) {
		int count = 0;
		List<Point> points = new ArrayList<>();
		
		//Adicionando as Possíveis jogadas ao redor
		points.add(new Point(possibleNextSteps.getPositionX() - 1, possibleNextSteps.getPositionY() - 1));
		points.add(new Point(possibleNextSteps.getPositionX() - 1, possibleNextSteps.getPositionY()));
		points.add(new Point(possibleNextSteps.getPositionX() - 1, possibleNextSteps.getPositionY() + 1));
		points.add(new Point(possibleNextSteps.getPositionX(), possibleNextSteps.getPositionY() - 1));
		points.add(new Point(possibleNextSteps.getPositionX(), possibleNextSteps.getPositionY() + 1));
		points.add(new Point(possibleNextSteps.getPositionX() + 1, possibleNextSteps.getPositionY() - 1));
		points.add(new Point(possibleNextSteps.getPositionX() + 1, possibleNextSteps.getPositionY()));
		points.add(new Point(possibleNextSteps.getPositionX() + 1, possibleNextSteps.getPositionY() + 1));
		
		if (map.get(possibleNextSteps).equals(TreasureChest.CHARACTER)) {
			count = Integer.MIN_VALUE;
			return count;
		}
		
		for (int i = 0; i < points.size(); i++) {
			Point currentPoint = points.get(i);
			int[] scenarioSize = map.getScenarioSize();
			if (currentPoint.getPositionX() < 0 || currentPoint.getPositionX() >= scenarioSize[0]
					|| currentPoint.getPositionY() < 0 || currentPoint.getPositionY() >= scenarioSize[1]) {
				continue;
			} else if (map.get(currentPoint).equals(Rock.CHARACTER) || map.get(currentPoint).equals(Monster.CHARACTER)) {
				pointsObstacles.add(currentPoint);
				count++;
			} else if (map.get(currentPoint).equals(TreasureChest.CHARACTER)) {
				pointTreasure.add(currentPoint);
				count--;
			} 
		}
		
		return count;
	}

	public boolean isNotValidPoint(Point p) {
		boolean validPoint = false;
		for (Point i : pointsObstacles) {
			if (i.getPositionX() == p.getPositionX() && i.getPositionY() == p.getPositionY()) {
				validPoint = true;
				break;
			} else {
				validPoint = false;
			}
		}
		return validPoint;
	}
	
	public boolean isPointTreasure(Point p) {
		boolean validPoint = false;
		for (Point i : pointTreasure) {
			if (i.getPositionX() == i.getPositionX() && i.getPositionY() == i.getPositionY()) {
				validPoint = true;
				break;
			} else {
			validPoint = false;
			}
		}
		return validPoint;
	}
	
	public boolean isPointBack(Point p, GameMap map) {
		boolean validPoint = false;
		Point robotPosition = map.getRobotLocation();
		if(p.getPositionY() < robotPosition.getPositionY()) {
			validPoint = true;
		} else {
			validPoint = false;
		}
		
	return validPoint;
	}
	
}
