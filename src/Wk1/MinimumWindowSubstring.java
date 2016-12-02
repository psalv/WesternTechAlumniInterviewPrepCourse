package Wk1;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class MinimumWindowSubstring {


    /**
     * Searches for the shortest substring of a document containing all characters of an inputted substring.
     * Note: limitation in that the input substring cannot have any repeating characters.
     *
     * @param s the document we are searching within
     * @param t the substring we are searching for
     * @return an empty string if the substring t doe snot appear in s, otherwise returns the shortest window substring
     */
    public String findMinimumWindowSubstring(String s, String t){

        // Comment out next 2 lines to include cases:
        s = s.toUpperCase();
        t = t.toUpperCase();

        // Added all of the characters we need to a hash set so we don't have to iterate
        // through the string t multiple times.

        HashSet<Character> windowCharacters = new HashSet<>();
        for(int c = 0; c < t.length(); c++){
            windowCharacters.add(t.charAt(c));
        }

        // Map the starting position to a character hashset so we know which of the characters have been seen.

        HashMap<Integer, HashSet<Character>> possibleWindows = new HashMap<>();
        LinkedList<Integer> currentWindowStarts = new LinkedList<>();


        int[] shortestPos = new int[2];
        int shortestSize = -1;

        for(int i = 0; i < s.length(); i++){

            char currentChar = s.charAt(i);

            if(windowCharacters.contains(currentChar)){

                // Creates the hashmap entry for the new possible start of the window

                possibleWindows.put(i, new HashSet<>());
                possibleWindows.get(i).add(currentChar);

                // Iterator through all of the window starting points to see if this character will
                // contribute to their completion.

                Iterator<Integer> iterator = currentWindowStarts.iterator();
                while(iterator.hasNext()){

                    Integer pos = iterator.next();

                    // If the size of a previous starting position already exceeds
                    // the smallest size then it is no longer a candidate, can be removed.

                    if(i - pos > shortestSize && shortestSize != -1){
                        iterator.remove();
                    }

                    // Otherwise we add it to the appropriate hashmap's corresponding hashset.

                    else {

                        HashSet<Character> correspondingHashSet = possibleWindows.get(pos);
                        if (!(correspondingHashSet.contains(currentChar))) {
                            correspondingHashSet.add(currentChar);
                        }

                        // When there are sufficient characters in the hash set the window is complete
                        // We compare with the shortest and remove it from the list of current open windows.

                        if (correspondingHashSet.size() == t.length()) {
                            int windowSize = i - pos;
                            iterator.remove();

                            if (windowSize < shortestSize || shortestSize == -1) {
                                shortestPos[0] = pos;
                                shortestPos[1] = i;
                                shortestSize = windowSize;
                            }
                        }
                    }
                }
                currentWindowStarts.add(i);
            }
        }

        if(shortestSize == -1){
            return "";
        }
        else{
            return s.substring(shortestPos[0], shortestPos[1] + 1);
        }

    }


    public static void main(String[] args) {
        MinimumWindowSubstring test = new MinimumWindowSubstring();

        System.out.println("Answer should be 'ABEC':" );
        System.out.println(test.findMinimumWindowSubstring("ac sjkdhkas iweabecyrweyabeeci", "abc"));
//        System.out.println("Answer should be 'abec':" );
        System.out.println("\nAnswer should be '':" );
        System.out.println(test.findMinimumWindowSubstring("ac sjkdhkas iweab2cyrweyi", "qq"));
    }

}
