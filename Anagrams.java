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
             f.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, ArrayList<String>> A = new HashMap<>();

        for (String w : D.keySet()) {
            String a = signature(w);

            if (!A.containsKey(a)) {
                A.put(a, new ArrayList<>());
            }
            A.get(a).add(w);
        } 
        //commit 
         try {

            PrintWriter f = new PrintWriter(new FileWriter("anagrams"));

            for (String key : A.keySet()) {

                ArrayList<String> list = A.get(key);

                if (list.size() > 1) {

                    String anagramlist = "";

                    for (String word : list) {
                        if (anagramlist.equals("")) {
                            anagramlist = word;
                        } else {
                            anagramlist += " " + word;
                        }
                    }
//commit to changes   
                   f.println(anagramlist + "\\\\");

                    for (int repeat = 0; repeat < list.size() - 1; repeat++) {

                        int space = anagramlist.indexOf(' ');
                        anagramlist = anagramlist.substring(space + 1) + " " + anagramlist.substring(0, space);

                        f.println(anagramlist + "\\\\");
                    }
                }
            }

            f.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 
          System("sort anagrams > anagrams.sorted");

        try {

            BufferedReader asf = new BufferedReader(new FileReader("anagrams.sorted"));
            PrintWriter asftex = new PrintWriter(new FileWriter("latex/theAnagrams.tex"));

            String letter = "X";
            String lemma;
//commit to changes  
            while ((lemma = asf.readLine()) != null) {

                char initial = lemma.charAt(0);

                if (Character.toLowerCase(initial) != Character.toLowerCase(letter.charAt(0))) {
                    letter = String.valueOf(initial);

                    asftex.println("\n\\vspace{14pt}\n\\noindent\\textbf{\\Large " 
                    + Character.toUpperCase(initial) + "}\\\\*[+12pt]");
                }

                asftex.println(lemma);
            }

            asf.close();
            asftex.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System("rm anagrams anagrams.sorted");
    }
}
//commit changes 
