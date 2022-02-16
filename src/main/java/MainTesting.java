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
        String cyphered = XOR.StringKeyXOR("I Heart Zoe and Max","AniLUL");
        Queue<Result> q = XOR.multiXORBruteForce(cyphered, 5);
        while(q.peek()!=null){
            Result r = q.poll();
            System.out.println(XOR.StringKeyXOR(cyphered, r.s)+", "+r.s);
        }
    }
}
