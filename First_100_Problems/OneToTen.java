import java.util.ArrayList;
import java.util.Iterator;

/*
 * Solves Problems 1 to 10 on Project Euler Archives.
 *
 * These are:
 * 1	Multiples of 3 or 5
 * 2	Even Fibonacci numbers
 * 3	Largest prime factor
 * 4	Largest palindrome product
 * 5	Smallest multiple
 * 6	Sum square difference
 * 7	10001st prime
 * 8	Largest product in a series
 * 9	Special Pythagorean triplet
 * 10	Summation of primes
 *
 * Each problem will have a designated method which solves it. These methods will all
 * be run in the main method.
 */



public class OneToTen {

    public static void main(String[] args) {

        OneToTen solution = new OneToTen();

        // Problem 1
        System.out.println("P1: " + solution.sumOfMultiples3o5(1000));
        // Problem 2
        System.out.println("P2: " + solution.sumOfEvenFibonacci(4000000));
        // Problem 3
        System.out.println("P3: " + solution.findLargestPrimeFactor(600851475143L));
        // Problem 4
        System.out.println("P4: " + solution.findLargestPalindrome(3));
        // Problem 5
        System.out.println("P5: " + solution.minMaxLCM(1, 20));
        // Problem 6
        System.out.println("P6: " + solution.sumSquareDifference(1, 100));
        // Problem 7
        System.out.println("P7: " + solution.nthPrime(10001));

    }

    //Problem 1
    public int sumOfMultiples3o5(int max) {
        int sum = 0;
        for (int i = 0; i < max; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    //Problem 2
    public int sumOfEvenFibonacci(int max) {
        int sum = 0;
        int prev2 = 0;
        int prev1 = 1;
        for (int current = 1; current < max; current = prev1 + prev2) {
            sum += (current % 2 == 0) ? current : 0;
            prev2 = prev1;
            prev1 = current;
        }
        return sum;
    }

    //Problem 3
    public long findLargestPrimeFactor(long num) {
        int divisor = 2;
        boolean found = true;
        while (true) {
            if (num % divisor != 0) {
                found = false;
                for (int j = divisor; j < Math.sqrt(num); j++) {
                    if (num % j == 0) {
                        divisor = j;
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                num /= divisor;
            } else {
                return num;
            }
        }

        /*
         * This approach is the same method of prime factorization though it seems
         * to take more operations/steps to finish. However the code written is much
         * simpler than the code above.
         */
        // int divisor = 2;
        // while (divisor < Math.sqrt(num)) {
        // if (num % divisor != 0) {
        // divisor++;
        // } else {
        // num /= divisor;
        // }
        // }
        // return num;

    }

    //Problem 4
    public int findLargestPalindrome(int digits) {
        int maxNum = (int) Math.pow(10, digits) - 1;
        int minNum = (int) Math.pow(10, digits - 1);
        int highest = 0;
        for (int i = maxNum; i > minNum; i--) {
            for (int j = maxNum; j > minNum; j--) {
                if (isPalindrome(i * j) && (i * j) > highest) {
                    highest = i * j;
                    minNum = (int) Math.floor(Math.sqrt(highest));
                }
            }
        }
        return highest;
    }

    //Problem 5
    public long minMaxLCM(int min, int max) {
        int lcm = 1;
        for (int i = min; i < max; i++) {
            lcm = findLCM(lcm, i);
        }
        return lcm;
    }

    //Problem 6
    public long sumSquareDifference(int min, int max) {
        long sqaureOfSum = 0;
        long sumOfSquares = 0;
        for (int i = min; i <= max; i++) {
            sqaureOfSum += i;
            sumOfSquares += Math.pow(i, 2);
        }
        sqaureOfSum = (long) Math.pow(sqaureOfSum, 2);
        return Math.abs(sqaureOfSum - sumOfSquares);
    }

    //Problem 7
    public long nthPrime(long n) {
        //Initialize Arraylist of Integers
        int numPrimes = 0;
        long lastPrime = 0;
        ArrayList<Long> numbers = new ArrayList<Long>();

        //Find what the size of the list should be using the approximation from the prime number theorem
        //Since it is an approximation, we multiply the result by 1.5
        long numLimit = 10;
        while(true){
            if (numLimit/Math.log(numLimit) >= n){
                break;
            } else {
                numLimit*=2;
            }
        }
        for(long i = 2; i < numLimit*1.5; i++){
            numbers.add(i);
        }
        //Use Sieve of Eratosthenes to remove numbers from the list
        while(true){
            Iterator<Long> numIter = numbers.iterator();
            Long divisor = numbers.get(numPrimes);
            while(numIter.hasNext()){
                long temp = numIter.next();
                if(temp % divisor == 0 && temp != divisor){
                    numIter.remove();
                }
            }
            numPrimes++;
            //Check to see if we have reached the nth number
            lastPrime = divisor;
            if(numPrimes == n){
                return lastPrime;
            }
        }
    }

    // Helper Methods
    public boolean isPalindrome(int num) {
        // int length = ("" + num).length();
        if(num == 0){
            return true;
        }
        int length = (int)(Math.log10(num) + 1);
        int[] digits = new int[length];
        for (int i = 0; i < length; i++) {
            digits[i] = num % 10;
            num /= 10;
        }
        for (int i = 0; i < length / 2; i++) {
            if (digits[i] != digits[length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int arrayContains(int[] array, int num) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                return i;
            }
        }
        return -1;
    }

    public int[] findFactors(int num) {
        int[] factors = new int[num / 2];
        int counter = 0;
        for (int i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0) {
                factors[counter] = i;
                counter++;
            }
        }
        return factors;
    }

    public int findGCD(int big, int small) {
        if (big % small == 0) {
            return small;
        }
        return findGCD(small, big % small);
    }

    public int findLCM(int a, int b) {
        if (a > b) {
            return a * b / findGCD(a, b);
        } else {
            return a * b / findGCD(b, a);
        }

    }

    public boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}