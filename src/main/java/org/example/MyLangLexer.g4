lexer grammar MyLangLexer;


options {
    superClass = MyLangLexerBase;
}

tokens {
    INDENT,
    DEDENT
}


NEWLINE: WHITESPACE? ('\r'? '\n' | '\r' | '\f') WHITESPACE? { this.onNewLine(); };

//NEWLINE : '\r'? '\n';

WHITESPACE : [ \t\f]+ -> skip;

//WHITESPACE: [ \t]+ -> skip;
//NEWLINE: WHITESPACE? ('\r'? '\n' | '\r' | '\f') WHITESPACE? { this.onNewLine(); };

BOOLEAN: 'логическая';
BREAK: 'стоп';
CONTINUE: 'пропустить';
FLOAT: 'вещественный';
INTEGER: 'целое';
IF: 'если';
ELSE_IF: 'иначе_если';
ELSE: 'иначе';
THEN: 'тогда';
FOR: 'цикл';
ARRAY: 'массив';
STRING: 'строка';
PROCEDURE: 'процедура';
RETURN: 'вернуть';
PROCEDURE_END: 'конец_процедуры';
ERROR: 'ERROR';

INTEGER_LITERAL: DecimalLiteral | SignedInteger;
fragment DecimalLiteral: DecimalNumber;
fragment DecimalNumber: '0' | NonZeroDigit Digits;
fragment Digits: Digit+;
fragment Digit: '0' | NonZeroDigit;
fragment NonZeroDigit: [1-9];

FLOAT_LITERAL: DecimalNumberWithDot;
fragment DecimalNumberWithDot: Digits '.' Digits;
fragment SignedInteger: Digits;

NULL_LITERAL: 'ничего';

CHAR_LITERAL: '\'' Character '\'' | '\'' EscapeSequence '\'';
fragment Character: ~['\\\r\n];

STRING_LITERAL: '"' StringCharacters? '"';
fragment StringCharacters: StringCharacter+;
fragment StringCharacter: ~["\\\r\n] | EscapeSequence;

BLOCK_TEXT: '"""' [ \t]* [\n\r] .*? '"""';

fragment EscapeSequence: '\\' [btnfr"'\\];

BOOLEAN_LITERAL: 'правда' | 'ложь';

LEFT_PAREN: '(';
RIGHT_PAREN: ')';
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';
SEMICOLON: ';';
COMMA: ',';
DOT: '.';

ASSIGN: '=';
GREATER: '>';
LESS: '<';
NOT: '!';
COLON: ':';
EQUAL: '==';
LESS_EQUAL: '<=';
GREATER_EQUAL: '>=';
NOT_EQUAL: '!=';
AND: '&&';
OR: '||';
ADD: '+';
SUBTRACT: '-';
MULTIPLY: '*';
DIVIDE: '/';
MODULO: '%';

ADD_ASSIGN: '+=';
SUBTRACT_ASSIGN: '-=';
MULTIPLY_ASSIGN: '*=';
DIVIDE_ASSIGN: '/=';
MODULO_ASSIGN: '%=';

PRINT: 'вывод';

COMMENT: '/*' .*? '*/' -> skip;


//IDENTIFIER: [a-zA-Z_\u0400-\u04FF][a-zA-Z0-9_\u0400-\u04FF]*;
//IDENTIFIER: [a-zA-Z_ \u0400-\u04FF][a-zA-Z0-9_ \u0400-\u04FF]*;
IDENTIFIER: [a-zA-Z_а-яА-Я][a-zA-Z0-9_а-яА-Я]*;