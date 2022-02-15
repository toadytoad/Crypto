import Bases.Base;
import XOR.XOR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MainTesting {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://cryptopals.com/static/challenge-data/4.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String best = "";
        double max = 0;
        for(int i = 0; i<60; i++){
            String s = XOR.crackSingleHexXOR(br.readLine());
            double score = XOR.score(s);
            if(score>max){
                max = score;
                best = s;
            }
            System.out.println(s);
        }
        System.out.println(best);
    }
}
