package com.atal.project.game.strategies.binaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.w3c.dom.Node;

import com.atal.project.game.Game;
import com.atal.project.game.Player;
import com.atal.project.game.map.GameMap;
import com.atal.project.game.map.Point;
import com.atal.project.game.map.TreasureChest;
import com.atal.project.game.strategies.Strategy;

public class BinaryTree implements Strategy {

    private NodeTree<Integer> root;
    private LinkedList<NodeTree<String>> sequenceSelected;

    public BinaryTree(GameMap map) {
        map.print();
        this.root = new NodeTree<>();
        this.sequenceSelected = new LinkedList<>();

        this.root = buildTree(map.getScenario(), 0, 0);
    }

    public void insert(Integer value) {
        if (this.root.getValue() == null) {
            this.root.setValue(value);
        } else {
            insertInTree(value, this.root);
        }
    }

    public void insertInTree(Integer value, NodeTree<Integer> node) {
        if (value < node.getValue()) {
            if (node.getLeft() == null) {
                NodeTree<Integer> newNode = new NodeTree<>();
                newNode.setValue(value);
                newNode.setFather(node);
                node.setLeft(newNode);
            } else {
                insertInTree(value, node.getLeft());
            }
        } else {
            if (node.getRight() == null) {
                NodeTree<Integer> newNode = new NodeTree<>();
                newNode.setValue(value);
                newNode.setFather(node);
                node.setRight(newNode);
            } else {
                insertInTree(value, node.getRight());
            }
        }
    }

    public boolean BFS(Integer value) {
        if (this.root == null || this.root.getValue() == null) {
            System.out.println("Árvore Vazia");
            return false;
        }
        Queue<NodeTree<Integer>> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            NodeTree<Integer> node = queue.poll();
            System.out.print(node.getValue() + "->");
            if (node.getValue().equals(value)) {
                return true;

            }

            if (node.getLeft() != null) {

                queue.add(node.getLeft());

            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

        }
        System.out.println("Valor não encontrado: " + value);
        return false;
    }

    public Integer min() {
        if (this.root == null || this.root.getValue() == null) {
            System.out.println("Árvore Vazia");
            return null;
        }
        return getMin(root);
    }

    public Integer getMin(NodeTree<Integer> node) {
        if (node.getLeft() == null) {
            return node.getValue();
        } else {
            return getMin(node.getLeft());

        }
    }

    public Integer max() {
        if (this.root == null || this.root.getValue() == null) {
            System.out.println("Árvore Vazia");
            return null;
        }
        return getMax(root);
    }

    public Integer getMax(NodeTree<Integer> node) {
        if (node.getRight() == null) {
            return node.getValue();
        } else {
            return getMax(node.getRight());

        }
    }

    public void DFS() {
        //preOrder(this.root);
        inOrder(this.root);
        //postOrder(this.root);
    }

    public void preOrder(NodeTree<Integer> node) {
        if (node != null && node.getValue() != null) {
            System.out.print(node.getValue() + " -> ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }

    }

    public void inOrder(NodeTree<Integer> node) {
        if (node != null && node.getValue() != null) {
            inOrder(node.getLeft());
            System.out.print(node.getValue() + " -> ");
            inOrder(node.getRight());
        }

    }

    public void postOrder(NodeTree<Integer> node) {
        if (node != null && node.getValue() != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getValue() + " -> ");
        }
    }

    public NodeTree<String> buildTree(String[][] map, int i, int j) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length) {
            return null;
        }

        if (map[i][j] == null || map[i][j].equals(Player.CHARACTER)) {
            NodeTree<String> newNode = new NodeTree<>();
            newNode.setValue(map[i][j]);
            newNode.setLeft(buildTree(map, i, j + 1));
            newNode.setRight(buildTree(map, i + 1, j));
            return newNode;
        }
        return new NodeTree<>();
    }

    public void biuldTreeAndCalculatePath(GameMap map, int i, int j) {
        this.root = buildTree(map.getScenario(), i, j);
        this.DFS(this.root);
    }

    public void DFS(NodeTree<String> node) {
        LinkedList<NodeTree<String>> path = new LinkedList<>();
        preOrder(node, TreasureChest.CHARACTER, path);
        this.sequenceSelected = path;
    }

    public boolean preOrder(NodeTree<String> node, String value, LinkedList<NodeTree<String>> path) {

        //raiz
        if (node == null) {
            return false;
        }

        path.add(node);

        if (node.getLeft() == null && node.getRight() == null || (node.getValue() != null && node.getValue().equals(value))) {
            return true;
        }

        if (preOrder(node.getLeft(), value, path) || preOrder(node.getRight(), value, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    @Override
    public Point evaluatePossbileNextStep(List<Point> possibleNextStep, GameMap map) {
        NodeTree<String> nextPoint = sequenceSelected.removeFirst();
        if(nextPoint != null){

        }
        if(nextPoint != null && nextPoint.getValue() != null){
            return new Point(nextPoint.getI(), nextPoint.getJ());
        }

    }

}
