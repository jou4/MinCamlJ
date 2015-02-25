grammar MinCaml;

// Fragments
fragment DIGIT : [0-9];
fragment LOWER : [a-z];
fragment UPPER : [A-Z];

// Lexer Rules
IF : 'if';
THEN : 'then';
ELSE : 'else';
LET : 'let';
IN : 'in';
REC : 'rec';
NOT : 'not';
BOOL : 'true' | 'false';
LPAREN : '(';
RPAREN : ')';
PLUS : '+';
MINUS : '-';
PLUS_DOT : '+.';
MINUS_DOT : '-.';
AST_DOT : '*.';
SLASH_DOT : '/.';
EQUAL : '=';
LESS_GREATER : '<>';
LESS_EQUAL: '<=';
GREATER_EQUAL : '>=';
LESS : '<';
GREATER : '>';
COMMA : ',';
DOT : '.';
LESS_MINUS : '<-';
SEMICOLON : ';';
ARRAY_CREATE : 'Array.make';
INT : DIGIT+;
FLOAT : DIGIT+ (DOT DIGIT*)? ([eE] [+-]? DIGIT+)?;
IDENT : (LOWER (DIGIT|LOWER|UPPER|'_')*) | '_';
WHITESPACE : [ \t\r\n]+ -> skip;

// Parser Rules
simple_exp
	: LPAREN exp RPAREN		#ParenExp
	| LPAREN exp (COMMA exp)+ RPAREN	#Tuple
	| LPAREN RPAREN			#Unit
	| BOOL					#Bool
	| INT					#Int
	| FLOAT					#Float
	| IDENT					#Ident
	| simple_exp DOT LPAREN exp RPAREN	#ArrayGet
	;

exp
	: simple_exp			#SimpleExp
	| NOT exp				#NotExp
	| (MINUS|MINUS_DOT) exp	#Neg
//	| MINUS exp				#Neg
//	| exp PLUS exp			#Add
//	| exp MINUS exp			#Sub
	| exp EQUAL exp			#Equal
	| exp LESS_GREATER exp	#LessGreater
	| exp LESS exp			#Less
	| exp GREATER exp		#Greater
	| exp LESS_EQUAL exp	#LessEqual
	| exp GREATER_EQUAL exp	#GreaterEqual
	| IF exp THEN exp ELSE exp	#If
//	| MINUS_DOT exp			#FNeg
//	| exp PLUS_DOT exp		#FAdd
//	| exp MINUS_DOT exp		#FSub
//	| exp AST_DOT exp		#FMul
//	| exp SLASH_DOT exp		#FDiv
	| LET IDENT EQUAL exp IN exp	#Let
	| LET REC IDENT IDENT+ EQUAL exp IN exp	#LetRec
	| exp simple_exp+		#App
	| LET LPAREN IDENT (COMMA IDENT)+ RPAREN EQUAL exp IN exp #LetTuple
	| simple_exp DOT LPAREN exp RPAREN LESS_MINUS exp	#ArrayPut
	| exp SEMICOLON exp		#Block
	| ARRAY_CREATE simple_exp simple_exp	#ArrayCreate
	| exp (AST_DOT|SLASH_DOT) exp	#MulDiv
	| exp (PLUS|MINUS|PLUS_DOT|MINUS_DOT) exp	#AddSub
	;
