package com.stirante.eventlang.visitor;

import com.stirante.eventlang.EventLangBaseVisitor;
import com.stirante.eventlang.EventLangParser;
import com.stirante.eventlang.model.Filter;
import com.stirante.eventlang.util.ParserUtils;

import java.util.Arrays;
import java.util.Collections;

public class TestVisitor extends EventLangBaseVisitor<Filter> {

    @Override
    public Filter visitComplex_test(EventLangParser.Complex_testContext ctx) {
        if (ctx.complex_test() == null) {
            return visit(ctx.test());
        }
        Filter f = new Filter();
        Filter f1 = visit(ctx.complex_test());
        if (ctx.test() != null) {
            Filter f2 = visit(ctx.test());
            if (ctx.AND() != null) {
                f.all_of = Arrays.asList(f1, f2);
            }
            else if (ctx.OR() != null) {
                f.any_of = Arrays.asList(f1, f2);
            }
        }
        else if (ctx.NOT() != null || ctx.LITERAL_NOT() != null) {
            f.none_of = Collections.singletonList(f1);
        }
        return f;
    }

    @Override
    public Filter visitTest(EventLangParser.TestContext ctx) {
        Filter f = new Filter();
        if (ctx.name().size() == 2) {
            f.subject = ctx.name(0).getText();
            f.test = ctx.name(1).getText();
        }
        else {
            f.test = ctx.name(0).getText();
        }
        f.operator = ctx.operator().getText();
        f.value = ParserUtils.valueToString(ctx.value());
        return f;
    }
}
