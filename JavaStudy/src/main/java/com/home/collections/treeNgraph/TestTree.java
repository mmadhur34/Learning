package com.home.collections.treeNgraph;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestTree {
    public static Logger log = Logger.getLogger(TestTree.class);
    @Test
    public void testBalanced(){
        BinarySearchTree<Character> bst= new BinarySearchTree<>('M');
        bst.addElement('M');
        bst.addElement('A');
        bst.addElement('D');
        bst.addElement('H');
        bst.addElement('U');
        bst.addElement('R');

        log.info("BST now is " + bst);
        log.info("Is BST tree balanced "+TreeNode.isBalanced(bst.getRoot()));

        BinarySearchTree<Integer> bt= new BinarySearchTree<>(11);
        bt.addElement(5);
        bt.addElement(2);
        bt.addElement(7);
        bt.addElement(19);
        bt.addElement(14);
        bt.addElement(26);

        log.info("BT now is " + bt);
        log.info("Is BT tree balanced "+TreeNode.isBalanced(bt.getRoot()));
    }
    @Test
    public void testLevelOrder(){
        BinarySearchTree<Integer> bt= new BinarySearchTree<>(11);
        bt.addElement(5);
        bt.addElement(2);
        bt.addElement(7);
        bt.addElement(19);
        bt.addElement(14);
        bt.addElement(26);

        bt.printLevelOrder(bt.root);
        System.out.println("");
        bt= new BinarySearchTree<>(1);
        bt.addElement(6);
        bt.addElement(2);
        bt.addElement(7);
        bt.addElement(2);
        bt.addElement(3);
        bt.addElement(4);
        bt.addElement(5);
        bt.printLevelOrder(bt.root);


    }
    public void testCheckBST(){

    }
}
