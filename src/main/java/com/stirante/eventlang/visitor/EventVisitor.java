package com.stirante.eventlang.visitor;

import com.stirante.eventlang.EventLangBaseVisitor;
import com.stirante.eventlang.EventLangParser;
import com.stirante.eventlang.model.Actions;
import com.stirante.eventlang.model.Event;
import com.stirante.eventlang.util.ParserUtils;

import java.util.ArrayList;

public class EventVisitor extends EventLangBaseVisitor<Event> {
    @Override
    public Event visitEvents(EventLangParser.EventsContext ctx) {
        if (ctx.statement().size() == 0) {
            return null;
        }
        else if (ctx.statement().size() == 1) {
            return visit(ctx.statement(0));
        }
        else {
            Event e = new Event();
            e.sequence = new ArrayList<>();
            for (EventLangParser.StatementContext statementContext : ctx.statement()) {
                e.sequence.add(visit(statementContext));
            }
            return e;
        }
    }

    @Override
    public Event visitStatement(EventLangParser.StatementContext ctx) {
        Event e = new Event();
        e.filters = new TestVisitor().visit(ctx.complex_test());
        for (EventLangParser.ActionContext actionContext : ctx.block().action()) {
            if (actionContext.statement() != null) {
                if (e.sequence == null) {
                    e.sequence = new ArrayList<>();
                }
                e.sequence.add(visit(actionContext.statement()));
            }
            else if (actionContext.ADD() != null) {
                if (e.add == null) {
                    e.add = new Actions();
                }
                e.add.component_groups.add(ParserUtils.unquote(actionContext.QUOTE().getText()));
            }
            else if (actionContext.REMOVE() != null) {
                if (e.remove == null) {
                    e.remove = new Actions();
                }
                e.remove.component_groups.add(ParserUtils.unquote(actionContext.QUOTE().getText()));
            }
        }
        return e;
    }
}
