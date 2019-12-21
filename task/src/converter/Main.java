package converter;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        int sourceRadix = Integer.parseInt(SCANNER.nextLine());
        String sourceNumber = SCANNER.nextLine();
        int destinationRadix = Integer.parseInt(SCANNER.nextLine());

        if (sourceRadix < Character.MIN_RADIX) {
            int result = 0;

            for (int i = sourceNumber.length() - 1; i >= 0; i--) {
                result += Integer.parseInt(sourceNumber.charAt(i) + "") *
                        Math.pow(sourceRadix, i - sourceNumber.length() + 1);
            }

            System.out.println(convertFromOneBaseToAnother(String.valueOf(result), 10, destinationRadix));
        }

        else if (destinationRadix < Character.MIN_RADIX) {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < Integer.parseInt(sourceNumber); i++) {
                result.append(1);
            }

            System.out.println(result.toString());
        }

        else {
            System.out.println(convertFromOneBaseToAnother(sourceNumber, sourceRadix, destinationRadix));
        }
    }

    private static String convertFromOneBaseToAnother(String number, int base1, int bas2) {
        return Integer.toString(Integer.parseInt(number , base1), bas2);
    }
}
