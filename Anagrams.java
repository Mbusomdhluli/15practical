//start
import java.io.*;
import java.util.*;

public class Anagrams {

    public static int System(String command) {
        System.out.println("---------------------------------------------------> " + command);
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
public static String signature(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java Anagrams inputfile");
            return;
        } 
// commit

String inputfile = args[0];
        System.out.println("Data file: " + inputfile);

        HashMap<String, Integer> D = new HashMap<>();

        try {
            BufferedReader f = new BufferedReader(new FileReader(inputfile));

            String line;
            int linenumber = 0;

            while ((line = f.readLine()) != null) {
                linenumber++;

                String[] words = line.split("\\s+");

                for (String w : words) {
                    String W = w.replaceAll("[0123456789(,.,.;:_.!?\\-)]", "");
                    String word = W;

                    if (D.containsKey(word)) {
                        D.put(word, D.get(word) + 1);
                    } else {
                        D.put(word, 1);
                    }
                }
            }
            // commit 
