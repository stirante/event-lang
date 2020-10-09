grammar EventLang;

EQUAL: '=='|'equals'|'=';
NOT_EQUAL: '!='|'<>';
LESS_THAN: '<';
GREATER_THAN: '>';
LESS_EQUAL: '<=';
GREATER_EQUAL: '>=';

LITERAL_NOT: 'not';

OR: 'or'|'||';
AND: 'and'|'&&';
NOT: '!';

IF: 'if';
END: 'end';
THEN: 'then';
ADD: 'add';
REMOVE: 'remove';

events
    : statement+
    ;

statement
    : IF complex_test THEN block END
    ;

block
    : action+
    ;

action
    : ADD QUOTE
    | REMOVE QUOTE
    | statement
    ;

complex_test
    : test
    | (NOT|LITERAL_NOT) test
    | complex_test OR test
    | complex_test AND test
    ;

test
   : (name '.')? name operator value
   ;

value
    : INT
    | QUOTE
    ;

operator
    : EQUAL
    | NOT_EQUAL
    | LITERAL_NOT
    | LESS_EQUAL
    | LESS_EQUAL
    | GREATER_THAN
    | GREATER_EQUAL
    ;

name
   : STRING
   ;

QUOTE
   : '\'' STRING '\''
   | '"' STRING '"'
   ;

STRING
   : [a-zA-Z_][a-zA-Z0-9_]*
   ;

INT
   : [0-9]+
   ;

BOOL
   : 'true'|'false'
   ;

WS
   : [ \r\n\t] -> channel(HIDDEN)
   ;