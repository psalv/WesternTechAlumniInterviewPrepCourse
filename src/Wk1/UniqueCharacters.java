package Wk1;

// Optimizations:
    // If string is greater than 256 characters then there is necessarily repetition


public class UniqueCharacters {


    public UniqueCharacters(){
    }

    public boolean find(String s){
        int[] table = new int[256];

        for(int i = 0; i < s.length(); ++i){
            int j = (int)(s.charAt(i));
            if(table[j] == 0){
                table[j] = 1;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        UniqueCharacters x = new UniqueCharacters();
        System.out.println(x.find("asdjhk8A"));
    }
}
