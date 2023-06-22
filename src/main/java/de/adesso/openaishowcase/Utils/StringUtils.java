package de.adesso.openaishowcase.Utils;

public class StringUtils {

    public static String removePunctuation(String prompt){

        String returnString = prompt.replaceAll("\\p{Punct}", "");
        return returnString;
    }
}
