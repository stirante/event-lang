package com.stirante.eventlang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stirante.eventlang.model.Event;
import com.stirante.eventlang.visitor.EventVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class EventCompiler {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void main(String[] args) {
        String test = "if has_tag == 'thing1' and other.has_tag == 'object1' then\n" +
                "    add 'group1'\n" +
                "    remove 'group2'\n" +
                "    if has_tag == 'another1' then\n" +
                "        add 'idk'\n" +
                "    end\n" +
                "end\n" +
                "if has_tag == 'thing2' and other.has_tag == 'object2' then\n" +
                "    add 'group2'\n" +
                "    remove 'group1'\n" +
                "end";
        System.out.println(new EventCompiler().compile(test));

    }

    public String compile(String code) {
        EventLangLexer lexer = new EventLangLexer(CharStreams.fromString(code));
        EventLangParser parser = new EventLangParser(new CommonTokenStream(lexer));
        EventLangParser.EventsContext events = parser.events();
        EventVisitor eventVisitor = new EventVisitor();
        Event result = eventVisitor.visit(events);
        return GSON.toJson(result);
    }

}
