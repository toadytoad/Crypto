package Bases;

import java.util.HashSet;
import java.util.Set;

public class Base {
    private static final char[] base64Chars = initBase64();
    private static final char[] hexChars = initHex();
    public static char[] initHex() {
        char[] arr = new char[16];
        int ind = 0;
        for(char i = '0'; i<='9'; i++){
            arr[ind] = i;
            ind++;
        }
        for(char i = 'a'; i<='f'; i++){
            arr[ind] = i;
            ind++;
        }
        return arr;
    }
    public static char[] initBase64(){
        int ind = 0;
        char[] arr = new char[64];
        for(char i = 'A'; i<='Z'; i++){
            arr[ind] = i;
            ind++;
        }
        for(char i = 'a'; i<='z'; i++){
            arr[ind] = i;
            ind++;
        }
        for(char i = '0'; i<='9'; i++){
            arr[ind] = i;
            ind++;
        }
        arr[62] = '+';
        arr[63] = '/';
        return arr;
    }
    public static boolean isValidHex(String s){
        for( char i : s.toCharArray()){
            if(!(i<='9'&&i>='0')&&!(i<='f'&&i>='a')) return false;
        }
        return true;
    }

    public static String toHex(String s){
        StringBuilder sb = new StringBuilder();
        for(char i : s.toCharArray()){
            String chunk = Integer.toHexString(i);
            if(chunk.length()==1) chunk = '0'+chunk;
            sb.append(chunk);
        }
        return sb.toString();
    }

    public static String fromHex(String s){
        if(!isValidHex(s)||s.length()%2==1){
            throw new IllegalArgumentException("String is not a valid hexadecimal string");
        }
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<s.length(); i+=2){
            sb.append((char)Integer.parseInt(s.substring(i, i+2), 16));
        }
        return sb.toString();
    }
    public static String hexToBase64(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i+=3){
            String chunk = Integer.toBinaryString(Integer.parseInt(s.substring(i, i + 3), 16));
            int initLen = chunk.length();
            for(int j = 0; j<12-initLen; j++){
                chunk = '0'+chunk;
            }
            sb.append(base64Chars[Integer.parseInt(chunk.substring(0, 6), 2)]);
            sb.append(base64Chars[Integer.parseInt(chunk.substring(6), 2)]);
        }
        if(s.length()%3==1){
            String chunk = Integer.toBinaryString(Integer.parseInt(s.substring(s.length()-1), 16))+"00000000";
            for(int j = 0; j<12-chunk.length(); j++){
                chunk = '0'+chunk;
            }
            sb.append(base64Chars[Integer.parseInt(chunk.substring(0, 6), 2)]);
            sb.append('=');
        } else if(s.length()%3==2){
            String chunk = Integer.toBinaryString(Integer.parseInt(s.substring(s.length()-1), 16))+"0000";
            for(int j = 0; j<12-chunk.length(); j++){
                chunk = '0'+chunk;
            }
            sb.append(base64Chars[Integer.parseInt(chunk.substring(0, 6), 2)]);
            sb.append(base64Chars[Integer.parseInt(chunk.substring(6), 2)]);
        }
        return sb.toString();
    }
}
