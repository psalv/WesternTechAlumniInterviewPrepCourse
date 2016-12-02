package Wk3;

// A node is a descendant of itself.

// The lowest possible common ancestor will be the first node (predecessor) that we find.
// From this point each time we go UP in the tree, this is the newest possible LCA.
// This way, when we find the second predecessor we can return the correct possible LCA (no need to traverse back up the tree).

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
          val = x;
      }
     public String toString(){
         return Integer.toString(val);
     }
}


public class LCABinaryTree {

    private boolean found1 = false;
    private boolean found2 = false;
    private TreeNode LCA;

    public void reset(){
        found1 = false;
        found2 = false;
        LCA = null;
    }


    // Uses an in order search to examine every node in the tree, keeping track of the which node could be the LCA.
    public TreeNode findLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){

        // If they are the same node, then we are only searching for one node.
        if(p == q){
            found2 = true;
        }

        if(root == null){
            return LCA;
        }

        // We use
        findLowestCommonAncestor(root.left, p, q);

        // If both are found recurse up, we are ready to return.
        if(found1 && found2){
            return LCA;
        }

        // If either has been found and we move up the tree, the new LCA possibility is the current root.
        if(found1 || found2){
            if(root.left == LCA){
                LCA = root;
            }
        }

        // If we find one and another has already been found, then we are finished.
        if((root == p || root == q) && found1){
            found2 = true;
            return LCA;
        }

        // This will execute when the first of the nodes is found, the lowest LCA possible is the current node.
        if(root == p || root == q){
            found1 = true;
            LCA = root;
        }

        findLowestCommonAncestor(root.right, p, q);

        return LCA;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(6);
        TreeNode e = new TreeNode(2);
        TreeNode f = new TreeNode(0);
        TreeNode g = new TreeNode(8);
        TreeNode h = new TreeNode(7);
        TreeNode i = new TreeNode(4);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        e.left = h;
        e.right = i;

        LCABinaryTree test = new LCABinaryTree();

        System.out.println(test.findLowestCommonAncestor(a, b, c));
        test.reset();
        System.out.println(test.findLowestCommonAncestor(a, b, b));
        test.reset();
        System.out.println(test.findLowestCommonAncestor(a, b, d));
        test.reset();
        System.out.println(test.findLowestCommonAncestor(a, i, d));
        test.reset();
        System.out.println(test.findLowestCommonAncestor(a, i, h));
    }

}
