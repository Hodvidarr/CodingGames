package com.hodvidar.formation.datastructures;

public class BinaryTreeNode {
    private Integer value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(final Integer value) {
        this.value = value;
    }

    public void insert(final Integer value) {
        if (value.compareTo(this.value) <= 0) {
            if (left == null) {
                left = new BinaryTreeNode(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new BinaryTreeNode(value);
            } else {
                right.insert(value);
            }
        }
    }

    public boolean contains(final Integer value) {
        if (value.equals(this.value)) {
            return true;
        }
        if (value.compareTo(this.value) < 0) {
            if (left == null) {
                return false;
            }
            return left.contains(value);
        }
        // value > this.value
        if (right == null) {
            return false;
        }
        return right.contains(value);
    }

    public int getMaxDepth() {
        if (left == null && right == null) {
            return 1;
        }
        final int leftDepth = left == null ? 0 : left.getMaxDepth();
        final int rightDepth = right == null ? 0 : right.getMaxDepth();
        return (1 + ((leftDepth > rightDepth) ? leftDepth : rightDepth));
    }

    public String toStringSimple() {
        return "{v=" + this.value + " Lv=" + this.left.getValue() + " Rv=" + this.right.getValue() + "}";
    }

    @Override
    public String toString() {
        return "v=" + this.value + " {L>" + this.left + " R>" + this.right + "}";
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(final BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(final BinaryTreeNode right) {
        this.right = right;
    }
}
