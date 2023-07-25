package de.adesso.openaishowcase.Enums;

import java.util.Locale;

public enum Category {
    EINLADUNG,
    ANGEBOT,
    AUFTRAGSBESTÄTIGUNG,
    MEETING,
    BEWERBUNG,
    WERBUNG,
    ABWESENHEIT,
    NEWSLETTER,
    FRAGE,
    STATUS,
    SONSTIGES,
    LOGISTIK;

    public static String validate(String answer){

        for (Category c : Category.values()){
            if (answer.toUpperCase().contains(c.name())){
                return c.name();
            }
        }
        return SONSTIGES.name();
    }

}
