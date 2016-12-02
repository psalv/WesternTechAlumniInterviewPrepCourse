package Wk1;

import java.util.Arrays;

public class SpiralMatrixII {

    private enum DIRECTION{RIGHT, DOWN, LEFT, UP}
    private DIRECTION currentDirection;
    private int xPosition;
    private int yPosition;
    private int[][] matrix;


    /**
     * Uses the properties of spiral matrix to generate a an n x n spiral matrix in O(n^2) time.
     *
     * @param n the size of the n x n matrix
     * @return an n x n matrix of ints arranged in a spiralling increasing order
     */
    public int[][] createSpiralMatrix(int n){
        xPosition = 0;
        yPosition = 1;
        currentDirection = DIRECTION.RIGHT;

        matrix = new int[n][n];

        // Create border using the properties of a spiral matrix.

        int lastRow = n*3 - 2;
        int secondRowFirstColumn = 4*(n - 1);
        int secondRowLastColumn = n + 1;

        for(int i = 0; i < n; i++) {
            matrix[0][i] = i + 1;
            matrix[n - 1][i] = lastRow--;

            if(i < n - 2){
                matrix[i + 1][0] = secondRowFirstColumn--;
                matrix[i + 1][n - 1] = secondRowLastColumn++;
            }
        }

        // Start in the row below the border and move in a spiral motion.

        int currentNumber = 4*(n - 1) + 1;
        while(currentNumber <= n*n && n != 1){
            move();
            matrix[yPosition][xPosition] = currentNumber++;
        }
        return matrix;
    }


    /**
     * The order of direction within the inner squares will always be RIGHT, DOWN, LEFT, UP.
     *
     * This utilizes already filled spaces and the current direction to determine when to turn.
     */
    private void move() {
        switch (currentDirection) {
            case RIGHT:
                if(matrix[yPosition][xPosition + 1] != 0){
                    currentDirection = DIRECTION.DOWN;
                    move();
                } else{
                    xPosition += 1;
                }
                break;
            case DOWN:
                if(matrix[yPosition - 1][xPosition] != 0){
                    currentDirection = DIRECTION.LEFT;
                    move();
                } else{
                    yPosition -= 1;
                }
                break;
            case LEFT:
                if(matrix[yPosition][xPosition - 1] != 0){
                    currentDirection = DIRECTION.UP;
                    move();
                } else{
                    xPosition -= 1;
                }
                break;
            case UP:
                if(matrix[yPosition + 1][xPosition] != 0){
                    currentDirection = DIRECTION.RIGHT;
                    move();
                } else{
                    yPosition += 1;
                }
                break;
        }
    }

    public static void main(String[] args) {
        SpiralMatrixII test = new SpiralMatrixII();

        System.out.println("1x1 Matrix:");
        for(int[] i: test.createSpiralMatrix(1))
            System.out.println(Arrays.toString(i));

        System.out.println("\n3x3 Matrix:");
        for(int[] i: test.createSpiralMatrix(3))
            System.out.println(Arrays.toString(i));

        System.out.println("\n5x5 Matrix:");
        for(int[] i: test.createSpiralMatrix(100))
            System.out.println(Arrays.toString(i));
    }
}
