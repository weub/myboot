package com.weub.myboot.service.tree;

import com.weub.myboot.model.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Traversal {

    /*public static void main(String[] args) {
        TreeNode<Integer> root = initTreeNode();
        preOrderRecursion(root);
    }*/

    public static TreeNode<Integer> initTreeNode() {
        TreeNode<Integer> root = new TreeNode<>(6);
        TreeNode<Integer> l2 = new TreeNode<>(2);
        TreeNode<Integer> l8 = new TreeNode<>(8);
        TreeNode<Integer> l0 = new TreeNode<>(0);
        TreeNode<Integer> l4 = new TreeNode<>(4);
        TreeNode<Integer> l9 = new TreeNode<>(9);
        TreeNode<Integer> l1 = new TreeNode<>(1);
        TreeNode<Integer> l5 = new TreeNode<>(5);

        root.setLeft(l2);
        root.setRight(l8);

        l2.setLeft(l0);
        l2.setRight(l4);

        l0.setRight(l1);

        l4.setRight(l5);

        l8.setRight(l9);
        return root;
    }

    public static void preOrderRecursion(TreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getVal());
        preOrderRecursion(node.getLeft());
        preOrderRecursion(node.getRight());
    }

    /**
     * 利用栈来实现二叉树的先序非递归遍历
     * @param root 二叉树根节点
     * @return
     */
    public static List<Integer> preorder(TreeNode<Integer> root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }

        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode<Integer> node = stack.pop();
            if (node != null) {
                stack.push(node.getRight());
                stack.push(node.getLeft());
                resultList.add(node.getVal());
            }
        }

        return resultList;
    }
}
