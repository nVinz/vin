package ru.aplana.XAKATOH;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VINGeneration {

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String digits = "0123456789";
    private static final String symbols = upper + digits;

    public String randomString(int length) {
        Random random;
        char[] symbolsArray;
        char[] buf;

        random = Objects.requireNonNull(new SecureRandom());
        symbolsArray = symbols.toCharArray();
        buf = new char[length];

        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbolsArray[random.nextInt(symbolsArray.length)];
        return new String(buf);
    }

    public static String generateVIN(String country, int year){
        return generateWMI(country) + generateVDS() + generateVIS(year);
    }

    private static String generateWMI(String country) {
        int ind = 0;
        try {
            Map<String, String> countryCodes = new HashMap<>();
            //TODO: Change zones to countries
            countryCodes.put("Africa", "A-H");
            countryCodes.put("Asia", "J-R");
            countryCodes.put("Europe", "S-Z");
            countryCodes.put("North America", "1-5");
            countryCodes.put("Oceania", "6-7");
            countryCodes.put("South America", "8-9");

            String mask = countryCodes.get(country);

            //TODO: it's for X-X pattern. need XX-XX pattern

            StringBuilder builder = new StringBuilder();

            int randomCharacterNumber = (int) (Math.random() * (symbols.indexOf(mask.split("-")[1]) - symbols.indexOf(mask.split("-")[0])) + symbols.indexOf(mask.split("-")[0]));
            ind++;
            builder.append(symbols.charAt(randomCharacterNumber));
            ind++;
            builder.append(symbols.charAt((int) (Math.random() * symbols.length())));
            ind++;
            builder.append(symbols.charAt((int) (Math.random() * symbols.length())));

            return builder.toString();
        } catch (StringIndexOutOfBoundsException e){
            return "ERROR IN generateWMI " + ind + "!!!!";
        }
    }

    private static String generateVDS() {
        try {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < 5; i++) {
                int randomCharacterNumber = (int) (Math.random() * symbols.length());
                builder.append(symbols.charAt(randomCharacterNumber));
            }
            builder.append("X");

            return builder.toString();
        } catch (StringIndexOutOfBoundsException e){
            return "ERROR IN generateVDS";
        }
    }

    private static String generateVIS(int year) {
        try {
            String lettersForYear = "ABCDEFGHJKLMNPRSTVWXY123456789";
            int yearOffset = Math.abs(year - 1980);
            StringBuilder builder = new StringBuilder();
            builder.append(lettersForYear.charAt(yearOffset % lettersForYear.length()));

            for (int i = 0; i < 3; i++) {
                int randomCharacterNumber = (int) (Math.random() * symbols.length());
                builder.append(symbols.charAt(randomCharacterNumber));
            }
            for (int i = 0; i < 4; i++) {
                int randomCharacterNumber = (int) (Math.random() * digits.length());
                builder.append(digits.charAt(randomCharacterNumber));
            }

            return builder.toString();
        } catch (StringIndexOutOfBoundsException e){
            return "ERROR IN generateVIS";
        }
    }
}
