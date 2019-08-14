import java.io.InputStreamReader;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;

public class BullsAndCows {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BullsAndCows.class);

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        try (
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                        BullsAndCows.class.getResourceAsStream("dictionary.txt")));
        ) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        int numberOfWords = words.size();
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Bulls and Cows game!");
        while (true) {
            int k = rand.nextInt(numberOfWords);
            String word = words.get(k);
            int wordLength = word.length();
            HashMap<Character, HashSet<Integer>> letters = new HashMap<>();
            for (int i = 0; i < wordLength; i++) {
                if (!letters.containsKey(word.charAt(i))) {
                    letters.put(word.charAt(i), new HashSet<>());
                }
                letters.get(word.charAt(i)).add(i);
            }
            System.out.println("I offered a " + wordLength + "-letter word, your guess?");

            for (int i = 0; i < 10; i++) {
                String guessWord = sc.next();
                int bulls = 0;
                int cows = 0;
                for (k = 0; k < guessWord.length(); k++) {
                    HashSet<Integer> occurrences = letters.get(guessWord.charAt(k));
                    if (!(occurrences == null)) {
                        if (occurrences.contains(k)) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }
                if (wordLength == guessWord.length() && bulls == wordLength) {
                    System.out.println("You won!");
                    break;
                }
                System.out.println("Bulls: " + bulls);
                System.out.println("Cows: " + cows);
                if (i == 9) {
                    System.out.println("You lose! Correct word is " + word);
                }
            }
            System.out.println("Wanna play again? Y/N");
            String isAgain = sc.next();
            if (isAgain.equals("Y")) {
                continue;
            } else {
                break;
            }
        }
        sc.close();
    }
}
