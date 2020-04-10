import java.util.Scanner;

public class ActivationKey {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String key = scan.nextLine();
        String command = scan.nextLine();
        while (!"Generate".equals(command)) {
            String[] tokens = command.split(">>>");
            switch (tokens[0]) {
                case "Contains":
                    String part = tokens[1];
                    if (key.contains(part)){
                        System.out.println(String.format("%s contains %s", key,part));
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;

                case "Flip": {
                    String action = tokens[1];
                    int startIndex = Integer.parseInt(tokens[2]);
                    int endIndex = Integer.parseInt(tokens[3]);
                    key = flip(action, startIndex, endIndex, key);
                }
                    break;

                case "Slice":
                    int startIndex = Integer.parseInt(tokens[1]);
                    int endIndex = Integer.parseInt(tokens[2]);
                    key = sliceMethod(key,startIndex,endIndex);

                    break;
            }

            command = scan.nextLine();
        }
        System.out.printf("Your activation key is: %s",key);
    }

    private static String sliceMethod(String key, int startIndex, int endIndex) {
        StringBuilder result = new StringBuilder(key);
        result = result.delete(startIndex,endIndex);
        System.out.println(result.toString());

        return result.toString();
    }

    private static String flip(String action, int startIndex, int endIndex, String key) {
        String substringPart = key.substring(startIndex,endIndex);
        if ("Upper".equals(action)){
            substringPart = substringPart.toUpperCase();
        }else if ("Lower".equals(action)){
             substringPart = substringPart.toLowerCase();
        }
        key = key.substring(0,startIndex) + substringPart + key.substring(endIndex);
        System.out.println(key);
        return key;
    }
}
