package javasem01;

import java.lang.reflect.Array;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaSem01 {
    private static final double GRAVITY = -9.81;
    private static int factorial = 1;

    public static void main(String[] args) throws Exception {
        String[] names = { "Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt",
                "Alex", "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda", "Aaron",
                "Kate" };
        int[] times = { 341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393,
                299, 343, 317, 265 };
        if (names.length != times.length)
            throw new Exception("Incorrect number of array elements");

        for (byte i = 0; i < Array.getLength(names); i++)
            System.out.println("[" + names[i] + ", <" + times[i] + ">]");
        try {
            System.out.println(positionCalc(10, 0, 100));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            System.out.println(factorialForLoop(6));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            System.out.println(factorialRecursive(6));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        double[] igo = generateArray(10, 100, 200);
        printArray(igo);
        double mean = getMean(igo);
        System.out.println("MEAN: " + mean);
        double min = getMin(igo);
        System.out.println("Min: " + min);
        double max = getMax(igo);
        System.out.println("MAX: " + max);
        double[] sorted_igo = arraySort(igo);
        printArray(sorted_igo);
        System.out.println("Attempts to roll 12 with 2 dices:" + roll2Dices());
        byte[] test_array = { 72, 101, 108, 108, 111, 33, 32, 77, 121, 32, 115, 107, 105, 108, 108, 115, 32, 97, 114,
                101,
                32, 103, 114, 101, 97, 116, 32, 97, 108, 114, 101, 97, 100, 121, 33 };
        String str = getTextFromBytes(test_array);
        System.out.println(str);
        System.out.println("PASCAL: " + pascalsTriangle(6));
        String equation = "2 * (3 + 4)";
        System.out.println("Result: " + executeStringEquation(equation));
        // Set<Character> resultSet = performOperations(input);
        // System.out.println("Result Set: " + resultSet);
        System.out.println(setOperations("[1, 2, 3]+ [3, 4, 5]"));
        System.out.println(setOperations("[0, 9, 8, 7] * [2, 4, 6, 8]"));
        System.out.println(setOperations("[5, 10, 15, 20] - [10, 20, 80]"));

    }

    public static double positionCalc(double initialVelocity, double initialPosition, double fallingTime)
            throws Exception {
        if (initialVelocity >= 0 && initialVelocity <= 299792458 && initialPosition >= 0 && initialPosition < 20000
                && fallingTime >= 0 && fallingTime < 8000) {
            return (double) 0.5 * GRAVITY * Math.pow(fallingTime, 2) + initialVelocity * fallingTime + initialPosition;
        } else {
            throw new Exception("Incorrect input parameters");
        }
    }

    public static int factorialForLoop(int N) throws Exception {
        if (N < 0)
            throw new Exception("Factorial cannot be negative");
        if (N == 0 || N == 1)
            return factorial;

        for (int i = 2; i <= N; i++)
            factorial *= i;
        return factorial;
    }

    public static int factorialRecursive(int N) throws Exception {
        if (N < 0)
            throw new Exception("Factorial cannot be negative");
        if (N == 0 || N == 1)
            return 1;
        return N * factorialRecursive(N - 1);
    }

    public static double[] generateArray(int N, double lower, double upper) throws Exception {
        if (N <= 0) {
            return new double[0]; // Return an empty array if N is non-positive
        }

        double[] rand_array = new double[N];
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            rand_array[i] = lower + (upper - lower) * rand.nextDouble();
        }

        return rand_array;
    }

    public static double getMean(double[] array) throws Exception {
        double sum = 0.0;
        for (int i = 0; i < array.length - 1; i++)
            sum += array[i];
        return sum / array.length;
    }

    public static double getMin(double[] array) throws Exception {
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }

    public static double getMax(double[] array) throws Exception {
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    public static double[] arraySort(double[] array) throws Exception {
        double temp;
        boolean swapped = false;
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            swapped = false;
            for (int j = 0; j < n - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
        return array;
    }

    public static void printArray(double arr[]) throws Exception {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static double[][] generateMatrix(int N, double lower, double upper) throws Exception {
        if (N <= 0 || (upper < lower))
            throw new Exception("Wrong input parameters");

        double[][] rand_array = new double[N][N];
        Random rand = new Random();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                rand_array[i][j] = lower + (upper - lower) * rand.nextDouble();
        return rand_array;
    }

    public static double getProduct(double[][] matrix, int i, int j) throws Exception {
        double product = 1.0;
        if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
            for (int col = 0; col < matrix[i].length; col++) {
                product *= matrix[i][col];
            }
            for (int row = 0; row < matrix.length; row++) {
                if (row != i) {
                    product *= matrix[row][j];
                }
            }
        } else {
            throw new Exception("Invalid indices");
        }
        return product;
    }

    public static double[] coinFlip(int N) throws Exception {
        Random rand = new Random();
        double[] rand_array = new double[3];
        if (rand.nextBoolean())
            rand_array[1] += 1;
        else
            rand_array[0] += 1;
        rand_array[2] = rand_array[0] / rand_array[1];
        return rand_array;
    }

    public static int[] rollDice(int N) throws Exception {
        if (N < 0)
            throw new Exception("Can't roll negative times dice");
        if (N == 0)
            throw new Exception("Don't want to roll the dice - DON'T CALL ME");
        Random rand = new Random();
        int[] array = new int[3];
        for (int i = 0; i < N; i++)
            array[rand.nextInt(6)]++;
        return array;
    }

    public static int roll2Dices() {
        int sum, attempts = 0;
        do {
            Random rand = new Random();
            sum = (1 + rand.nextInt(6)) + (1 + rand.nextInt(6));
            attempts++;
        } while (sum != 12);
        return attempts;
    }

    public static String getTextFromBytes(byte[] array) throws Exception {
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 127 && array[i] < 32)
                throw new Exception("Can't convert bytes to ascii outside of 'printable' range");
        }
        return new String(array);
    }

    public static String pascalsTriangle(int level) throws Exception {
        if (level < 0) {
            throw new Exception("There is no negative levels in pascal - starts from 0 to INFINITY");
        }
        String triangle = new String();
        try {
            for (int j = 0; j <= level; j++) {
                triangle += factorialRecursive(level) / (factorialRecursive(j) * factorialRecursive(level - j)) + " ";
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return triangle;
    }
    // got information from (soon will be outdated):
    // https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form
    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/instanceof
    public static double executeStringEquation(String inputEquation) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            Object result = engine.eval(inputEquation);
            if (result instanceof Integer) {
                return ((Integer) result).doubleValue();
            } else if (result instanceof Double) {
                return (Double) result;
            } else {
                throw new Exception("Invalid result type: " + result.getClass());
            }
        } catch (ScriptException e) {
            throw new Exception("Invalid equation: " + inputEquation, e);
        }
    }

    public static String setOperations(String input) throws Exception {
        input = input.replaceAll("\\[|\\]|\\s+", "");
        int operatorIndex = input.indexOf("+");
        operatorIndex = operatorIndex != -1 ? operatorIndex : input.indexOf("*");
        operatorIndex = operatorIndex != -1 ? operatorIndex : input.indexOf("-");
        char operator = operatorIndex != -1 ? input.charAt(operatorIndex) : ' ';
        String[] parts = input.split("[-+*]");
        switch (operator) {
            case '+':
                return union(parts[0], parts[1]);
            case '*':
                return intersection(parts[0], parts[1]);
            case '-':
                return difference(parts[0], parts[1]);
            default:
                throw new Exception("Invalid operation: " + operator);
        }
    }

    public static String union(String part1, String part2) {
        String[] char1 = part1.split(",");
        String[] char2 = part2.split(",");
        int newSize = char1.length + char2.length;
        String[] unifiedBytes = new String[newSize];
        System.arraycopy(char1, 0, unifiedBytes, 0, char1.length);
        System.arraycopy(char2, 0, unifiedBytes, char1.length, char2.length);
        for (int i = 0; i < newSize - 1; i++) {
            for (int j = i + 1; j < newSize;) {
                if (unifiedBytes[i].equals(unifiedBytes[j])) {
                    for (int k = j; k < newSize - 1; k++) {
                        unifiedBytes[k] = unifiedBytes[k + 1];
                    }
                    newSize--;
                } else {
                    j++;
                }
            }
        }
        String[] finalUnifiedBytes = new String[newSize];
        System.arraycopy(unifiedBytes, 0, finalUnifiedBytes, 0, newSize);
        String result = new String("[" + String.join(", ", finalUnifiedBytes) + "]");
        return result;
    }

    public static String intersection(String part1, String part2) {
        String[] char1 = part1.split(",");
        String[] char2 = part2.split(",");
        String[] unifiedBytes = new String[char1.length+char2.length];
        int newSize = 0;
        for(int i = 0; i < char1.length; i++) {
            for(int j = 0; j < char2.length; j++){
                if(char1[i].equals(char2[j])){
                    unifiedBytes[newSize] = char1[i];
                    newSize++;
                }
            } 
        }
        String[] finalUnifiedBytes = new String[newSize];
        System.arraycopy(unifiedBytes, 0, finalUnifiedBytes, 0, newSize);
        return new String("[" + String.join(", ", finalUnifiedBytes) + "]");
    }

    public static String difference(String part1, String part2) {
        String[] char1 = part1.split(",");
        String[] char2 = part2.split(",");
        int[] indexToRemove1 = new int[char1.length];
        int[] indexToRemove2 = new int[char2.length];
        int index = 0;
        for (int i = 0; i < char1.length; i++) {
            for (int j = 0; j < char2.length; j++) {
                if (char1[i].equals(char2[j])) {
                    indexToRemove1[index] = i;
                    indexToRemove2[index] = j;
                    index++;
                }
            }
        }
        String[] charFinal1 = removeTheElement(char1, index, indexToRemove1);
        String[] charFinal2 = removeTheElement(char2, index, indexToRemove2);
        return new String("[" + String.join(", ", charFinal1) + ", " + String.join(", ", charFinal2) + "]");
    }

    // Function to remove the element <- https://www.geeksforgeeks.org/remove-an-element-at-specific-index-from-an-array-in-java/
    public static String[] removeTheElement(String[] arr, int count, int[] index) { 
        if (arr == null || count > arr.length) {
            return arr; 
        } 
        for (int i = 0; i < count; i++) {
            if(index[i] < 0 || index[i] >= arr.length)
                return arr;
        }

        // Create another array of size one less 
        String[] anotherArray = new String[arr.length - count]; 
        boolean skip = false;
        // Copy the elements except the index 
        // from original array to the other array 
        for (int i = 0, k = 0; i < arr.length; i++) { 
  
            // if the index is 
            // the removal element index
            for(int j = 0; j < count; j++){
                if (i == index[j]) { 
                    skip = true; 
                }
            }
            if(skip){
                skip = false;
                continue;
            }
            // if the index is not 
            // the removal element index 
            anotherArray[k++] = arr[i]; 
        } 
  
        // return the resultant array 
        return anotherArray; 
    } 
}