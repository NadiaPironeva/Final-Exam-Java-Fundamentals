import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        List<String> coolEmoji = new ArrayList<>();

        String regexNumbers = "\\d";
        Pattern patternNumbers = Pattern.compile(regexNumbers);
        Matcher matcherNumbers = patternNumbers.matcher(input);
        int coolThreshold = 1;
        while(matcherNumbers.find()){
            int digit = Integer.parseInt(matcherNumbers.group());
            coolThreshold *=digit;
        }
        System.out.println(String.format("Cool threshold: %d",coolThreshold));

        String regexEmoji = "(\\*\\*|::)(?<emoji>[A-Z][a-z]{2,})(\\1)";
        Pattern patternEmoji = Pattern.compile(regexEmoji);
        Matcher matcherEmoji = patternEmoji.matcher(input);
        int countEmoji = 0;
        while(matcherEmoji.find()){
            countEmoji++;
            String emojiSet = matcherEmoji.group();
            String emoji = matcherEmoji.group("emoji");
            int coolness = coolNumber(emoji);

            if (coolness>coolThreshold){
                coolEmoji.add(emojiSet);
            }
        }
        System.out.printf("%d emojis found in the text. The cool ones are:%n",countEmoji);
        for (String cool : coolEmoji) {
            System.out.println(cool);
        }
    }

    private static int coolNumber(String emoji) {
        int result = 0;
        for (int i = 0; i <emoji.length() ; i++) {
            result += emoji.charAt(i);
        }
        return result;
    }
}
