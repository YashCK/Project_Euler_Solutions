import java.util.ArrayList;

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
        // Problem 8
        String stringNum = "73167176531330624919225119674426574742355349194934" + 
        "96983520312774506326239578318016984801869478851843" + 
        "85861560789112949495459501737958331952853208805511" +
        "12540698747158523863050715693290963295227443043557" + 
        "66896648950445244523161731856403098711121722383113" +
        "62229893423380308135336276614282806444486645238749" +
        "30358907296290491560440772390713810515859307960866" +
        "70172427121883998797908792274921901699720888093776" +
        "65727333001053367881220235421809751254540594752243" +
        "52584907711670556013604839586446706324415722155397" +
        "53697817977846174064955149290862569321978468622482" +
        "83972241375657056057490261407972968652414535100474" +
        "82166370484403199890008895243450658541227588666881" +
        "16427171479924442928230863465674813919123162824586" +
        "17866458359124566529476545682848912883142607690042" +
        "24219022671055626321111109370544217506941658960408" +
        "07198403850962455444362981230987879927244284909188" +
        "84580156166097919133875499200524063689912560717606" +
        "05886116467109405077541002256983155200055935729725" + 
        "71636269561882670428252483600823257530420752963450";
        System.out.println("P8: " + solution.largestProductInSeries(stringNum, 13));
        //Problem 9
        System.out.println("P9: " + solution.findSpecialPythagoreanTriplet(1000))   ;
        //Problem 10
        System.out.println("P10: " + solution.sumOfPrimes(2000000));
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
        int lastPrime = 0;
        //Find what the size of the list should be using the approximation from the prime number theorem
        //Since it is an approximation, we multiply the result by 1.5
        int numLimit = 10;
        while(true){
            if (numLimit/Math.log(numLimit) >= n)
                break;
            numLimit*=2;
        }
        //Initialize boolean of primes to true
        boolean[] primes = new boolean[(int)(numLimit*1.5)];
        for(int i = 0; i < primes.length; i++){
            primes[i] = true;
        }
        //Use Sieve of Eratosthenes to find prime numbers
        int num = 2;
        while(true){
            if(num >= primes.length){
                break;
            } else if (primes[num]){
                for(long i = ((long)num)*num; i < primes.length; i += num){
                    primes[(int)i] = false;
                }
                numPrimes++;
                lastPrime = num;
                num++;
                //Check to see if we have reached the nth prime
                if(numPrimes == n){
                    return lastPrime;
                }
            } else {
                num++;
            }
        }
        return lastPrime;

        //Method using an ArrayList

        // ArrayList<Long> numbers = new ArrayList<Long>();
        // for(long i = 2; i < numLimit*1.5; i++){
        //     numbers.add(i);
        // }
        // //Use Sieve of Eratosthenes to remove numbers from the list
        // while(true){
        //     Iterator<Long> numIter = numbers.iterator();
        //     Long divisor = numbers.get(numPrimes);
        //     while(numIter.hasNext()){
        //         long temp = numIter.next();
        //         if(temp % divisor == 0 && temp != divisor){
        //             numIter.remove();
        //         }
        //     }
        //     numPrimes++;
        //     //Check to see if we have reached the nth number
        //     lastPrime = divisor;
        //     if(numPrimes == n){
        //         return lastPrime;
        //     }
        // }
    }

    //Problem 8
    public long largestProductInSeries(String num, int digits){
        long largestPossible = (long)Math.pow(9, digits);
        long maxProduct = 0;
        //Create Array consisting of digits of num
        char[] digitsArray = num.toCharArray();
        // System.out.println("Array: " + Arrays.toString(digitsArray));
        //Loop through every "digits" length sequence to find maxProduct
        for(int i = 0; i < digitsArray.length - digits; i++){
            long product = Integer.parseInt("" + digitsArray[i]);
            // System.out.println(product);
            for(int j = i + 1; j < i + digits; j++){
                product *= Integer.parseInt("" + digitsArray[j]);
            }
            // System.out.println("product: " + product);
            if(product == largestPossible){
                return largestPossible;
            } else if(product > maxProduct){
                maxProduct = product;
            }
        }
        return maxProduct;
    }

    //Problem 9
    public long findSpecialPythagoreanTriplet(int sum){
        /*
        c = sum - a - b
        a^2 + b^2 = (sum - a - b)^2 = sum^2 + a^2 + b^2 - 2sum*a - 2sum*b + 2ab
        0 = sum^2 - 2sum*a - 2sum*b + 2ab
        sum^2/2 = (sum - a)(sum - b)
        (sum^2/2)/sum ≤ factor < sum
        */
        int goal = (int)Math.pow(sum, 2)/2;
        int lowerbound = goal/sum;
        for(int i = lowerbound; i < sum; i++){
            for(int j = sum - 1; j > lowerbound; j--){
                if(i*j == goal){
                    int a = sum - i;
                    int b = sum - j;
                    return a*b*(sum - a - b);
                }
            }
        }
        return -1;
    }

    //Problem 10
    public long sumOfPrimes(int below){
        long sum = 0;
        //Initialize boolean of primes to true
        boolean[] primes = new boolean[below];
        for(int i = 0; i < primes.length; i++){
            primes[i] = true;
        }
        //Use Sieve of Eratosthenes to find prime numbers and add to sum counter
        int num = 2;
        while(num < primes.length){
            if (primes[num]){
                for(long i = ((long)num)*num; i < primes.length; i += num){
                    primes[(int)i] = false;
                }
                sum += num;
            }
            num++;
        }
        return sum;
    }

    // Helper Methods

    Integer[] primeFactorization(int num){
        ArrayList<Integer> factors = new ArrayList<>();
        int lpf = (int)findLargestPrimeFactor(num);
        while(lpf != 1){
            factors.add(lpf);
            num = num/lpf;
            lpf = (int)findLargestPrimeFactor(num);
        }
        return factors.toArray(new Integer[factors.size()]);
    }

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

    public boolean isPrime(long num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
