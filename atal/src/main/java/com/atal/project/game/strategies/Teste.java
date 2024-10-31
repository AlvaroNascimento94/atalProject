package com.atal.project.game.strategies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.atal.project.game.map.Map;
import com.atal.project.game.map.Monster;
import com.atal.project.game.map.Point;
import com.atal.project.game.map.Rock;
import com.atal.project.game.map.TreasureChest;

public class Teste implements Strategy {

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
        Iterator<Point> it = possibleNextStep.iterator();
        int min = Integer.MAX_VALUE;
        Point pointSelected = null;
        while (it.hasNext()) {
            Point p = it.next();
            int count = evaluatePoint(p, map);
            if(min > count){
                min = count;
                pointSelected = p;
            }
        }
        return pointSelected;
    }

    private int evaluatePoint(Point p, Map map){
        int count = 0;
        List<Point> points = new ArrayList<>();
        points.add(new Point(p.getPositionX() -1 , p.getPositionY() - 1));
        points.add(new Point(p.getPositionX() -1 , p.getPositionY()));
        points.add(new Point(p.getPositionX() -1 , p.getPositionY() + 1));
        points.add(new Point(p.getPositionX() , p.getPositionY() - 1));
        points.add(new Point(p.getPositionX() , p.getPositionY() + 1));
        points.add(new Point(p.getPositionX() + 1 , p.getPositionY() - 1));
        points.add(new Point(p.getPositionX() + 1 , p.getPositionY()));
        points.add(new Point(p.getPositionX() + 1 , p.getPositionY() + 1));
        for(int i = 0; i<points.size()-1; i++){
            Point curretPoint = points.get(i);
            int [] scenarioSize = map.getScenarioSize();
            if(
                (curretPoint.getPositionX() < 0 || curretPoint.getPositionX() >= scenarioSize[0]) ||
                (curretPoint.getPositionY() < 0 || curretPoint.getPositionY() >= scenarioSize[1])
            ){
                continue;
            }else{
                if(map.get(curretPoint).equals(Rock.CHARACTER) || map.get(curretPoint).equals(Monster.CHARACTER)){
                    count ++;
                } else{
                    if(map.get(curretPoint).equals(TreasureChest.CHARACTER)){
                        count = 0;
                    }
                }
            }
        }
        return count;
    }

}
