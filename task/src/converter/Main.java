package converter;

import java.util.Scanner;

public class Main {

    private static final String DECIMAL = "\\.";

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);

            int sourceRadix = Integer.parseInt(scanner.nextLine());
            String sourceNumber = scanner.nextLine();
            int destinationRadix = Integer.parseInt(scanner.nextLine());

            if (sourceRadix < 1 || sourceRadix > 36 || destinationRadix < 1 || destinationRadix > 36) {
                throw new Exception();
            }

            // interpret integer and fraction part independently
            String[] number = sourceNumber.split(DECIMAL);

            String integer = convertWholeNumberPart(number[0], sourceRadix, destinationRadix);
            String fraction = "";

            if (number.length > 1) {
                fraction = "." + convertFractionPart("0." + number[1], sourceRadix, destinationRadix);
            }

            System.out.println(integer + fraction);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    private static String convertWholeNumberPart(String sourceNumber, int sourceRadix, int destinationRadix) {
        if (sourceRadix < Character.MIN_RADIX) {
            return Long.toString(sourceNumber.length(), destinationRadix);
        }

        else if (destinationRadix < Character.MIN_RADIX) {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < Long.parseLong(sourceNumber); i++) {
                result.append(1);
            }

            return (result.length() == 0) ? "0" : result.toString();
        }

        long immediateNumber = Long.parseLong(sourceNumber, sourceRadix);
        return Long.toString(immediateNumber, destinationRadix);
    }

    private static String convertFractionPart(String sourceNumber, int sourceRadix, int destinationRadix) {
        StringBuilder fractionPart = new StringBuilder();

        if (sourceRadix != 10) {
            double result = 0;
            sourceNumber = sourceNumber.split(DECIMAL)[1];

            for (int i = 0; i < sourceNumber.length(); i++) {
                double a = Long.parseLong(sourceNumber.charAt(i) + "", sourceRadix) * 1.0;
                double b = Math.pow(sourceRadix, i + 1);
                result += a / b;
            }

            sourceNumber = "0." + String.valueOf(result).split(DECIMAL)[1];
            sourceRadix = 10;
        }

        int i = 0;
        while (i < 5) {
            double fraction = Double.parseDouble(sourceNumber) * destinationRadix;
            sourceNumber = String.valueOf(fraction);
            fractionPart.append(convertWholeNumberPart
                    (sourceNumber.split(DECIMAL)[0], sourceRadix, destinationRadix)
            );
            if (sourceNumber.split(DECIMAL).length > 1) {
                sourceNumber = "0." + sourceNumber.split(DECIMAL)[1];
            }
            else {
                break;
            }
            i++;
        }

        return fractionPart.toString();
    }
}
