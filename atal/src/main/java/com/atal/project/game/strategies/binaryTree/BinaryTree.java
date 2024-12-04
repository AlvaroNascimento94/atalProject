package com.atal.project.game.strategies.binaryTree;

import java.util.LinkedList;
import java.util.List;

import com.atal.project.game.Player;
import com.atal.project.game.map.GameMap;
import com.atal.project.game.map.MapOfTreasure;
import com.atal.project.game.map.Monster;
import com.atal.project.game.map.Point;
import com.atal.project.game.map.TreasureChest;
import com.atal.project.game.strategies.Strategy;

public class BinaryTree implements Strategy {

    private NodeTree<String> root;
    private LinkedList<NodeTree<String>> sequenceSelected;

    public BinaryTree(GameMap map) {
        this.root = new NodeTree<>();
        this.sequenceSelected = new LinkedList<>();
        updateTree(map);
    }

    public void updateTree(GameMap map) {
        printMap(map.getScenario());
        this.root = buildTree(map.getScenario(), 0, 0);
    }

    public NodeTree<String> buildTree(String[][] map, int i, int j) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length) {
            return null;
        }

        if (map[i][j] == null) {
            return null;
        }

        if (map[i][j] != null && (map[i][j].equals("*")
                || map[i][j].equals(Player.CHARACTER)
                || map[i][j].equals(TreasureChest.CHARACTER)
                || map[i][j].equals(MapOfTreasure.CHARACTER)
                || map[i][j].equals(Monster.CHARACTER))) {
            NodeTree<String> newNode = new NodeTree<>(map[i][j], i, j);
            newNode.setLeft(buildTree(map, i, j + 1));
            newNode.setRight(buildTree(map, i + 1, j));
            return newNode;
        }

        return null;
    }

    public void DFS(NodeTree<String> node) {
        LinkedList<NodeTree<String>> path = new LinkedList<>();

        if (!preOrder(node, MapOfTreasure.CHARACTER, path)) {
            printTree(this.root);
            return;
        }

        NodeTree<String> mapOfTreasure = path.getLast();
        path = new LinkedList<>();

        if (!preOrder(node, mapOfTreasure, path)) {
            printTree(this.root);
            return;
        }

        this.sequenceSelected = path;
        if (!sequenceSelected.isEmpty()) {
            sequenceSelected.removeFirst();
        }
    }

    public boolean preOrder(NodeTree<String> node, Object value, LinkedList<NodeTree<String>> path) {
        if (node == null || node.getValue() == null) {
            return false;
        }

        path.add(node);
        if (node.getValue().equals(value)) {
            return true;
        }

        if (preOrder(node.getLeft(), value, path) || preOrder(node.getRight(), value, path)) {
            return true;
        }

        path.removeLast();
        return false;
    }

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextStep, GameMap map) {
        updateTree(map);

        DFS(this.root);

        if (!this.sequenceSelected.isEmpty()) {
            NodeTree<String> nextNode = this.sequenceSelected.getFirst();
            return new Point(nextNode.getI(), nextNode.getJ());
        }

        return possibleNextStep.isEmpty() ? null : possibleNextStep.get(0);
    }

    public void printMap(String[][] map) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                System.out.print((map[i][j] == null ? "." : map[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public void printTree(NodeTree<String> node) {
        if (node == null) {
            return;
        }
        printTree(node.getLeft());
        printTree(node.getRight());
    }
}
