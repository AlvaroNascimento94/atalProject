package com.atal.project.game.strategies.binaryTree;

public class NodeTree<T> {

    private T value;
    private int i;
    private int j;
    private Object treasure;

    private NodeTree<T> left;
    private NodeTree<T> right;

    public NodeTree() {
    }

    public NodeTree(T value, int i, int j) {
        this.value = value;
        this.i = i;
        this.j = j;
    }

    public void setTreasure(Object treasure) {
        this.treasure = treasure;
    }

    public Object getTreasure() {
        return treasure;
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

    public boolean isNILL() {
        return this.getValue() == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            String value = (String) obj;
            return this.getValue().equals(value);
        }
        NodeTree<T> node = (NodeTree) obj;
        return this.getI() == node.getI() && this.getJ() == node.getJ();
    }
}
