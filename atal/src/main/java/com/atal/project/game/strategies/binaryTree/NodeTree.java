package com.atal.project.game.strategies.binaryTree;

public class NodeTree<T extends Object> {

    private T value;
    private NodeTree<T> left;
    private int i;
    private int j;
    
    private NodeTree<T> right;
    private NodeTree<T> father;

    private NodeTree<T> treasureLocalization;

    public NodeTree() {
    }

    public NodeTree(T value, int i, int j) {
        this.value = value;
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodeTree<T> getLeft() {
        return left;
    }

    public void setLeft(NodeTree<T> left) {
        this.left = left;
    }

    public NodeTree<T> getRight() {
        return right;
    }

    public void setRight(NodeTree<T> right) {
        this.right = right;
    }

    public NodeTree<T> getFather() {
        return father;
    }

    public void setFather(NodeTree<T> father) {
        this.father = father;
    }

    public boolean isNILL() {
        return this.value == null;
    }
}
