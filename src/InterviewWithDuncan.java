import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class InterviewWithDuncan {

    /**
     Given two sorted lists, merge them into a still sorted list.
     */


/**
 a = 2, 4, 6
 b = 3, 7, 8
 */

import java.util.*;


// 2, 3, 4, 6, 7, 8

    public ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b) {

        ArrayList<Integer> c = new ArrayList<>();

        int placeA = 0; //3
        int placeB = 0; //2
        int length = a.size() + b.size();   //6

        while(c.size() < length){  //1

            if(placeA == a.size()){
                c.add(b[placeB++]);
            }

            else if(placeB == b.size()){
                c.add(a[placeA++]);
            }

            else if(a[placeA] < b[placeB]){
                c.add(a[placeA++]);
            }
            else{
                c.add(b[placeB++]);
            }
        }

        return c;

    }


    /**
     Given a 2D character array representing a game of Boggle, as well as a word in the form of a string, implement a function isInBoard() that determines if the word is in the board. A word is a valid word on the Boggle board when its letters are adjacent (above, below, left, or right), in order.

     For example, APPLE is a word on this Boggle board:

     B S D A D
     I O P P E
     Q R L G Z
     L S E O O
     A F S K J
     */


    class Node{
        ArrayList<Node> neighbors = new ArrayList<>();
        Character val;

        public Node(Character val){
            this.val = val;
        }
    }

    HashMap<String, Node> position = new HashMap<>();

    public void buildGraph(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                position.put("" + i + j, new Node(board[i][j]));
            }
        }

        for(String s: position.keySet()){

            int i = Integer.parseInt(s.charAt(0));
            int j = Integer.parseInt(s.charAt(1));


            int a = i + 1;
            int b = j + 1;
            int c = i - 1;
            int d = j - 1;


            if(b != 5)
                position.get(s).neighbors.add(position.get(i.toString() + b.toString()));   //Primitive types don't have toString methods.
            if(d != -1)
                position.get(s).neighbors.add(position.get(i.toString() + d.toString()));
            if(c != -1)
                position.get(s).neighbors.add(position.get(c.toString() + j.toString()));
            if(a != 5)
                position.get(s).neighbors.add(position.get(a.toString() + j.toString()));
        }

    }


// Iterate through the positions
// If the position is equal to the first character in the word, then we do a DFS

// Base-case, if the word is greater array > false

// Check each neighbor for the next character we need.

// Check again until we reach the last character



    public boolean DFSvisit(Node current, int p, HashSet seen, String word){
        seen.add(current);

        if(p == word.length()){
            return true;
        }

        for(Node n: current.neighbors){

            if(seen.contains(n)){
                continue;
            }

            else{
                if(n.val == word.charAt(p){
                    // what would you do here?
                    if( DFSvisit(n, p + 1, seen, word)){
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public boolean isInBoard(char[][] board, String word) {
        buildGraph(board);


        int p = 0;
        for(String s: position.keySet()){

            Node current = position.get(s);

            if(current.val == word.charAt(p)){

                if(DFSvisit(current, p + 1, new HashSet<Nodes>(), word)){
                    return true;
                }


            }
        }

        return false;
    }


}
