package com.ll;

public class Calc {
    public static int run(String s){
        String[] bits = s.split(" ");
        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[2]);
        String operator = bits[1];

        if(operator.equals("+")) return a+b;
        else if (operator.equals("-")) return a-b;

        return -1;
    }
}
