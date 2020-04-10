import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String password = scan.nextLine();
        String command = scan.nextLine();
        while (!"Done".equals(command)){
            String[] tokens = command.split("\\s+");
            switch(tokens[0]){
                case "TakeOdd":
                    password = takeOdds(password);
                    break;
                case "Cut":
                    int index = Integer.parseInt(tokens[1]);
                    int length = Integer.parseInt(tokens[2]);
                    password = cut(password,index,length);
                    break;
                case "Substitute":
                    String substring = tokens[1];
                    String substitute = tokens[2];
                    password = subs(password,substring,substitute);
                    break;
            }

            command = scan.nextLine();
        }
        System.out.printf("Your password is: %s",password);
    }

    private static String subs(String password, String substring, String substitute) {
        if (password.contains(substring)){
           password = password.replace(substring,substitute);
            System.out.println(password);
        } else{
            System.out.println("Nothing to replace!");
        }
        return  password;
    }

    private static String cut(String password, int index, int length) {
        password = password.substring(0,index) + password.substring(index+length);
        System.out.println(password);
        return password;
    }

    private static String takeOdds(String password) {
        StringBuilder newPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            if (i%2!=0){
                newPassword.append(password.charAt(i));
            }
        }
        System.out.println(newPassword.toString());
        return  newPassword.toString();
    }
}
