package com.atal.project.game.strategies.binaryTree;

public class NodeTree <T extends  Object> {

    private T value;
    private NodeTree<T> left;
    private NodeTree<T> right;
    private NodeTree<T> father;
    private NodeTree<T> i;
    private NodeTree<T> j;

    public NodeTree<T> getI() {
        return i;
    }

    public void setI(NodeTree<T> i) {
        this.i = i;
    }

    public NodeTree<T> getJ() {
        return j;
    }

    public void setJ(NodeTree<T> j) {
        this.j = j;
    }

    public NodeTree() {
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

    
}
