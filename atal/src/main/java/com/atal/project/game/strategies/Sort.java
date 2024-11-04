package com.atal.project.game.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.atal.project.game.map.Map;
import com.atal.project.game.map.Point;

public class Sort implements Strategy {

    private List<Point> pointsVisited = new ArrayList<>();

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
        Random random = new Random();
        if (possibleNextSteps != null && !possibleNextSteps.isEmpty()) {

            while (!possibleNextSteps.isEmpty()) {
                int randomIndex = random.nextInt(possibleNextSteps.size());
                Point nextStep = possibleNextSteps.get(randomIndex);

                if (isValidStep(nextStep, map)) {
                    pointsVisited.add(nextStep);
                    return nextStep;
                } else if (isValidMoviment(nextStep)) {
                    return null;
                } else {
                    possibleNextSteps.remove(randomIndex);
                }
            }
        }
        return null;
    }

    private boolean isValidStep(Point point, Map map) {
        int x = point.getPositionX();
        int y = point.getPositionY();
        int[] scenarioSize = map.getScenarioSize();
        return x >= 0 && x < scenarioSize[0] && y >= 0 && y < scenarioSize[1] && !isObstacle(point, map);
    }

    private boolean isObstacle(Point point, Map map) {
        String cell = map.get(point);
        return cell != null && (cell.equals("R") || cell.equals("M"));
    }

    //Verifica se ele já passou por aquele ponto N vezes
    private boolean isValidMoviment(Point p) {
        int count = 0;
        boolean isValidPoint = false;
        for (Point point : pointsVisited) {
            if (point.getPositionX() == p.getPositionX() || point.getPositionY() == p.getPositionY()) {
                count++;
            }
        }
        if (count > 50) {
            isValidPoint = true;
        }
        return isValidPoint;
    }
}
