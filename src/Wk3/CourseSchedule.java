package Wk3;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class DigraphNode{
    ArrayList<DigraphNode> neighbors = new ArrayList<>();
    int value;
    boolean status = false;

    public DigraphNode(int value){
        this.value = value;
    }
}


public class CourseSchedule {

    private HashMap<Integer, DigraphNode> nodes;
    private HashSet<DigraphNode> seen;

    // Builds a digraph from an array of edges of the form [[to, from], ... ]
    public void buildGraph(int[][] prereqs){
        for(int[] p: prereqs){

            if(!(nodes.containsKey(p[0]))){
                nodes.put(p[0], new DigraphNode(p[0]));
            }
            if(!(nodes.containsKey(p[1]))){
                nodes.put(p[1], new DigraphNode(p[1]));
            }

            nodes.get(p[1]).neighbors.add(nodes.get(p[0]));
        }
    }


    /**
     * A DFS visit algorithm using a property of the node, status, to determine whether or not the node has been seen already.
     * Since the purpose of this algorithm is to only look for cycles, we need not keep track of the path,
     * but rather need to exhaustively look at all neighbours until none remain or a cycle is found.
     *
     * @param s A starting point for a DFS
     * @throws ValueException when a cycle is found (a node that is already in status is visited again)
     */
    public void DFSvisit(DigraphNode s) throws ValueException{
        s.status = true;

        for(DigraphNode n: s.neighbors){
            if(n.status){
                throw new ValueException("Cycle found.");
            }

            if(!seen.contains(n)){
                seen.add(n);
                DFSvisit(n);
            }
        }
        s.status = false;
    }


    public boolean canFinish(int[][] prerequisites){
        nodes = new HashMap<>();
        seen = new HashSet<>();

        buildGraph(prerequisites);

        try {
            for (Integer i : nodes.keySet()) {
                DigraphNode n = nodes.get(i);

                // Only visit nodes that we haven't seen before, ones we have seen we are sure do not contain cycles.
                if(!seen.contains(n)) {
                    seen.add(n);
                    DFSvisit(n);
                }
            }
        }

        // If at any point a ValueException is thrown, then there is a cycle and we return false.
        catch(ValueException e) {
            return false;
        }

        // If we make it through every node in the key set without throwing an exception, then no cycles  exist.
        return true;
    }

    public String toString(){
        String toReturn = "";
        for(Integer key: nodes.keySet()){
            for(DigraphNode node: nodes.get(key).neighbors){
                toReturn += key + " -> " + node.value + "\n";
            }
        }
        return toReturn;
    }

    public static void main(String[] args) {
        int[][] toBuild1 = {{4, 1}, {2, 1}, {5, 4}, {6, 4}, {6, 5}, {3, 2}};
        int[][] toBuild2 = {{4, 1}, {2, 1}, {5, 4}, {6, 4}, {6, 5}, {3, 2}, {1, 2}};

        CourseSchedule test = new CourseSchedule();
        System.out.println(test.canFinish(toBuild1));
        System.out.println(test.canFinish(toBuild2));
    }
}
