package Wk3;


import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

class TreeNode3 {
    int val;
    TreeNode3 left;
    TreeNode3 right;
    TreeNode3(int x) {
        val = x;
    }
    public String toString(){
        return Integer.toString(val);
    }
}


public class ValidateBST {


    /**
     * Checks the representation invariant (RI) of the a BST.
     *
     * @param node the current node that we are checking the RI of.
     * @throws ValueException thrown when the BST RI does not hold.
     */
    public void vistNode(TreeNode3 node) throws ValueException{

        // If we are at null or our current node does not have any children then return (RI holds).
        if(node == null || (node.left == null && node.right == null)){
            return;
        }

        try {
            if (node.left.val >= node.val) {
                throw new ValueException("BST RI does not hold.");
            }
        }
        catch(NullPointerException e){
        }

        try {
            if (node.right.val <= node.val) {
                throw new ValueException("BST RI does not hold.");
            }
        }
        catch(NullPointerException e){
        }

        // Recursively call the children to check their RIs.
        vistNode(node.left);
        vistNode(node.right);
    }

    public boolean validateBST(TreeNode3 root){

        try{
            vistNode(root);
        }
        catch(ValueException e){
            return false;
        }

        return true;

    }



    public static void main(String[] args) {
        TreeNode3 a = new TreeNode3(3);
        TreeNode3 b = new TreeNode3(5);
        TreeNode3 c = new TreeNode3(1);
        TreeNode3 d = new TreeNode3(6);
        TreeNode3 e = new TreeNode3(2);
        TreeNode3 f = new TreeNode3(0);
        TreeNode3 g = new TreeNode3(8);
        TreeNode3 h = new TreeNode3(7);
        TreeNode3 i = new TreeNode3(4);
        TreeNode3 j = new TreeNode3(0);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        e.left = h;
        e.right = i;
        h.right = j;

        ValidateBST test = new ValidateBST();
        System.out.println(test.validateBST(a));

        d.left = b;
        d.right = g;
        b.left = c;
        b.right = null;
        c.left = j;
        c.right = e;
        g.left = h;
        g.right = null;
        j.left = null;
        j.right = null;
        e.left = null;
        e.right = null;
        h.left = null;
        h.right = null;

        System.out.println(test.validateBST(d));

    }
}
