package com.infinity.javacollections.trees.binarytree;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        BinarySearchTree theTree = new BinarySearchTree();
        theTree.addRootNode(30);

        //Let's create a list with the elements to insert into the tree
        List<Integer> numbersToAdd = List.of(20,15,5,18, 25, 40, 35, 50, 45, 60);

        numbersToAdd.forEach(theTree::addTreeNode);

        System.out.println("**********************************");
        System.out.println("The InOrderTraversal result is: ");
        theTree.printInOrderTraversal();
        System.out.println("");

        System.out.println("**********************************");
        System.out.println("The PreOrderTraversal result is: ");
        theTree.printPreOrderTraversal();
        System.out.println("");

        System.out.println("**********************************");
        System.out.println("The PostOrderTraversal result is: ");
        theTree.printPostOrderTraversal();
        System.out.println("");

    }
}
