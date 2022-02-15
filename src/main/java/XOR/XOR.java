package XOR;

import Bases.Base;

public class XOR {
    private static final char[] hexChars = Base.initHex();
    public static String sameLengthHexXOR(String a, String b){
        if(a.length()!=b.length()){
            throw new IllegalArgumentException("Strings not of same length: "+a.length()+" vs. "+b.length());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<a.length(); i++){
            sb.append(hexChars[Integer.parseInt(a.substring(i, i+1), 16)^Integer.parseInt(b.substring(i, i+1), 16)]);
        }
        return sb.toString();
    }
    public static String crackSingleHexXOR(String s){
        if(!Base.isValidHex(s)){
            throw new IllegalArgumentException("String is not a valid hexadecimal string");
        }
        String best = "";
        double max = 0;
        for(int i = 0; i<256; i++){
            String n = singleHexXOR(s, (char)i);
            double a = score(n);
            if(a>max){
                best = n;
                max = a;
            }
        }
        return best;
    }

    public static String singleHexXOR(String s, char c){
        String n = Base.fromHex(s);
        StringBuilder sb = new StringBuilder();
        for(char i : n.toCharArray()){
            sb.append((char)(i^c));
        }
        return sb.toString();
    }
    //TODO make better scoring system, where if a string ends up having only hex or base 64 characters etc, excluding delimeters (' ', ',', etc..).
    //TODO Possibly make a new class which has several different types of scoring system for entropy.
    public static double score(String s){
        int len = s.length();
        double score = 0;
        for(char i : s.toCharArray()){

                if(i<='z'&&i>='a'||i<='Z'&&i>='A') score++;

        }
        return score/len*100;
    }
}
