import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

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

public class Twenty {

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

    //Problem 13
    String largeSum(String[] numbers, int digits){
        BigInteger sum = BigInteger.ZERO;
        for(String s : numbers){
            sum = sum.add(new BigInteger(s));
        }
        return sum.toString().substring(0, digits);
    }

    //Problem 14
    int longestCollatzSequence(int num){
        // int[] memoryNums = new int[num];
        int[] memoryLens = new int[num];
        int maxLength = 1;
        int maxNum = 1;
        for(int i = 2; i < num; i++){
            //Find Collatz Sequence Length
            int length = 0;
            long n = i;
            while(n != 1){
                n = (n % 2 == 0) ? n/2 : 3*n + 1;
                length++;
                //Use memoization
                if(n < num && memoryLens[(int)n] != 0){
                    length += memoryLens[(int)n];
                    break;
                }
            }
            memoryLens[i] = length;
            if(length > maxLength){
                maxLength = length;
                maxNum = i;
            }
        }
        return maxNum;
    }

    //Problem 15
    BigInteger latticePaths(int gridSize){
        //n choose k
        //There are 2N choose N paths to traverse the lattice
        BigInteger product = BigInteger.ONE;
        int n = 2*gridSize;
        for(int i = 0; i < gridSize; i++){
            product = product.multiply(new BigInteger("" + (n - i)));
        }
        BigInteger factorial = BigInteger.ONE;
        for(int i = 2; i <= gridSize; i++){
            factorial = factorial.multiply(new BigInteger("" + i));
        }
        return product.divide(factorial);
    }

    //Problem 16
    int powerDigitSum(int base, int exp){
        int[] digits = bigNumber(base, exp);
        return Arrays.stream(digits).sum();
    }

    //Problem 17
    int numberLetterCounts(int start, int end){
        int count = 0;
        for(int i = start; i <= end; i++){
            count += stringRepresentation(i).length();
        }
        return count;
    }

    //Problem 18
    int maximumPathSum(int[][] triangle){
        for(int row = triangle.length - 2; row > -1; row--){
            for(int pos = 0; pos < triangle[row].length; pos++){
                //Add the greater of its two children
                triangle[row][pos] += Math.max(triangle[row + 1][pos], triangle[row + 1][pos + 1]);
            }
        }
        return triangle[0][0];
    }

