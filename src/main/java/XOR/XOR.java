package XOR;

import Bases.Base;

import java.util.PriorityQueue;
import java.util.Queue;

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
    public static Queue<Result> crackSingleHexXOR(String s){
        if(!Base.isValidHex(s)){
            throw new IllegalArgumentException("String is not a valid hexadecimal string");
        }
        Queue<Result> q = new PriorityQueue<>();
        for(int i = 0; i<256; i++){
            String n = singleHexXOR(s, (char)i);
            q.add(new Result(n));
        }
        return q;
    }
    public static String getKeyStream(String key, int len){
        StringBuilder sb = new StringBuilder();
        while(sb.length()+key.length()<=len){
            sb.append(key);
        }
        sb.append(key, 0, len%key.length());
        return sb.toString();
    }
    public static String StringKeyXOR(String s, String key){
        String keyStream = getKeyStream(key, s.length());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            sb.append((char)(keyStream.charAt(i)^s.charAt(i)));
        }
        return sb.toString();
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
            if(i>=' '&&i<='~') {
                if(i>='a'&&i<='z'||i>='A'&&i<='Z') score+=3;
                else if(i>='0'&&i<='9') score+=2;
                else if(i==' '||i=='_'||i=='{'||i=='}')score+=1.5;
                else score++;
            } else {
                score--;
            }

        }
        return score/len*100;
    }
}
