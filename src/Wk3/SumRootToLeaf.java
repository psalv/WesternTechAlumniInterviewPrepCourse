package Wk3;


// This question requires me to sum all of the strings concatenated by the path from root to each leaf.

// What I can do is an in order traversal.
// Whenever I move down a node, I pass the current string and build the next node on to the tail end of it.
// When I reach a node without any children, this is a leaf, and I add the number to the running total.

// First case to check is if the current node has no children, if so then add and return up a layer.

class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;
    TreeNode2(int x) {
        val = x;
    }
    public String toString(){
        return Integer.toString(val);
    }
}




public class SumRootToLeaf {

    private int sum = 0;

    // Keeps track of the current string, when we reach a leaf node we add it to the sum.
    public void sumNumbers(TreeNode2 node, String s) {

        if(node == null){
            return;
        }

        s += node.toString();

        if(node.left == null && node.right == null){
            sum += Integer.parseInt(s);
            return;
        }

        sumNumbers(node.left, s);
        sumNumbers(node.right, s);
    }

    // The beginning of the algorithm, begins a the root and does a traversal to visit all leaf nodes.
    public int sumNumbers(TreeNode2 root){

        if(root == null){
            return sum;
        }

        String s = root.toString();

        if(root.left == null && root.right == null){
            sum += Integer.parseInt(s);
            return sum;
        }

        sumNumbers(root.left, s);
        sumNumbers(root.right, s);

        return sum;
    }


    public static void main(String[] args) {
        TreeNode2 a = new TreeNode2(3);
        TreeNode2 b = new TreeNode2(5);
        TreeNode2 c = new TreeNode2(1);
        TreeNode2 d = new TreeNode2(6);
        TreeNode2 e = new TreeNode2(2);
        TreeNode2 f = new TreeNode2(0);
        TreeNode2 g = new TreeNode2(8);
        TreeNode2 h = new TreeNode2(7);
        TreeNode2 i = new TreeNode2(4);
        TreeNode2 j = new TreeNode2(0);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        e.left = h;
        e.right = i;
        h.right = j;

        System.out.println(356 + 35270 + 3524 + 310 + 318);

        SumRootToLeaf test = new SumRootToLeaf();
        System.out.println(test.sumNumbers(a));
    }
}
