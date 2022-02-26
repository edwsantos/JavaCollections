package com.infinity.javacollections.trees.binarytree;

import java.util.Optional;

import static java.util.Objects.isNull;

public class BinarySearchTree {
    private TreeNode rootNode;

    public BinarySearchTree() {
        this.rootNode = null;
    }

    public void addRootNode(int value) {
        this.rootNode = new TreeNode(value);
    }

    public void addTreeNode(int value) {
        addTreeNode(this.rootNode, value);
    }

    private TreeNode addTreeNode(TreeNode currentNode, int value) {
        if (isNull(currentNode)) {
            return new TreeNode(value);
        }
        if (value < currentNode.getValue()) {
            currentNode.setLeftChild(addTreeNode(currentNode.getLeftChild(), value));
        } else {
            currentNode.setRightChild(addTreeNode(currentNode.getRightChild(), value));
        }
        return currentNode;
    }

    /*
    InOrder Traversal traverse the BinarySearchTree in order, left subtree => RootNode => Right subtree
    * */
    public void printInOrderTraversal() {
        Optional.ofNullable(this.rootNode)
                .ifPresentOrElse(this::printInOrderTraversal,
                        () -> System.out.println("The BinarySearchTree is empty!"));
    }

    private void printInOrderTraversal(TreeNode currentNode){
        if(!isNull(currentNode.getLeftChild())){
            printInOrderTraversal(currentNode.getLeftChild());
            System.out.print(currentNode.getValue() + " ");
            printInOrderTraversal(currentNode.getRightChild());
        } else{
            System.out.print(currentNode.getValue() + " ");
        }
    }
}
