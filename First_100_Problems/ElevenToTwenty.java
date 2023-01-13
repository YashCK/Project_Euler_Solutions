import java.util.ArrayList;

/*
 * Solves Problems 1 to 10 on Project Euler Archives,
 *
 * These are:
 * 11	Largest product in a grid
 * 12	Highly divisible triangular number
 * 13	Large sum
 * 14	Longest Collatz sequence
 * 15	Lattice paths
 * 16	Power digit sum
 * 17	Number letter counts
 * 18	Maximum path sum I
 * 19	Counting Sundays
 * 20	Factorial digit sum
 *
 * Each problem will have a designated method which solves it, These methods will all
 * be run in the main method,
 */

public class ElevenToTwenty {
    
    public static void main(String[] args) {
        
        ElevenToTwenty solution = new ElevenToTwenty();

        //Problem 11
        int[][] grid = {
            {8, 2, 22, 97, 38, 15, 0, 40, 0, 75, 4, 5, 7, 78, 52, 12, 50, 77, 91, 8},
            {49, 49, 99, 40, 17, 81, 18, 57, 60, 87, 17, 40, 98, 43, 69, 48, 4, 56, 62, 0},
            {81, 49, 31, 73, 55, 79, 14, 29, 93, 71, 40, 67, 53, 88, 30, 03, 49, 13, 36, 65},
            {52, 70, 95, 23, 04, 60, 11, 42, 69, 24, 68, 56, 1, 32, 56, 71, 37, 2, 36, 91},
            {22, 31, 16, 71, 51, 67, 63, 89, 41, 92, 36, 54, 22, 40, 40, 28, 66, 33, 13, 80},
            {24, 47, 32, 60, 99, 3, 45, 2, 44, 75, 33, 53, 78, 36, 84, 20, 35, 17, 12, 50},
            {32, 98, 81, 28, 64, 23, 67, 10, 26, 38, 40, 67, 59, 54, 70, 66, 18, 38, 64, 70},
            {67, 26, 20, 68, 2, 62, 12, 20, 95, 63, 94, 39, 63, 8, 40, 91, 66, 49, 94, 21},
            {24, 55, 58, 5, 66, 73, 99, 26, 97, 17, 78, 78, 96, 83, 14, 88, 34, 89, 63, 72},
            {21, 36, 23, 9, 75, 0, 76, 44, 20, 45, 35, 14, 0, 61, 33, 97, 34, 31, 33, 95},
            {78, 17, 53, 28, 22, 75, 31, 67, 15, 94, 3, 80, 4, 62, 16, 14, 9, 53, 56, 92},
            {16, 39, 5, 42, 96, 35, 31, 47, 55, 58, 88, 24, 0, 17, 54, 24, 36, 29, 85, 57},
            {86, 56, 0, 48, 35, 71, 89, 7, 5, 44, 44, 37, 44, 60, 21, 58, 51, 54, 17, 58},
            {19, 80, 81, 68, 5, 94, 47, 69, 28, 73, 92, 13, 86, 52, 17, 77, 04, 89, 55, 40},
            {4, 52, 8, 83, 97, 35, 99, 16, 07, 97, 57, 32, 16, 26, 26, 79, 33, 27, 98, 66},
            {88, 36, 68, 87, 57, 62, 20, 72, 3, 46, 33, 67, 46, 55, 12, 32, 63, 93, 53, 69},
            {4, 42, 16, 73, 38, 25, 39, 11, 24, 94, 72, 18, 8, 46, 29, 32, 40, 62, 76, 36},
            {20, 69, 36, 41, 72, 30, 23, 88, 34, 62, 99, 69, 82, 67, 59, 85, 74, 4, 36, 16},
            {20, 73, 35, 29, 78, 31, 90, 1, 74, 31, 49, 71, 48, 86, 81, 16, 23, 57, 5, 54},
            {1, 70, 54, 71, 83, 51, 54, 69, 16, 92, 33, 48, 61, 43, 52, 1, 89, 19, 67, 48}
        };
        System.out.println("P11: " + solution.largestProductInAGrid(grid));
        //Problem 12
        System.out.println("P12: " + solution.higlyDivisiblePrimeNumber(500));
    }

    //Problem 11
    public long largestProductInAGrid(int[][] grid){
        long max = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                //Up
                max = Math.max(max, prodInDirection(grid, i, j, 0, 1, 4));
                //Down
                max = Math.max(max, prodInDirection(grid, i, j, 0, -1, 4));
                //Right
                max = Math.max(max, prodInDirection(grid, i, j, 1, 0, 4));
                //Left
                max = Math.max(max, prodInDirection(grid, i, j, -1, 0, 4));
                //Up Right
                max = Math.max(max, prodInDirection(grid, i, j, 1, 1, 4));
                //Up Left
                max = Math.max(max, prodInDirection(grid, i, j, -1, 1, 4));
                //Down Right
                max = Math.max(max, prodInDirection(grid, i, j, 1, -1, 4));
                //Down Left
                max = Math.max(max, prodInDirection(grid, i, j, -1, -1, 4));
            }  
        }
        return max;
    }

    //Problem 12
    long higlyDivisiblePrimeNumber(int factors){
        int num = 2;
        long triangleNum = 1;
        while(true){
            if(numFactors(triangleNum) < factors){
                triangleNum += num;
                num++;
            } else {
                return triangleNum;
            }
        }
    }

    //Helper functions

    int numFactors(long num){
        Integer[] primeFactors = primeFactorization(num);
        int[] unique = new int[primeFactors.length];
        int[] count = new int[unique.length];
        int ind = -1;
        int lastFactor = -1;
        for(Integer i : primeFactors){
            if(ind == -1 || i != lastFactor){
                ind++;
                unique[ind] = i;
                lastFactor = i;
                count[ind] = 1;
            } else{
                count[ind]++;
            }
        }
        int c = 1;
        for(int n : count){
            if(n == 0)
                break;
            c *= n + 1;
        }        
        return c;
    }

    Integer[] primeFactorization(long num){
        ArrayList<Integer> factors = new ArrayList<>();
        int lpf = (int)findLargestPrimeFactor(num);
        while(lpf != 1){
            factors.add(lpf);
            num = num/lpf;
            lpf = (int)findLargestPrimeFactor(num);
        }
        return factors.toArray(new Integer[factors.size()]);
    }

    public long findLargestPrimeFactor(long num) {
        long original = num;
        int divisor = 2;
        while (divisor <= Math.sqrt(original)) {
            if (num % divisor != 0) {
                divisor++;
            } else {
                if(num == divisor)
                    return num;
                num /= divisor;
            }
        }
        return num;

    }

    long prodInDirection(int[][] grid, int row, int col, int xdir, int ydir, int times){
        long product = 1;
        for(int i = 0; i < times; i++){
            product *= grid[row][col];
            row += xdir;
            col += ydir;
            if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
                break;
        }
        return product;
    }

}