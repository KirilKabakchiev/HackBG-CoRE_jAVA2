package com.hackbulgaria.corejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problems2Impl implements Problems2 {

    @Override
    public boolean isOdd(int number) {
        return number % 2 != 0;
    }

    @Override
    public boolean isPrime(int number) {
        if (number % 2 == 0)
            return false;
        for (int i = 3; i * i <= number; i += 2)
            if (number % i == 0)
                return false;
        return true;
    }

    @Override
    public int min(int... array) {
        return Arrays.stream(array).min().getAsInt();
    }

    @Override
    public int kthMin(int k, int[] array) {
        Arrays.sort(array);
        return array[k - 1];
    }

    @Override
    public float getAverage(int[] array) {
        return (float) Arrays.stream(array).average().getAsDouble();
    }

    @Override
    public long getSmallestMultiple(int upperBound) {
        boolean flag = true;
        int number = 1;
        while (true) {
            flag = true;
            for (int i = upperBound; i > 0 && flag; i--) {
                if (number % i != 0) {
                    flag = false;
                }
            }
            if (flag == true) {
                return number;
            }
            number++;
        }
    }

    // ako upperbound e 7
    // 1 2 3 4 5 6 7 | 2
    // 1 1 3 2 5 3 7 | 2
    // 1 1 3 1 5 3 7 | 3
    // | 3
    // | 5
    // | 7
    @Override
    public long getLargestPalindrome(long N) {
        for (long i = N; i > 0; i--) {
            if (isPalindrome((int) i)) {
                return (Long) i;
            }
        }
        return 0L;
    }

    @Override
    public int[] histogram(short[][] image) {
        int[] result = new int[256];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                result[image[i][j]]++;
            }
        }
        return result;
    }

    @Override
    public long doubleFac(int n) {
        long result = 1L;
        for (int i = n; i > 0; i--) {
            result *= i;
        }
        // System.out.println(result);
        for (int j = (int) result - 1; j > 0; j--) {
            result *= j;
        }
        // System.out.println(result);
        return result;
    }

    @Override
    public long kthFac(int k, int n) {
        long result = n;
        for (int j = 0; j < k; j++) {
            for (long i = result - 1; i > 0; i--) {
                result *= i;
            }
        }
        return result;
    }

    @Override
    public int getOddOccurrence(int[] array) {
        // HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> asList = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        Set<Integer> mySet = new HashSet<>(asList);
        for (Integer s : mySet) {
            int count = Collections.frequency(asList, s);
            // map.put(s, count);
            System.out.println(count);
            if (count % 2 != 0) {
                System.out.println(" dasdadsa" + s);
                return s;
            }
        }
        return 0;
    }

    @Override
    public long pow(int a, int b) {
        long temp;
        if (b == 0) {
            return 1;
        }
        temp = pow(a, b / 2);
        if (b % 2 == 0) {
            return temp * temp;

        } else {
            if (b > 0)
                return a * temp * temp;
            else {
            }
            return (temp * temp) / a;
        }
    }

    @Override
    public long maximalScalarSum(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    @Override
    public int maxSpan(int[] ints) {
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(); // use
                                                                           // TIntIntHashMap
                                                                           // for
                                                                           // efficiency.
        int maxspan = 0; // max span so far.
        for (int i = 0; i < ints.length; i++) {
            int num = ints[i];
            if (map.containsKey(num)) { // have we seen this number before?
                int span = i - map.get(num) + 1; // num has been found so what
                                                 // is the span
                if (span > maxspan)
                    maxspan = span; // if the span is greater, update the
                                    // maximum.
            } else {
                map.put(num, i); // first occurrence of number num at location
                                 // i.
            }
        }
        return maxspan;
    }

    @Override
    public boolean canBalance(int[] array) {
        int rightSum = Arrays.stream(array).sum();
        // edna za cqlata suma i edna lqva suma - dobavqme v left izvajdame ot
        int leftSum = 0;
        // right (koqto e max) dokato se izravnqt
        for (int i = 0; i < array.length; i++) {
            leftSum += array[i];
            rightSum -= array[i];
            if (leftSum == rightSum) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int[][] rescale(int[][] original, int newWidth, int newHeight) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String reverseMe(String argument) {
        return new StringBuilder(argument).reverse().toString();
    }

    @Override
    public String copyEveryChar(String input, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < k; j++) {
                sb.append(input.charAt(i));
            }
        }

        if (Character.isUpperCase(sb.toString().charAt(0))) {
            String temp = sb.toString();
            return temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
        }
        return sb.toString();

    }

    @Override
    public String reverseEveryWord(String arg) {
        String[] temp = arg.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            temp[i] = reverseMe(temp[i]);
            sb.append(temp[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public boolean isPalindrome(String argument) {

        return argument.equals(reverseMe(argument));
    }

    @Override
    public boolean isPalindrome(int number) {
        int reverse = 0;
        int n = number;

        while (n != 0) {
            reverse = reverse * 10;
            reverse = reverse + n % 10;
            n = n / 10;
        }

        if (number == reverse) {
            return true;
        }
        return false;
    }

    @Override
    public int getPalindromeLength(String input) {
        int k = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '*') {
                System.out.println(i);
                k = i;
            }
        }
        boolean flag = true;
        int j = k, t = k, count = 0;
        while (flag && j > 0 && t < input.length()) {
            // System.out.println(input.charAt(j - 1) + " " + input.charAt(t +
            // 1));
            if (input.charAt(j - 1) == input.charAt(t + 1)) {
                j--;
                t++;
                count++;
            } else {
                flag = false;
            }
        }
        return count;
    }

    @Override
    public int countOcurrences(String needle, String haystack) {
        return haystack.split(needle, -1).length - 1;
    }

    @Override
    public String decodeURL(String input) {
        return input.replaceAll("%20", "").replaceAll("%3A", ":").replaceAll("%3D", "?").replaceAll("%2F", "/");
    }

//    private String deleteAllNonDigit(String s) {
//        String temp = s.replaceAll("\\D", " ");
//        String temp2 = temp.trim().replaceAll(" +", " ");
//        return temp2;
//    }

    @Override
    public int sumOfNumbers(String input) {
        int sum = 0;
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(input);
        while (m.find()) {
            String group = m.group();
            sum += Integer.parseInt(group);
        }
        return sum;
    }

    public String removeChar(String s, char c) {
        StringBuffer buf = new StringBuffer(s.length());
        // System.out.println(buf.toString());
        boolean flag = false;
        // buf.setLength(s.length());
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur != c) {
                buf.append(cur);
            } else if (cur == c && flag == false) {
                flag = true;
            } else if (cur == c && flag == true) {
                buf.append(cur);
            }
        }
        // System.out.println(buf.toString());
        return buf.toString();
    }

    @Override
    public boolean areAnagrams(String A, String B) {
        String Bcopy = B;
        for (int i = 0; i < A.length(); i++) {

            if (A.charAt(i) != ' ') {
                if (!Bcopy.contains(Character.toString(A.charAt(i)))) {
                    return false;
                } else {
                    Bcopy = new String(removeChar(Bcopy, A.charAt(i)));
                }
            }
        }

        String Acopy = A;
        for (int i = 0; i < B.length(); i++) {
            if (B.charAt(i) != ' ') {

                if (!Acopy.contains(Character.toString(B.charAt(i)))) {
                    return false;
                } else {
                    Acopy = new String(removeChar(Acopy, B.charAt(i)));
                }
            }
        }

        return true;
    }

    @Override
    public boolean hasAnagramOf(String string, String string2) {
        for (int i = 0; i <= string2.length() - string.length(); i++) {
            if (areAnagrams(string, string2.substring(i, i + string.length())))
                return true;
        }
        return false;
    }

}
