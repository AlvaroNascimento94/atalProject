package com.atal.project.game.strategies;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.atal.project.game.map.GameMap;
import com.atal.project.game.map.Point;

public class Voting implements Strategy {
    
    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextStep, GameMap map) {
        Sort sort = new Sort();
        FewerObstacles fewerObstacles = new FewerObstacles();
        ShortestDistance shortestDistance = new ShortestDistance();

        Point sortPoint = sort.evaluatePossbileNextStep(possibleNextStep, map);
        Point shortestDistancePoint = shortestDistance.evaluatePossbileNextStep(possibleNextStep, map);
        Point fewerObstaclesPoint = fewerObstacles.evaluatePossbileNextStep(possibleNextStep, map);

        

        List<Point> points = new LinkedList<>();
        points.add(sortPoint);
        points.add(shortestDistancePoint);
        points.add(fewerObstaclesPoint);
        
        Map<Point, Integer> voting = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if(voting.get(p)==null){
                voting.put((p), 1);
            }
            else{
                voting.put(p, voting.get(p)+1);
            }
        }
        return  getMostVotedPoint(voting);
    }
    private Point getMostVotedPoint(Map<Point, Integer> voting) {
		Integer biggestValue = Integer.MIN_VALUE;
		Point point = null;
		for (Entry<Point, Integer> entry : voting.entrySet()) {
            if (entry.getValue() > biggestValue && entry.getKey() != null) {
                biggestValue = entry.getValue();
                point = entry.getKey();
            }else{
                
            }
        }
		return point;
        
	}
}
