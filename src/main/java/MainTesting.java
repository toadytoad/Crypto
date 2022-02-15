import Bases.Base;
import XOR.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainTesting {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://cryptopals.com/static/challenge-data/4.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        Queue<Result> total = new PriorityQueue<>();
        for(int i = 0; i<327; i++){
            total.addAll(XOR.crackSingleHexXOR(br.readLine()));

        }
        for(int i = 0; i<100; i++){
            System.out.println(Objects.requireNonNull(total.poll()).s);
        }
    }
}
