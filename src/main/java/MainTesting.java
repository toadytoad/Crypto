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
        String key = "ICE";
        String a = "Burning 'em, if you ain't quick and nimble\n" +
                "I go crazy when I hear a cymbal";
        System.out.println(Base.toHex(XOR.StringKeyXOR(a, key)));
        String b = "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f";
        b = Base.fromHex(b);
        System.out.println(XOR.StringKeyXOR(b, key));
    }
}
