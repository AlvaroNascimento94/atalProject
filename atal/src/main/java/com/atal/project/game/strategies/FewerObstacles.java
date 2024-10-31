package com.atal.project.game.strategies;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.atal.project.game.map.Map;
import com.atal.project.game.map.Point;

public class FewerObstacles implements Strategy {

	private Set<Point> visitedPoints = new HashSet<>();

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
		if (possibleNextSteps.isEmpty()) {
			return null; // Não há próximo passo possível
		}

		Point bestNextStep = null;
		int minObstaclesAroundTreasure = Integer.MAX_VALUE;

		// Loop através de todos os próximos passos possíveis
		for (Point nextStep : possibleNextSteps) {
			// Ignora pontos já visitados ou que são obstáculos
			if (visitedPoints.contains(nextStep) || isObstacle(nextStep, map)) {
				continue;
			}

			// Encontra o tesouro mais próximo usando busca por amplitude (BFS)
			Point nearestTreasure = findNearestTreasureBFS(nextStep, map);
			if (nearestTreasure == null) {
				continue; // Ignora este ponto se não houver tesouro acessível no mapa
			}

			int obstaclesAroundTreasure = countObstaclesAround(nearestTreasure, map);

			// Se encontrar um ponto que leva a um tesouro com menos obstáculos, atualiza
			// bestNextStep
			if (obstaclesAroundTreasure < minObstaclesAroundTreasure) {
				minObstaclesAroundTreasure = obstaclesAroundTreasure;
				bestNextStep = nextStep;
			}
		}

		// Marca o melhor próximo passo como visitado
		if (bestNextStep != null) {
			visitedPoints.add(bestNextStep);
		}

		return bestNextStep; // Retorna o ponto que leva ao tesouro com menos obstáculos ao redor
	}

	private int countObstaclesAround(Point point, Map map) {
		int obstacles = 0;
		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // Cima, baixo, esquerda, direita

		// Verifica cada direção ao redor do ponto
		for (int[] dir : directions) {
			Point neighbor = new Point(point.getPositionX() + dir[0], point.getPositionY() + dir[1]);

			// Conta como obstáculo se o vizinho é válido e contém um obstáculo
			if (isValidPoint(neighbor, map) && isObstacle(neighbor, map)) {
				obstacles++;
			}
		}

		return obstacles; // Retorna o total de obstáculos ao redor do ponto
	}

	private boolean isValidPoint(Point point, Map map) {
		int[] scenarioSize = map.getScenarioSize();
		return point.getPositionX() >= 0 && point.getPositionY() >= 0 && point.getPositionX() < scenarioSize[0]
				&& point.getPositionY() < scenarioSize[1];
	}

	private boolean isObstacle(Point point, Map map) {
		String cell = map.get(point);
		return cell != null && (cell.equals("R") || cell.equals("M"));
	}

	private boolean isTreasure(Point point, Map map) {
		String cell = map.get(point);
		return cell != null && cell.equals("T"); // "T" representa o tesouro
	}

	private Point findNearestTreasureBFS(Point start, Map map) {
		Queue<Point> queue = new LinkedList<>();
		Set<Point> visited = new HashSet<>();

		queue.add(start);
		visited.add(start);

		while (!queue.isEmpty()) {
			Point current = queue.poll();

			// Retorna o ponto se for um tesouro
			if (isTreasure(current, map)) {
				return current;
			}

			// Adiciona os vizinhos válidos, não visitados e que não são obstáculos à fila
			int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
			for (int[] dir : directions) {
				Point neighbor = new Point(current.getPositionX() + dir[0], current.getPositionY() + dir[1]);
				if (isValidPoint(neighbor, map) && !visited.contains(neighbor) && !isObstacle(neighbor, map)) {
					visited.add(neighbor);
					queue.add(neighbor);
				}
			}
		}
		return null; // Retorna null se não houver tesouro acessível no mapa
	}
}