package com.stirante.eventlang.model;

import java.util.List;

public class Event {
    public List<Event> sequence;
    public Actions add;
    public Actions remove;
    public Filter filters;
}