    //Problem 19
    int countingSundays(int day, int month, int year){
        //1 Jan 1900 was a Monday
        int weekday = 0;
        //Counter for number of sundays
        int count = 1;
        //Iterate through the months of every following year
        for(int y = 1901; y <= year; y++){
            int feb_days = (y % 4 == 0) ? 29 : 28;
            for(int m = 1; m <= 12; m++){
                switch(m) {
                    case 1, 3, 5, 7, 8, 10, 12:
                        weekday += 31;
                        break;
                    case 2:
                        weekday += feb_days;
                        break;
                    default:
                        weekday += 30;
                }
                weekday %= 7;
                if (weekday == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    //Problem 20
    int factorialDigitSum(int factorial){
        BigInteger num = BigInteger.ONE;
        for (int i = 2; i <= factorial; i++){
            num = num.multiply(new BigInteger(i + ""));
        }
        String number = num.toString();
        int total = 0;
        for(int i = 0; i < number.length(); i++){
            total += Integer.parseInt(number.charAt(i) + "");
        }
        return total;
    }

    //Helper functions
    String stringRepresentation(int n){
        //String representation of numbers
        String[] lessThanTen = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] tenToTwenty = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] multsOfTen = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String hundred = "hundred";
        String thousand = "thousand";
        String million = "million";
        String billion = "billion";
        //Return String representation of Input
        if(n < 10){
            return lessThanTen[n];
        } else if(n > 10 && n <= 19){
            return tenToTwenty[n - 10];
        } else if(n < 100){
            if(n % 10 == 0){
                return multsOfTen[n/10];
            }
            return multsOfTen[n/10] + ((n % 10 != 0) ? lessThanTen[n % 10] : "");
        } else if(n >= 100 && n < 1000){
            return lessThanTen[n/100] + hundred + ((n % 100 != 0) ? "and" + stringRepresentation(n % 100) : "");
        } else if(n >= 1000 && n < 1000000){
            return stringRepresentation(n/1000) + thousand + ((n % 1000 != 0) ? stringRepresentation(n % 1000) : "");
        } else if(n >= 1000000 && n < 1000000000){
            return stringRepresentation(n/1000000) + million + ((n % 1000000 != 0) ? stringRepresentation(n % 1000000) : "");
        } else if(n == 1000000000){
            return billion;
        }
        return "";
    }

    int[] bigNumber(int base, int exp){
        //Number of digits of base^exp = 1 + exp(log10 base)
        int numDigits = (int)(1 + exp*(Math.log10(base)));
        int[] digits = new int[numDigits];
        digits[0] = base;
        int exponent = 1;
        int highDigitIndex = 0;
        while(exponent < exp){
            exponent++;
            for(int i = 0; i < highDigitIndex + 1; i++){
                digits[i] *= 2;
            }
            for(int i = 0; i < highDigitIndex + 1; i++){
                if(digits[i] >= 10){
                    digits[i + 1] += 1;
                    digits[i] -= 10;
                    if(i == highDigitIndex){
                        highDigitIndex += 1;
                    }
                }
            }
        }
        return digits;
    }

    int emptySpot(int[] array){
        int index = 0;
        for(int i : array){
            if(i != 0)
                index++;
            else
                return index;
        }
        return -1;
    }

    int search(int[] array, int element){
        int low = 0;
        int high = array.length - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(array[mid] == element)
                return mid;
            if (array[mid] < element)
                low = mid + 1;
            else   
                high = mid - 1;
        }
        return -1;
    }

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

    //Main Method
    public static void main(String[] args) {
        
        Twenty solution = new Twenty();

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
        //Problem 13
        String[] largeNums = {
            "37107287533902102798797998220837590246510135740250",
            "46376937677490009712648124896970078050417018260538",
            "74324986199524741059474233309513058123726617309629",
            "91942213363574161572522430563301811072406154908250",
            "23067588207539346171171980310421047513778063246676",
            "89261670696623633820136378418383684178734361726757",
            "28112879812849979408065481931592621691275889832738",
            "44274228917432520321923589422876796487670272189318",
            "47451445736001306439091167216856844588711603153276",
            "70386486105843025439939619828917593665686757934951",
            "62176457141856560629502157223196586755079324193331",
            "64906352462741904929101432445813822663347944758178",
            "92575867718337217661963751590579239728245598838407",
            "58203565325359399008402633568948830189458628227828",
            "80181199384826282014278194139940567587151170094390",
            "35398664372827112653829987240784473053190104293586",
            "86515506006295864861532075273371959191420517255829",
            "71693888707715466499115593487603532921714970056938",
            "54370070576826684624621495650076471787294438377604",
            "53282654108756828443191190634694037855217779295145",
            "36123272525000296071075082563815656710885258350721",
            "45876576172410976447339110607218265236877223636045",
            "17423706905851860660448207621209813287860733969412",
            "81142660418086830619328460811191061556940512689692",
            "51934325451728388641918047049293215058642563049483",
            "62467221648435076201727918039944693004732956340691",
            "15732444386908125794514089057706229429197107928209",
            "55037687525678773091862540744969844508330393682126",
            "18336384825330154686196124348767681297534375946515",
            "80386287592878490201521685554828717201219257766954",
            "78182833757993103614740356856449095527097864797581",
            "16726320100436897842553539920931837441497806860984",
            "48403098129077791799088218795327364475675590848030",
            "87086987551392711854517078544161852424320693150332",
            "59959406895756536782107074926966537676326235447210",
            "69793950679652694742597709739166693763042633987085",
            "41052684708299085211399427365734116182760315001271",
            "65378607361501080857009149939512557028198746004375",
            "35829035317434717326932123578154982629742552737307",
            "94953759765105305946966067683156574377167401875275",
            "88902802571733229619176668713819931811048770190271",
            "25267680276078003013678680992525463401061632866526",
            "36270218540497705585629946580636237993140746255962",
            "24074486908231174977792365466257246923322810917141",
            "91430288197103288597806669760892938638285025333403",
            "34413065578016127815921815005561868836468420090470",
            "23053081172816430487623791969842487255036638784583",
            "11487696932154902810424020138335124462181441773470",
            "63783299490636259666498587618221225225512486764533",
            "67720186971698544312419572409913959008952310058822",
            "95548255300263520781532296796249481641953868218774",
            "76085327132285723110424803456124867697064507995236",
            "37774242535411291684276865538926205024910326572967",
            "23701913275725675285653248258265463092207058596522",
            "29798860272258331913126375147341994889534765745501",
            "18495701454879288984856827726077713721403798879715",
            "38298203783031473527721580348144513491373226651381",
            "34829543829199918180278916522431027392251122869539",
            "40957953066405232632538044100059654939159879593635",
            "29746152185502371307642255121183693803580388584903",
            "41698116222072977186158236678424689157993532961922",
            "62467957194401269043877107275048102390895523597457",
            "23189706772547915061505504953922979530901129967519",
            "86188088225875314529584099251203829009407770775672",
            "11306739708304724483816533873502340845647058077308",
            "82959174767140363198008187129011875491310547126581",
            "97623331044818386269515456334926366572897563400500",
            "42846280183517070527831839425882145521227251250327",
            "55121603546981200581762165212827652751691296897789",
            "32238195734329339946437501907836945765883352399886",
            "75506164965184775180738168837861091527357929701337",
            "62177842752192623401942399639168044983993173312731",
            "32924185707147349566916674687634660915035914677504",
            "99518671430235219628894890102423325116913619626622",
            "73267460800591547471830798392868535206946944540724",
            "76841822524674417161514036427982273348055556214818",
            "97142617910342598647204516893989422179826088076852",
            "87783646182799346313767754307809363333018982642090",
            "10848802521674670883215120185883543223812876952786",
            "71329612474782464538636993009049310363619763878039",
            "62184073572399794223406235393808339651327408011116",
            "66627891981488087797941876876144230030984490851411",
            "60661826293682836764744779239180335110989069790714",
            "85786944089552990653640447425576083659976645795096",
            "66024396409905389607120198219976047599490197230297",
            "64913982680032973156037120041377903785566085089252",
            "16730939319872750275468906903707539413042652315011",
            "94809377245048795150954100921645863754710598436791",
            "78639167021187492431995700641917969777599028300699",
            "15368713711936614952811305876380278410754449733078",
            "40789923115535562561142322423255033685442488917353",
            "44889911501440648020369068063960672322193204149535",
            "41503128880339536053299340368006977710650566631954",
            "81234880673210146739058568557934581403627822703280",
            "82616570773948327592232845941706525094512325230608",
            "22918802058777319719839450180888072429661980811197",
            "77158542502016545090413245809786882778948721859617",
            "72107838435069186155435662884062257473692284509516",
            "20849603980134001723930671666823555245252804609722",
            "53503534226472524250874054075591789781264330331690"
        };
        System.out.println("P13: " + solution.largeSum(largeNums, 10));
        //Problem 14
        System.out.println("P14: " + solution.longestCollatzSequence(1000000));
        //Problem 15
        System.out.println("P15: " + solution.latticePaths(20));
         //Problem 16
         System.out.println("P16: " + solution.powerDigitSum(2, 1000));
         //Problem 17
         System.out.println("P17: " + solution.numberLetterCounts(0, 1000));
         //Problem 18
         int[][] triangle = {
            {75},
            {95, 64},
            {17, 47, 82},
            {18, 35, 87, 10},
            {20, 4, 82, 47, 65},
            {19, 1, 23, 75, 3, 34},
            {88, 02, 77, 73, 7, 63, 67},
            {99, 65, 4, 28, 6, 16, 70, 92},
            {41, 41, 26, 56, 83, 40, 80, 70, 33},
            {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
            {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
            {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
            {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
            {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
            {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}
         };
         System.out.println("P18: " + solution.maximumPathSum(triangle));
         //Problem 19
         System.out.println("P19: " + solution.countingSundays(31, 12, 2000));
         //Problem 20
         System.out.println("P20: " + solution.factorialDigitSum(100));
    }

}
