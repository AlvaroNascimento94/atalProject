package com.atal.project.game.strategies;

import java.util.List;
import java.util.Random;

import com.atal.project.game.map.Map;
import com.atal.project.game.map.Point;

public class Sort implements Strategy {

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
        Random random = new Random();
        if (possibleNextSteps != null && !possibleNextSteps.isEmpty()) {

            while (!possibleNextSteps.isEmpty()) {
                int randomIndex = random.nextInt(possibleNextSteps.size());
                Point nextStep = possibleNextSteps.get(randomIndex);

                if (isValidStep(nextStep, map)) {
                    return nextStep;
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
}

