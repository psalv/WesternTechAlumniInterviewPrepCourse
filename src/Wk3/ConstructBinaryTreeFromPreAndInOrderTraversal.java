package Wk3;

import java.util.HashSet;
import java.util.Stack;

class TreeNode4 {
    int val;
    TreeNode4 left;
    TreeNode4 right;
    TreeNode4(int x) {
        val = x;
    }
    public String toString(){
        return Integer.toString(val);
    }
}


public class ConstructBinaryTreeFromPreAndInOrderTraversal {

    private Stack<TreeNode4> nodes = new Stack<>();
    private TreeNode4 root;

    private HashSet<Integer> seen = new HashSet<>();


    private int posPre = 0;
    private int posIn = 0;


    public TreeNode4 buildTree(int[] preorder, int[] inorder){

        // When we reach the end of the pre order list we are finished.
        while(posPre < preorder.length) {

            // This is how we go left, all left nodes we need to add will occur in descending order down the tree
            // in the preorder. We know we have reached a left when the next available inorder position matches.
            while (!seen.contains(inorder[posIn])) {

                // The first node of the preorder will be the root, this is what we return.
                if(root == null){
                    root = new TreeNode4(preorder[posPre++]);
                    nodes.push(root);
                    seen.add(root.val);
                }

                // We set the left child of the current top of the stack (our current bottom of the tree were are constructing)
                // We add this node to the stack (making it the new bottom), and add it to the nodes we have seen.
                else {
                    nodes.peek().left = new TreeNode4(preorder[posPre++]);
                    nodes.push(nodes.peek().left);
                    seen.add(nodes.peek().val);
                }
            }

            ++posIn;

            // If the current inorder position has already been seen then we want it at the top of the stack.
            while(seen.contains(inorder[posIn]) && nodes.peek().val != inorder[posIn]){
                nodes.pop();
            }

            // If the current inorder position has not yet been seen, then we move to the right.
            if(!seen.contains(inorder[posIn])) {
                nodes.peek().right = new TreeNode4(preorder[posPre++]);
                nodes.push(nodes.peek().right);
                seen.add(nodes.peek().val);
            }
        }
        return root;
    }

    public String toString(){
        String toReturn = "";
        Stack<TreeNode4> all = new Stack<>();
        all.push(root);
        while(!all.isEmpty()){
            TreeNode4 cur = all.pop();
            if(cur.left != null){
                toReturn += cur.val + " -> " + cur.left.val + "\n";
                all.push(cur.left);
            }
            if(cur.right != null){
                toReturn += cur.val + " -> " + cur.right.val + "\n";
                all.push(cur.right);
            }
        }
        return toReturn;
    }

    public void reset(){
        root = null;
        nodes = new Stack<>();
        seen = new HashSet<>();
        posIn = 0;
        posPre = 0;
    }

    public static void main(String[] args) {
        int[] preorder1 = {7, 2, 1, 4, 3, 5, 6, 8, 10, 9};
        int[] inorder1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] preorder2 = {7, 3, 2, 8, 9, 1};
        int[] inorder2 = {2, 3, 7, 8, 1, 9};

        ConstructBinaryTreeFromPreAndInOrderTraversal test = new ConstructBinaryTreeFromPreAndInOrderTraversal();
        test.buildTree(preorder1, inorder1);

        System.out.println(test);

        test.reset();

        test.buildTree(preorder2, inorder2);
        System.out.println(test);
    }
}
