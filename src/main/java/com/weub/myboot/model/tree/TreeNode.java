package com.weub.myboot.model.tree;

/**
 * 二叉树节点
 */
public class TreeNode<T> {

    private T val;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(T val) {
        this.val = val;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public T getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "val: " + val;
    }
}
