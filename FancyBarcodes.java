import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        for (int i = 0; i <n ; i++) {
            String input = scan.nextLine();
            String regexValidBarcode = "@#+(?<barcode>[A-Z][A-Za-z0-9]{4,}[A-Z])@#+";
            Pattern pattern = Pattern.compile(regexValidBarcode);
            Matcher matcher = pattern.matcher(input);
            String productGroup = "";

            if (matcher.find()){
                String barcode = matcher.group("barcode");
                for (int j = 0; j <barcode.length() ; j++) {
                    char currentChar = barcode.charAt(j);
                    if (Character.isDigit(currentChar)){
                        productGroup += "" + currentChar;
                    }
                }
                if ("".equals(productGroup)){
                    System.out.println("Product group: 00");
                }else {
                    System.out.println(String.format("Product group: %s", productGroup.toString()));
                }
            } else {
                System.out.println("Invalid barcode");
            }


        }


    }
}
