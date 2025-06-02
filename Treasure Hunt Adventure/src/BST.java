/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package treasurehuntadventuregame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yakup
 */
class BSTNode {

    String username;
    int level;
    int score;
    BSTNode left = null;
    BSTNode right = null;

    public BSTNode(String username, int level, int score) {
        this.username = username;
        this.level = level;
        this.score = score;
    }
}

public class BozdemirMavlyudovBST {

    BSTNode root;

    public BozdemirMavlyudovBST() {
        root = null;
    }

    public void insert(String username, int level, int score) {
        root = insertRec(root, new BSTNode(username, level, score));
    }

    private BSTNode insertRec(BSTNode root, BSTNode newNode) {
        if (root == null) {
            return newNode;
        }

        if (newNode.score < root.score) {
            root.left = insertRec(root.left, newNode);
        } else {
            root.right = insertRec(root.right, newNode);
        }

        return root;
    }

    public void inOrderTraversal() {
        inOrderRec(root);
    }

    public void preOrderTraversal() {
        preOrderRec(root);
    }

    private void preOrderRec(BSTNode node) {
        if (node != null) {
            System.out.println("Username: " + node.username + ", Level: " + node.level + ", Score: " + node.score);
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    public void postOrderTraversal() {
        postOrderRec(root);
    }

    private void postOrderRec(BSTNode node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.println("Username: " + node.username + ", Level: " + node.level + ", Score: " + node.score);
        }
    }

    private void inOrderRec(BSTNode node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println("Username: " + node.username + ", Level: " + node.level + ", Score: " + node.score);
            inOrderRec(node.right);
        }
    }

    public BSTNode findMaxScore() {
        BSTNode current = root;
        if (current == null) {
            return null;
        }
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    public BSTNode findMinScore() {
        BSTNode current = root;
        if (current == null) {
            return null;
        }
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public List<BSTNode> inOrderTraversalToList() {
        List<BSTNode> nodeList = new ArrayList<>();
        inOrderToList(root, nodeList);
        return nodeList;
    }

    private void inOrderToList(BSTNode node, List<BSTNode> list) {
        if (node != null) {
            inOrderToList(node.left, list);
            list.add(node);
            inOrderToList(node.right, list);
        }
    }

}
