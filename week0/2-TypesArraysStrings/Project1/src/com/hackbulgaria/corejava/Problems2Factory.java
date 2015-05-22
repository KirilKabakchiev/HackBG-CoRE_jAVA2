package com.hackbulgaria.corejava;

import static org.junit.Assert.assertEquals;

public class Problems2Factory {
    String OATH_TOKEN = "THIS IS MY DEAR PASSWORD";

    public static Problems2 createProblems2Instance() {
        return new Problems2Impl();
    }

    public static void main(String[] args) {
       System.out.println("hello");
           Problems2 caller = new Problems2Impl();
//        System.out.println("isOdd main call: " + caller.isOdd(5));  
//        System.out.println("isPrime main call: " + caller.isPrime(5)); 
//        System.out.println("doubleFact main call: " + caller.doubleFac(3));
//        
//        int[] numbers = new int[] { 1, 1, 3, 3, 6, 5, 4, 6, 5, 9, 1, 1, 3, 15, 4, 5, 9, 3, 15 };
//        System.out.println("doubleFact main call: " + caller.getOddOccurrence(numbers));
//        
       // System.out.println(new Problems2Impl().removeChar("abccd", 'c'));
    //    System.out.println("asdasda" + caller.getPalindromeLength("taz*zad"));
//        assertEquals(true, problems2.areAnagrams("BRADE", "BEARD"));
//        assertEquals(true, problems2.areAnagrams("TOP CODER", "COTE PROD"));
//        assertEquals(false, problems2.areAnagrams("TOP CODER", "COTO PRODE")););
         
    }

    public static void meh(Integer k) {
    }
}
