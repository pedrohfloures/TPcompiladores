package lang.parser;

import beaver.Symbol;
import beaver.Scanner;
import lang.ast.*;
import lang.ast.expr.*;
import lang.ast.cmd.*;
import lang.ast.types.*;
%%

%public
%class LangScanner
%extends Scanner
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%eofval{
         return newToken(Terminals.EOF, "end-of-file");
%eofval}
%unicode
%line
%column
%{       
        private char c;  
        private int cCount = 0;
	private Symbol newToken(short id)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength());
	}

	private Symbol newToken(short id, Object value)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
	}
%}

LineTerminator = \r|\n|\r\n
LineComment    = "--" [^\n\r]*
WhiteSpace     = {LineTerminator} | {LineComment} | [ \t\f]
LowerLetter = [a-z]
UpperLetter = [A-Z]
Number    = [:digit:] [:digit:]*
charCode    = [:digit:] [:digit:] [:digit:]
Float     = {Number} "." {Number}
Id        = {LowerLetter} ({LowerLetter} | {UpperLetter} | [:digit:])*
TyId      = {UpperLetter} ({LowerLetter} | {UpperLetter} | [:digit:])*

%state CHAR

%%    
<YYINITIAL> {
        ";"           { return newToken(Terminals.SEMI, yytext());   } 
        ":"           { return newToken(Terminals.COLON, yytext());  }
        ","           { return newToken(Terminals.COMMA, yytext());  }
        "."           { return newToken(Terminals.DOT, yytext());  }
        "{"           { return newToken(Terminals.LBRACE, yytext()); }
        "}"           { return newToken(Terminals.RBRACE, yytext()); }
        "["           { return newToken(Terminals.LBRACKET, yytext()); }
        "]"           { return newToken(Terminals.RBRACKET, yytext()); }
        "("           { return newToken(Terminals.LPAREN, yytext()); }
        ")"           { return newToken(Terminals.RPAREN, yytext()); }
        "+"           { return newToken(Terminals.PLUS, yytext());   }
        "-"           { return newToken(Terminals.SUB, yytext());  }
        "*"           { return newToken(Terminals.MULT, yytext());   }
        "/"           { return newToken(Terminals.DIV, yytext());    }
        "%"           { return newToken(Terminals.MOD, yytext());    }
        "&&"          { return newToken(Terminals.AND, yytext());    }        
        "!"           { return newToken(Terminals.NOT, yytext());    }    
        "<"           { return newToken(Terminals.LT, yytext());     }
        "<:"          { return newToken(Terminals.RTL, yytext());    }
        ":>"          { return newToken(Terminals.RTR, yytext());    }
        "=="          { return newToken(Terminals.EQ, yytext());     }
        "!="          { return newToken(Terminals.NEQ, yytext());    }
        "="           { return newToken(Terminals.ATTR, yytext());   }
        "if"          { return newToken(Terminals.IF, yytext());     }        
        "else"        { return newToken(Terminals.ELSE, yytext());   }  
        "while"       { return newToken(Terminals.WHILE, yytext());  }
        "read"        { return newToken(Terminals.READ, yytext());  }
        "print"       { return newToken(Terminals.PRINT, yytext());  }
        "return"      { return newToken(Terminals.RETURN, yytext());  }
        "iterate"     { return newToken(Terminals.ITERATE, yytext());  }
        "Char"        { return newToken(Terminals.TYCHAR, yytext());   }
        "Bool"        { return newToken(Terminals.TYBOOL, yytext());   }
        "Int"         { return newToken(Terminals.TYINT, yytext());    }
        "Char"        { return newToken(Terminals.TYCHAR, yytext());    }
        "data"        { return newToken(Terminals.DATA,yytext());    }
        "Void"        { return newToken(Terminals.TYVOID, yytext());   }
        "T"           { return newToken(Terminals.TRUE, yytext());   }
        "F"           { return newToken(Terminals.FALSE, yytext());  }
        "NULL"        { return newToken(Terminals.NULL, yytext());  }
        "::"          { return newToken(Terminals.TYBIND, yytext()); }
        \'            { c = '\0'; yybegin(CHAR);       }
        {Number}      { return newToken(Terminals.INT, new Integer(yytext())); }
        {Float}       { return newToken(Terminals.FLOAT, new Float(yytext()) ); }
        "new"         { return newToken(Terminals.NEW, yytext() ); }
        {Id}          { return newToken(Terminals.ID, yytext());     }
        {TyId}        { return newToken(Terminals.TYID, yytext());     }
        
        {WhiteSpace}  {/* Ignorar  */ }
 }


<CHAR> {
    \'               { yybegin(YYINITIAL); return newToken(Terminals.CHAR, c); } 
   [^\n\t\r\"\\]     { c = yytext().charAt(0);                             }
   \\                { c = '\\';                                           }
   \\n               { c = '\n';                                           }
   \\t               { c = '\t';                                           }
   \\r               { c = '\r';                                           }
   \\b               { c = '\b';                                           }
 }

 
[^]            { throw new Scanner.Exception("carácter inesperado na entrada: '" + yytext() + "'"); }
