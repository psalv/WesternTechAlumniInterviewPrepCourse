package Wk3;

import java.util.*;

class UndigraphNode{
    ArrayList<UndigraphNode> neighbors = new ArrayList<>();
    int value;

    public UndigraphNode(int value){
        this.value = value;
    }

    public String toString(){
        Stack<UndigraphNode> stack = new Stack<>();
        HashSet<UndigraphNode> seen = new HashSet<>();
        stack.push(this);

        String s = "";

        while(!stack.isEmpty()){
            UndigraphNode curr = stack.pop();
            seen.add(curr);

            for(UndigraphNode n: curr.neighbors){
                if(!seen.contains(n)){
                    s += curr.value + " <-> " + n.value + "\n";
                    stack.push(n);
                }
            }
        }
        return s + "\n";
    }
}


public class CloneGraph {

    /**
     * Takes in a list of edges and builds an undirected graph from them.
     * Returns a node in this graph, since the graph is undirected, every connected node
     * is reachable from this node.
     *
     * @param edge a list of edges of the form [to, from].
     * @return a node from the constructed graph
     */
    public static UndigraphNode buildGraph(ArrayList<int[]> edge){
        HashMap<Integer, UndigraphNode> nodes = new HashMap<>();

        // We arbitrarily pick the first node we encounter to be the return node.
        UndigraphNode first = null;

        // We look at every edge, seeing if the associated nodes have been seen or not.
        for(int[] e: edge){

            // If they haven't been seen than we create the node.
            if(!(nodes.containsKey(e[0]))){
                nodes.put(e[0], new UndigraphNode(e[0]));

                if(first == null){
                    first = nodes.get(e[0]);
                }
            }
            if(!(nodes.containsKey(e[1]))){
                nodes.put(e[1], new UndigraphNode(e[1]));
            }

            // We set the neighbors based on the edges.
            nodes.get(e[1]).neighbors.add(nodes.get(e[0]));
            nodes.get(e[0]).neighbors.add(nodes.get(e[1]));
        }

        return first;
    }

    /**
     * From an undirected graph we return a clone of the graph by building the graph base don it's edges.
     *
     * @param graph a node in an undirected graph.
     * @return an undirected graph node belonging to the newly cloned graph.
     */
    public static UndigraphNode cloneGraph(UndigraphNode graph){
        ArrayList<int[]> edges = new ArrayList<>();
        HashSet<UndigraphNode> seen = new HashSet<>();

        Stack<UndigraphNode> toDo = new Stack<>();
        toDo.push(graph);

        while(!toDo.isEmpty()){
            UndigraphNode curr = toDo.pop();
            seen.add(curr);

            // We build a list of edges from the original graph to input into our buildGraph method.
            for(UndigraphNode n: curr.neighbors){
                if(!seen.contains(n)){
                    toDo.push(n);

                    int[] toAdd = {curr.value, n.value};
                    edges.add(toAdd);
                }
            }
        }
        return buildGraph(edges);
    }


    public static void main(String[] args) {
        int[][] toBuild = {{4, 1}, {2, 1}, {5, 4}, {6, 4}, {6, 5}, {3, 2}, {6, 3}, {6, 1}};
        ArrayList<int[]> list = new ArrayList<>();
        for(int[] i: toBuild){
            list.add(i);
        }

        UndigraphNode test = CloneGraph.buildGraph(list);
        System.out.println(test);

        UndigraphNode clone = CloneGraph.cloneGraph(test);
        System.out.println(clone);
    }
}
