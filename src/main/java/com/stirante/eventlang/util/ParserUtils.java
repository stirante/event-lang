package com.stirante.eventlang.util;

import com.stirante.eventlang.EventLangParser;

public class ParserUtils {

    public static String valueToString(EventLangParser.ValueContext ctx) {
        String value = null;
        if (ctx.INT() != null) {
            value = ctx.INT().getText();
        }
        else if (ctx.QUOTE() != null) {
            value = unquote(ctx.QUOTE().getText());
        }
        return value;
    }

    public static String unquote(String value) {
        return value.substring(1, value.length() - 1);
    }

}
