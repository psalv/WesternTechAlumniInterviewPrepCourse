package Wk1;

import java.util.Arrays;
import java.util.HashSet;

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> nums1HashSet = new HashSet<>();
        HashSet<Integer> nums2HashSet = new HashSet<>();
        HashSet<Integer> intersection = new HashSet<>();

        // Store the numbers in different hashsets for fast look up

        for(int i: nums1){
            nums1HashSet.add(i);
        }
        for(int i: nums2){
            nums2HashSet.add(i);
        }

        // Go through the elements in the hashsets determining which are contained in both
        // Since we do not know how many intersections there will be cannot yet create an appropriately sized array.

        for(Integer i: nums1HashSet){
            if(nums2HashSet.contains(i)){
                intersection.add(i);
            }
        }

        // Iterate through the intersections building them into the array format that the question calls for.

        int[] toReturn = new int[intersection.size()];
        int spot = 0;
        for(Integer i: intersection){
            toReturn[spot++] = i;
        }

        return toReturn;
    }

    public static void main(String[] args) {
        IntersectionOfTwoArrays test = new IntersectionOfTwoArrays();

        int[] arr1 = {1, 2, 111, 3, 4, 5};
        int[] arr2 = {4, 4, 4, 5, 20, 1, 30, 111};

        System.out.println(Arrays.toString(test.intersection(arr1, arr2)));
    }
}
