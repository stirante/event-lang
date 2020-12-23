package com.stirante.eventlang.model;

import java.util.List;

public class Filter {
    public String test;
    public String value;
    public String operator;
    public String subject;
    public String domain;
    public List<Filter> all_of;
    public List<Filter> any_of;
    public List<Filter> none_of;
}
