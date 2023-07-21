package de.adesso.openaishowcase.Enums;

public enum Mood {
    POSITIV,
    NEGATIV,
    ESKALATION,
    SONSTIGES;


    public static String validate(String answer){

        for (Mood c : Mood.values()){
            if (answer.contains(c.name())){
                return c.name();
            }
        }
        return SONSTIGES.name();
    }

}

