package org.oaosalty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {

    public static boolean licenseNumberInputChecker(String licenseNumber) {
        Pattern pattern = Pattern.compile("[а-яА-Я]{1}\\d{3}[а-яА-Я]{2}");
        Matcher matcher = pattern.matcher(licenseNumber);
        return matcher.find();
    }

    public static boolean yearInputChecker(String year) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(year);
        if (matcher.find()) {
            return true;
        } else {
            int intYear = Integer.parseInt(year);
            return (intYear <= 1950) || (intYear >= 2025);
        }
    }

    public static boolean isItNum (String strNum)
    {
        try
        {
            int intNum = Integer.parseInt(strNum);
            return true;
        }
        catch (NumberFormatException err)
        {
            return false;
        }
    }

    public static boolean isThereData (String data)
    {
        if (data.isEmpty()) return false;

        Pattern pattern = Pattern.compile("[0-9a-zA-Zа-яА-Я]"); //если в строке присутствует хоть что-нибудь
        Matcher matcher = pattern.matcher(data);
        return matcher.find();
    }

    public static boolean fileNameChecker(String name)
    {
        Pattern pattern = Pattern.compile("[^0-9a-zA-Z-_]"); //если в строке присутствует хоть один запрещённый символ
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

}
