package com.ll;

public class Calc {
    public static int run(String s){
        String[] bits = s.split(" ");
        int result = 0;

        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[2]);
        String operator = bits[1];

        if(operator.equals("+")) result = a+b;
        else if (operator.equals("-")) result = a-b;

        if(bits.length == 3) return result;

        String operator2 = bits[3];
        int c = Integer.parseInt(bits[4]);

        if(operator2.equals("+")) return result + c;
        else if (operator2.equals("-")) return result - c;

        return -1;
    }
}
