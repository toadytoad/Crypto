package XOR;

import Bases.Base;

import java.util.*;

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
        s = Base.fromHex(s);
        return crackSingleXOR(s);
    }
    public static Queue<Result> crackSingleXOR(String s){
        Queue<Result> q = new PriorityQueue<>();
        for(int i = 0; i<256; i++){
            String n = singleXOR(s, (char)i);
            q.add(new Result(n).setChar((char)i));
        }
        return q;
    }
    private static int bitsIn(char c){
        String b = Integer.toBinaryString(c);
        int total = 0;
        for(char i : b.toCharArray()){
            if(i=='1') total++;
        }
        return total;
    }
    public static int getHammingDistance(String a, String b){
        if(a.length()!=b.length()) throw new IllegalArgumentException("Strings not of same length: "+a.length()+" vs. "+b.length());
        String s = stringKeyXOR(a, b);
        int total = 0;
        for(char i : s.toCharArray()){
            total+=bitsIn(i);
        }
        return total;
    }
    public static String getKeyStream(String key, int len){
        StringBuilder sb = new StringBuilder();
        while(sb.length()+key.length()<=len){
            sb.append(key);
        }
        sb.append(key, 0, len%key.length());
        return sb.toString();
    }
    public static String stringKeyXOR(String s, String key){
        String keyStream = getKeyStream(key, s.length());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            sb.append((char)(keyStream.charAt(i)^s.charAt(i)));
        }
        return sb.toString();
    }
    public static String singleXOR(String s, char c){
        StringBuilder sb = new StringBuilder();
        for(char i : s.toCharArray()){
            sb.append((char)(i^c));
        }
        return sb.toString();
    }
    public static Queue<Result> multiXORBruteForce(String s, int hammingDepth){
        Queue<Pair> q = new PriorityQueue<>();
        for(int keySize = 2; keySize<=40&&keySize<=s.length()/2; keySize++){
            q.add(new Pair(multiXOR1(s, keySize), keySize));
        }
        Queue<Result> r = new PriorityQueue<>();
        for(int z = 0; z<hammingDepth; z++){
            Pair p = q.poll();
            r.add(multiXOR2(s, p.keySize));
        }
        return r;
    }

    private static double multiXOR1(String s, int keySize){
        String a = s.substring(0, keySize);
        String b = s.substring(keySize, 2*keySize);
        return (double)getHammingDistance(a, b)/keySize;
    }
    private static Result multiXOR2(String s, int keySize){
        String[] chunks = new String[keySize];
        for(int i = 0; i<s.length(); i++){
            chunks[i%keySize]+=s.charAt(i);
        }
        StringBuilder key = new StringBuilder();
        for(String i : chunks){
            Queue<Result> r = crackSingleXOR(i);
            while(r.peek()!=null){
                if(r.peek().c>='~'||r.peek().c<=' '){
                    r.poll();
                } else break;
            }
            key.append(r.poll().c);
        }
        return new Result(key.toString());
    }
    public static double score(String s){
        int len = s.length();
        double score = 0;
        for(char i : s.toCharArray()){
            if(i>=' '&&i<='~') {
                if(i>='a'&&i<='z'||i>='A'&&i<='Z') score+=4;
                else if(i>='0'&&i<='9') score+=2;
                else if(i==' '||i=='_'||i=='{'||i=='}')score+=2.5;
                else score+=0.5;
            } else {
                score-=5;
            }

        }
        return score/len*100;
    }

    static class Pair implements Comparable<Pair>{
        double val;
        int keySize;
        public Pair(double val, int keySize){
            this.val=val;
            this.keySize=keySize;
        }
        @Override
        public int compareTo(Pair o) {
            return Double.compare(this.val, o.val);
        }
    }
}
