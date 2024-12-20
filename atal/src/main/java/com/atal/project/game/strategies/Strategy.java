package com.atal.project.game.strategies;

import java.util.List;

import com.atal.project.game.map.GameMap;
import com.atal.project.game.map.Point;

public interface Strategy {
	public Point evaluatePossbileNextStep(List<Point> possibleNextStep, GameMap map);
}
