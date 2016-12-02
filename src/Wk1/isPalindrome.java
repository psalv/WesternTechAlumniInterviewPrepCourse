package Wk1;


public class isPalindrome {

    /**
     * Determines if a string is palindromic in O(n) time.
     * Ignores non alphanumeric characters and cases.
     *
     * @param s a string that we will check for having a palindromic property
     * @return a boolean representing if the string s is palindromic
     */
    public boolean checkPalindrome(String s){

        // Ignore case

        s = s.toUpperCase();
        int front = 0;
        int back = s.length() - 1;

        // Exit condition when front and back either change (have moved past the middle) or are equal (at the middle)

        while(front < back){

            // First need to check if the characters to be considered are alpha numeric
            // If not, we move to the next character and check again.

            boolean bothAlphaNumeric = true;

            char frontChar = s.charAt(front);
            char backChar = s.charAt(back);

            if(!(Character.isLetterOrDigit(frontChar))) {
                front += 1;
                bothAlphaNumeric = false;
            }
            if(!(Character.isLetterOrDigit(backChar))){
                back -= 1;
                bothAlphaNumeric = false;
            }

            // When we know both numbers are alpha numeric, we check if the relative ends are the same
            // If not the sequence is not palindromic

            if(bothAlphaNumeric) {
                if (!(frontChar == backChar)) {
                    return false;
                }
                front += 1;
                back -= 1;
            }
        }

        // If we can exit the while loop then every corresponding alphanumeric pair was equal
        // Therefore this is a palindrome

        return true;
    }

    public static void main(String[] args) {
        isPalindrome test = new isPalindrome();

        // Testing various cases

        System.out.println("Even length palindrome: ");
        System.out.println(test.checkPalindrome("aabbaa"));
        System.out.println(test.checkPalindrome("a,,,,abb:/][aa"));
        System.out.println(test.checkPalindrome(""));

        System.out.println("\nOdd length palindrome: ");
        System.out.println(test.checkPalindrome("aabaa"));
        System.out.println(test.checkPalindrome("a"));

        System.out.println("\nEven length non-palindrome: ");
        System.out.println(test.checkPalindrome("aabbac"));

        System.out.println("\nOdd length non-palindrome: ");
        System.out.println(test.checkPalindrome("aabbc"));

    }
}
