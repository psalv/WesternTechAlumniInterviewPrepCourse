package Wk1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class LongestNonrepeatingSubstring {


    /**
     * Determines the longest substring of non-repeating characters in a string.
     *
     * @param s a string in which we wish to find the longest non-repeating subsequence of characters.
     * @return an integer representing the length of the longest non-repeating subsequence of characters.
     */
    public int lengthOfLongest(String s){

        // Create a hashmap to keep track of which characters have been seen in substrings
        // starting at positions indicated by the keys of the hashmap.

        HashMap<Integer, HashSet<String>> currentOpenFrames = new HashMap<>();
        int longest = 1;

        for(int i = 0; i < s.length(); i++){

            // Add each position to the hashmap, inputting itself as one of the seen characters.

            Integer currentPosition = (int)s.charAt(i);
            String currentCharString = Character.toString(s.charAt(i));
            currentOpenFrames.put(currentPosition, new HashSet<>());

            currentOpenFrames.get(currentPosition).add(currentCharString);

            // Iterate through the hashmap to add this character to all open substring frames.

            Iterator iterator = currentOpenFrames.keySet().iterator();
            while(iterator.hasNext()){

                Object openPosition = iterator.next();

                if(openPosition.equals(currentPosition)){
                    continue;
                }

                else {
                    HashSet<String> currentHashSet = currentOpenFrames.get(openPosition);

                    // If we've seen this character before in an open frame the frame is closed
                    // we check if it is the longest and store it if so. Then it is removed from currentOpenFrames.

                    // Otherwise the character is added as a seen character in the key's hashset.

                    if (currentHashSet.contains(currentCharString)) {
                        if (longest < currentHashSet.size()) {
                            longest = currentHashSet.size();
                        }

                        iterator.remove();
                    } else {
                        currentHashSet.add(currentCharString);
                    }
                }
            }
        }

        // Once we've exited the for loop every position has been examined and we can return the longest.

        return longest;

    }


    public static void main(String[] args) {
        LongestNonrepeatingSubstring test = new LongestNonrepeatingSubstring();

        System.out.println("\nString: 'aaaaabcedfghiaaaaa'");
        System.out.println("Answer should be 9: " + test.lengthOfLongest("aaaaabcedfghiaaaaa"));

        System.out.println("\nString: 'aaaaaaaaaaaaaaaa'");
        System.out.println("Answer should be 1: " + test.lengthOfLongest("aaaaaaaaaaaaaaaa"));

        System.out.println("\nString: 'aaaaaaaabaaaaa'");
        System.out.println("Answer should be 2: " + test.lengthOfLongest("aaaaaaaabaaaaaaa"));
    }
}
