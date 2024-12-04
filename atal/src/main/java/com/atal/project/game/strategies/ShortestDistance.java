package com.atal.project.game.strategies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.atal.project.game.map.GameMap;
import com.atal.project.game.map.Point;

public class ShortestDistance implements Strategy {

    private Point currentPosition = new Point(0, 0);
    private List<Point> pointsVisited = new ArrayList<>();

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, GameMap map) {
        Point treasure = findClosestTreasure(map);
        if (treasure == null) {
            return null;
        }

        List<Point> path = findShortestPath(map, currentPosition, treasure);
        if (path == null || path.isEmpty()) {
            return null;
        }

        currentPosition = path.get(0);

        if (isCountVisitedPoint(currentPosition)) {
            return null;
        }
        pointsVisited.add(currentPosition);
        return currentPosition;
    }

    private Point findClosestTreasure(GameMap map) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = createVisitedGrid(map);
        queue.offer(currentPosition);
        visited[currentPosition.getPositionX()][currentPosition.getPositionY()] = true;

        int[][] directions = {{1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if ("T".equals(map.get(current))) {
                return current;
            }
            addNeighbors(map, current, directions, queue, visited);
        }
        return null;
    }

    private List<Point> findShortestPath(GameMap map, Point start, Point target) {
        boolean[][] visited = createVisitedGrid(map);
        Point[][] parent = new Point[map.getScenarioSize()[0]][map.getScenarioSize()[1]];
        Queue<Point> queue = new LinkedList<>();

        queue.offer(start);
        visited[start.getPositionX()][start.getPositionY()] = true;

        int[][] directions = {{1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(target)) {
                return reconstructPath(parent, target);
            }
            addNeighbors(map, current, directions, queue, visited, parent);
        }
        return null;
    }

    private void addNeighbors(GameMap map, Point current, int[][] directions, Queue<Point> queue, boolean[][] visited) {
        addNeighbors(map, current, directions, queue, visited, null);
    }

    private void addNeighbors(GameMap map, Point current, int[][] directions, Queue<Point> queue, boolean[][] visited, Point[][] parent) {
        for (int[] dir : directions) {
            Point neighbor = new Point(current.getPositionX() + dir[0], current.getPositionY() + dir[1]);
            if (isValidPoint(neighbor, map) && !visited[neighbor.getPositionX()][neighbor.getPositionY()] && !isObstacle(neighbor, map)) {
                queue.offer(neighbor);
                visited[neighbor.getPositionX()][neighbor.getPositionY()] = true;
                if (parent != null) {
                    parent[neighbor.getPositionX()][neighbor.getPositionY()] = current;
                }
            }
        }
    }

    private List<Point> reconstructPath(Point[][] parent, Point target) {
        List<Point> path = new LinkedList<>();
        for (Point current = target; current != null; current = parent[current.getPositionX()][current.getPositionY()]) {
            path.add(0, current);
        }
        if (!path.isEmpty()) {
            path.remove(0);
        }
        return path;
    }

    private boolean isValidPoint(Point point, GameMap map) {
        int[] size = map.getScenarioSize();
        return point.getPositionX() >= 0 && point.getPositionY() >= 0
                && point.getPositionX() < size[0] && point.getPositionY() < size[1];
    }

    private boolean isObstacle(Point point, GameMap map) {
        String cell = map.get(point);
        return cell != null && (cell.equals("R") || cell.equals("M") || cell.equals("B"));
    }

    private boolean[][] createVisitedGrid(GameMap map) {
        int[] size = map.getScenarioSize();
        return new boolean[size[0]][size[1]];
    }

    private boolean isCountVisitedPoint(Point point) {
        int count = 0;
        for (Point p : pointsVisited) {
            if (p.getPositionX() == point.getPositionX() || p.getPositionY() == point.getPositionY()) {
                count++;
            }
        }
        if (count <= 25) {
            return false;
        }
        return true;
    }
}
