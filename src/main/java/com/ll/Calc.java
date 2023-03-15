package com.ll;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {
    public static int run(String exp){
        exp = exp.trim();
        exp = removeOuterBracket(exp);

        if (!exp.contains(" ")) return Integer.parseInt(exp);

        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+") || exp.contains("-");
        boolean needToCompound = needToMulti && needToPlus;
        boolean needToSplit = exp.contains("(") || exp.contains(")");

        if( needToSplit ) {

            int bracketStart = 0;
            int bracketEnd = 0;
            for(int i=0; i<exp.length(); i++){
                if(exp.charAt(i) == '('){
                    bracketStart = i;
                    break;
                }
            }

            for(int i=exp.length()-1; i>=0; i--){
                if(exp.charAt(i) == ')') {
                    bracketEnd = i;
                    break;
                }
            }

            if(bracketStart != 0){
                exp = exp.substring(0, bracketStart) + Calc.run(exp.substring(bracketStart+1, bracketEnd));
            } else exp = Calc.run(exp.substring(1, bracketEnd)) + exp.substring(bracketEnd+1);

            return Calc.run(exp);

        } else if ( needToCompound ) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newExp);
        } else if ( needToPlus ) {
            exp = exp.replaceAll("- ", "+ -");
            String[] bits = exp.split(" \\+ ");

            int sum = 0;
            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }
            return sum;
        } else if ( needToMulti ) {
            String[] bits = exp.split(" \\* ");

            int sum = 1;
            for (int i = 0; i < bits.length; i++) {
                sum *= Integer.parseInt(bits[i]);
            }
            return sum;
        }

        throw new RuntimeException("올바른 계산식이 아닙니다.");
    }

    public static String removeOuterBracket(String exp){
        while(exp.charAt(0) == '(' && exp.charAt(exp.length()-1) == ')'){
            exp = exp.substring(1, exp.length()-1);
        }
        return exp;
    }
}
