parser grammar MyLangParser1;


options {
    superClass = MyLangParserBase;
    tokenVocab = MyLangLexer;
}


program: function_declarations? statements EOF;

statements:  statement+;

statement: simple_statements | compound_statement NEWLINE? | NEWLINE;

simple_statements
    : simple_stmt (';' simple_stmt)* ';'? NEWLINE
    ;
function_declarations:
    function_declaration (NEWLINE function_declaration)*;

simple_stmt:
    declaration
    |assignment
    | return_statement
    | BREAK
    | CONTINUE
    | print
    | input
    | function_call SEMICOLON?;

print:
    PRINT LEFT_PAREN ?expression RIGHT_PAREN
            ;

input:
    INPUT LEFT_PAREN ?IDENTIFIER RIGHT_PAREN;

declaration:
    type IDENTIFIER (ASSIGN expression)?;

compound_statement:
    conditional_statement
    | for;


assignment:
      IDENTIFIER ASSIGN expression
    | (IDENTIFIER (ASSIGN | compound_operator) expression)
    |  IDENTIFIER array_index ASSIGN expression;


compound_operator:
    ADD_ASSIGN
    | SUBTRACT_ASSIGN
    | MULTIPLY_ASSIGN
    | DIVIDE_ASSIGN
    | MODULO_ASSIGN;

return_statement: RETURN expression?;


conditional_statement:
    IF expression THEN COLON block (ELSE_IF expression THEN COLON block)* (ELSE COLON block)?;


for: FOR LEFT_PAREN declaration SEMICOLON expression SEMICOLON assignment RIGHT_PAREN COLON block;


function_declaration:
    PROCEDURE return_type IDENTIFIER LEFT_PAREN parameters? RIGHT_PAREN COLON block PROCEDURE_END;

return_type:
    type;

parameters: parameter (COMMA parameter)*;

parameter: type IDENTIFIER;

type:
    INTEGER
    | FLOAT
    | BOOLEAN
    | STRING
    | array;

array:
    ARRAY LEFT_BRACKET INTEGER_LITERAL RIGHT_BRACKET (INTEGER | FLOAT | BOOLEAN | STRING);
// Вызов функции
function_call:
    IDENTIFIER LEFT_PAREN arguments? RIGHT_PAREN;

arguments: expression (COMMA expression)*
 ;



block: NEWLINE INDENT statement+ DEDENT;


expression:
    singleExpression
    |logical_expression
    | arithmetic_expression
    | function_call
    | string_expression
    | array_literal
     ;

string_expression:
         string_term (ADD string_term)*;

string_term:
    STRING_LITERAL | IDENTIFIER | IDENTIFIER array_index;

singleExpression:
      literal
    | SUBTRACT? IDENTIFIER;


logical_expression:
    term_logical (OR term_logical)*;

term_logical:
    factor_logical (AND factor_logical)*;

factor_logical:
  NOT? BOOLEAN_LITERAL | NOT? IDENTIFIER | NOT? LEFT_PAREN logical_expression RIGHT_PAREN | comparison_operator | arithmetic_expression comparison_operator arithmetic_expression | NOT? IDENTIFIER array_index;

arithmetic_expression:
     term ((ADD | SUBTRACT) term)*;

term:
    factor ((DIVIDE | MULTIPLY | MODULO) factor)*;

factor:
    literal | SUBTRACT? IDENTIFIER | LEFT_PAREN arithmetic_expression RIGHT_PAREN | SUBTRACT? IDENTIFIER array_index | function_call;


array_index:
    LEFT_BRACKET (INTEGER_LITERAL | IDENTIFIER) RIGHT_BRACKET;

comparison_operator:
    EQUAL
    | NOT_EQUAL
    | GREATER
    | LESS
    | GREATER_EQUAL
    | LESS_EQUAL;



array_literal:
    LEFT_PAREN ( (literal | STRING_LITERAL) (COMMA (literal | STRING_LITERAL))*)? RIGHT_PAREN
    ;


literal:
    SUBTRACT? INTEGER_LITERAL
    | SUBTRACT? FLOAT_LITERAL
    | BOOLEAN_LITERAL
    | NULL_LITERAL;



