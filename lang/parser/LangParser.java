package lang.parser;

import lang.ast.decls.*;
import lang.ast.cmd.*;
import lang.ast.*;
import lang.ast.expr.*;
import lang.ast.types.*;
import java.io.IOException;
import beaver.*;
import java.util.ArrayList;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "Lang.grammar".
 */
public class LangParser extends Parser {

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9pjbcTuL4KKXi#zcmAaKq92Yn6IW9HG38GaG0X9I24WOaT5L4H5HCKMHKMnApPKYWYFZ$f" +
		"751N4hbYmW1KLKH15HIag2XP0qV6TsM4pcsmm$k4NkyyttpTdpioPEpFtp1Nnnyb06IM7IJ" +
		"yvNFAalmoGVAbMNqQoDk9PAbKoL7f8XdGsEaUIcjhxeEq0sKuBxT8nTfUqFXGzNksH6FMjJ" +
		"9Fpv18vNawKKoBwQZqcD4uMkb0wgjqIWssoJ90qQIzJv9QmST9eIvTcORQxiBMLPDhEb3Xq" +
		"k7zNwICbOJPNfzhelKBgE4cHbj9AMaiRH#sRStTd$aCSNO5ERT3IEy88HpYzZdRq2Ow#mD6" +
		"rZhxIH35Ff7QX4dZguH8TSSOTfKoo9LSceIwIYrd9Zf8f5verxHcoT9BBvIovNJg4BCKoNQ" +
		"9aWGIi9PO8ryWTShBuTYrPQxL8xfSRY70ROsRA8J93kiYnCjj4l8VvTZEgLFg2OOnGAIEOw" +
		"v4oMct0NxSDbrEbXX4ovLmPI#rQEOh$N9bgR9V9LMRakTHkOXVJPARUOVMRD9T4cIWjAECb" +
		"IVqgxIHURP82AP91CbZAPQICez$ru2GvNSwWTZOuHywNI#KAkKvkv4JTBBVArPoRE#LUcSL" +
		"8SqJaRZ1FxfFvul6a0TOWcfCOorbhJhIuYDR8dhdiUn#UNBylGPtHvDu7RGVArd2NWhgGS#" +
		"0vxr4gvxrntxfn2dapSarVRIlXLEbCwOMDaqXzA2V4jMLXooV39lAE5pNmr$cqIvZDrScXw" +
		"7Mg7Mzu1$Dcrwd6vk$gv0Wj$s#4faK9Ff6Wz5F7yyv6dd4wMIMJzpxNtZcvPBi2etg4BDsn" +
		"T6E4eI5B1fPoCa65jVX4A227995XWf5rr1eoT14HYXapWRPICc8lSfIEECBCLolD6l6Cgdi" +
		"DO#VYyDTjv1xkm7JJEubQdCbH7gnjGRy4wkbO4$a7TpY5MSMJHNCeqnWrXNyQyF4VXiqpIA" +
		"0zWSY#oJf9vZpvvf$7tsFMsTp6MJnb2giOBqEKaaIGJIs9bLquNVJFQwaF8rW2EbdR9f0bu" +
		"dS2BT1bS0PS1NS6RRJV$e1FDlw3uAnWNNQWSx0zWku6RxNsVZRcUnJuUnlWuR3U$T7MPwpr" +
		"UOXY0lmDF0MU2TV#npm6A7umRvtVsuutWxxBxPYhA6PQlHUV8kipc$gskd6yIRJrjMsiYJn" +
		"0tNVY12WMmVr07dePVNw1brDlRdq6OrjVrqTMOekoRIKKhU42q0omvjvYED#sioP#34XmOb" +
		"PGV0otmZTiJx1LKh2MVYfj6TR6$ibck2ssdd1NU81juqpuxUopBa2tj$QHbhjZVn2kTcBqi" +
		"bmEHe0EeDJQkbZk3sh0A7T#DWxxwSspkjQnwxNROxLdULhz$li3l#tEU7FsQOx23iTdbQEt" +
		"sFPTjizgomNEE3cMnrXUquHvhBE#koqNrrk3jGtxUENrwX#1bLQpbjxO27t6Exg7vObrDhz" +
		"rlNcjg5VVwEYTuMt1Sn4XvcJBioAqJRKytx7LMdxOyc8dHfgrFODEjxPafs$zERMj5s#fqv" +
		"OUQRxEyoraf$yEIlMdPTvLzJFu4Jog7bDBrHBrf7f2FQuMgn#CnpO9$HGPIArHdr2oD#e3y" +
		"2vuMtr8oHkftWVlgBVKc#eDjKAzhbvJBzYUhvhoMKM#KwlKAvJF6yjpYcoWbgkNhD#BwcN3" +
		"JojMHAqs#cjJRWoEfRxQDwRjyGLuIYrJwzMwaErpjLLj2TK#2wcru5EZjflo9x3PgE$Kz#e" +
		"ljLVz4$BT2NwtUeVwGsTJli7Bk3RAk6t8Iy3ZFjNmHUUa2dGDkXAkXCaDm2E$HtEdHd4NQy" +
		"I0Q13BBHimNpLvS06iKOekDVTOW9lR1nvtTHG8XA1pPecnDmFHmECsZtOGo$T407XyLGGSU" +
		"7nf12mylZi23ZnkSnzuZI3QS3MngqqzO91hRc3DKTpI0VFLLmXNmLNmG3WIyWq2Cl9VQ28d" +
		"Rm==");

    private boolean tainted = false;
    
    public boolean isTainted(){ return tainted;}
    
    protected void recoverFromError(Symbol token, TokenStream in) throws IOException, Parser.Exception{
        super.recoverFromError(token,in);
        tainted = true;
    }

	private final Action[] actions;

	public LangParser() {
		super(PARSING_TABLES);
		actions = new Action[] {
			new Action() {	// [0] lst$dataDec = dataDec
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [1] lst$dataDec = lst$dataDec dataDec
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
				}
			},
			Action.NONE,  	// [2] opt$lst$dataDec = 
			Action.RETURN,	// [3] opt$lst$dataDec = lst$dataDec
			new Action() {	// [4] lst$functionDec = functionDec
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [5] lst$functionDec = lst$functionDec functionDec
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
				}
			},
			Action.NONE,  	// [6] opt$lst$functionDec = 
			Action.RETURN,	// [7] opt$lst$functionDec = lst$functionDec
			new Action() {	// [8] program = opt$lst$dataDec.dats opt$lst$functionDec.decs
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_dats = _symbols[offset + 1];
					final ArrayList _list_dats = (ArrayList) _symbol_dats.value;
					final Data[] dats = _list_dats == null ? new Data[0] : (Data[]) _list_dats.toArray(new Data[_list_dats.size()]);
					final Symbol _symbol_decs = _symbols[offset + 2];
					final ArrayList _list_decs = (ArrayList) _symbol_decs.value;
					final Func[] decs = _list_decs == null ? new Func[0] : (Func[]) _list_decs.toArray(new Func[_list_decs.size()]);
					 return new Program(dats, decs);
				}
			},
			new Action() {	// [9] lst$field = field
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [10] lst$field = lst$field field
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
				}
			},
			Action.NONE,  	// [11] opt$lst$field = 
			Action.RETURN,	// [12] opt$lst$field = lst$field
			new Action() {	// [13] dataDec = DATA TYID.n LBRACE opt$lst$field.f RBRACE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_n = _symbols[offset + 2];
					final String n = (String) _symbol_n.value;
					final Symbol _symbol_f = _symbols[offset + 4];
					final ArrayList _list_f = (ArrayList) _symbol_f.value;
					final Field[] f = _list_f == null ? new Field[0] : (Field[]) _list_f.toArray(new Field[_list_f.size()]);
					 return new Data(new TyID(n),f);
				}
			},
			new Action() {	// [14] field = ID.v TYBIND TYID.t SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final String v = (String) _symbol_v.value;
					final Symbol _symbol_t = _symbols[offset + 3];
					final String t = (String) _symbol_t.value;
					  return new Field(v,new TyID(t));
				}
			},
			new Action() {	// [15] functionDec = ID.n LPAREN RPAREN block.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_n = _symbols[offset + 1];
					final String n = (String) _symbol_n.value;
					final Symbol _symbol_b = _symbols[offset + 4];
					final Cmd b = (Cmd) _symbol_b.value;
					return new Func(new SType[0], n, new TyBind[0], b);
				}
			},
			new Action() {	// [16] functionDec = ID.n LPAREN RPAREN block.b COLON tyList.l
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_n = _symbols[offset + 1];
					final String n = (String) _symbol_n.value;
					final Symbol _symbol_b = _symbols[offset + 4];
					final Cmd b = (Cmd) _symbol_b.value;
					final Symbol _symbol_l = _symbols[offset + 6];
					final ArrayList _list_l = (ArrayList) _symbol_l.value;
					final SType[] l = _list_l == null ? new SType[0] : (SType[]) _list_l.toArray(new SType[_list_l.size()]);
					return new Func(l, n, new TyBind[0], b);
				}
			},
			new Action() {	// [17] functionDec = ID.n LPAREN varDecList.p RPAREN block.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_n = _symbols[offset + 1];
					final String n = (String) _symbol_n.value;
					final Symbol _symbol_p = _symbols[offset + 3];
					final ArrayList _list_p = (ArrayList) _symbol_p.value;
					final TyBind[] p = _list_p == null ? new TyBind[0] : (TyBind[]) _list_p.toArray(new TyBind[_list_p.size()]);
					final Symbol _symbol_b = _symbols[offset + 5];
					final Cmd b = (Cmd) _symbol_b.value;
					return new Func(new SType[0], n, p, b);
				}
			},
			new Action() {	// [18] functionDec = ID.n LPAREN varDecList.p RPAREN COLON tyList.l block.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_n = _symbols[offset + 1];
					final String n = (String) _symbol_n.value;
					final Symbol _symbol_p = _symbols[offset + 3];
					final ArrayList _list_p = (ArrayList) _symbol_p.value;
					final TyBind[] p = _list_p == null ? new TyBind[0] : (TyBind[]) _list_p.toArray(new TyBind[_list_p.size()]);
					final Symbol _symbol_l = _symbols[offset + 6];
					final ArrayList _list_l = (ArrayList) _symbol_l.value;
					final SType[] l = _list_l == null ? new SType[0] : (SType[]) _list_l.toArray(new SType[_list_l.size()]);
					final Symbol _symbol_b = _symbols[offset + 7];
					final Cmd b = (Cmd) _symbol_b.value;
					return new Func(l, n, p, b);
				}
			},
			new Action() {	// [19] tyList = tyList COMMA type
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [20] tyList = type
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [21] block = LBRACE commandList.c RBRACE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_c = _symbols[offset + 2];
					final Cmd c = (Cmd) _symbol_c.value;
					 return c;
				}
			},
			new Action() {	// [22] stmtBlock = block.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_b = _symbols[offset + 1];
					final Cmd b = (Cmd) _symbol_b.value;
					 return b;
				}
			},
			new Action() {	// [23] stmtBlock = cmd.c
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_c = _symbols[offset + 1];
					final Cmd c = (Cmd) _symbol_c.value;
					 return c;
				}
			},
			new Action() {	// [24] varDecList = varDecList COMMA varDec
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [25] varDecList = varDec
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [26] varDec = ID.i TYBIND type.t
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_t = _symbols[offset + 3];
					final SType t = (SType) _symbol_t.value;
					  return new TyBind(t,i);
				}
			},
			new Action() {	// [27] commandList = cmd.c commandList.l
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_c = _symbols[offset + 1];
					final Cmd c = (Cmd) _symbol_c.value;
					final Symbol _symbol_l = _symbols[offset + 2];
					final Cmd l = (Cmd) _symbol_l.value;
					 return new Seq(c,l);
				}
			},
			new Action() {	// [28] commandList = cmd.c
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_c = _symbols[offset + 1];
					final Cmd c = (Cmd) _symbol_c.value;
					 return c;
				}
			},
			new Action() {	// [29] cmd = location.v ATTR expr.e SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final Location v = (Location) _symbol_v.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final Expr e = (Expr) _symbol_e.value;
					 return new Attr(v,e);
				}
			},
			new Action() {	// [30] cmd = PRINT expr.e SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 2];
					final Expr e = (Expr) _symbol_e.value;
					 return new Print(e);
				}
			},
			new Action() {	// [31] cmd = READ location.l SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_l = _symbols[offset + 2];
					final Location l = (Location) _symbol_l.value;
					 return new Read(l);
				}
			},
			new Action() {	// [32] cmd = RETURN argList.r SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_r = _symbols[offset + 2];
					final ArrayList _list_r = (ArrayList) _symbol_r.value;
					final Expr[] r = _list_r == null ? new Expr[0] : (Expr[]) _list_r.toArray(new Expr[_list_r.size()]);
					 return new Return(r);
				}
			},
			Action.NONE,  	// [33] opt$argList = 
			Action.RETURN,	// [34] opt$argList = argList
			new Action() {	// [35] cmd = ID.v LPAREN opt$argList.c RPAREN RTL retList.l RTR SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final String v = (String) _symbol_v.value;
					final Symbol _symbol_c = _symbols[offset + 3];
					final ArrayList _list_c = (ArrayList) _symbol_c.value;
					final Expr[] c = _list_c == null ? new Expr[0] : (Expr[]) _list_c.toArray(new Expr[_list_c.size()]);
					final Symbol _symbol_l = _symbols[offset + 6];
					final ArrayList _list_l = (ArrayList) _symbol_l.value;
					final Location[] l = _list_l == null ? new Location[0] : (Location[]) _list_l.toArray(new Location[_list_l.size()]);
					 return new CallCmd(v,c,l);
				}
			},
			new Action() {	// [36] cmd = ID.v LPAREN opt$argList.c RPAREN SEMI
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final String v = (String) _symbol_v.value;
					final Symbol _symbol_c = _symbols[offset + 3];
					final ArrayList _list_c = (ArrayList) _symbol_c.value;
					final Expr[] c = _list_c == null ? new Expr[0] : (Expr[]) _list_c.toArray(new Expr[_list_c.size()]);
					 return new CallCmd(v,c,new Location[0]);
				}
			},
			new Action() {	// [37] cmd = stmt.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final Cmd s = (Cmd) _symbol_s.value;
					 return s;
				}
			},
			new Action() {	// [38] stmt = WHILE LPAREN expr.e RPAREN stmtBlock.c
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 3];
					final Expr e = (Expr) _symbol_e.value;
					final Symbol _symbol_c = _symbols[offset + 5];
					final Cmd c = (Cmd) _symbol_c.value;
					 return new While(e,c);
				}
			},
			new Action() {	// [39] stmt = ITERATE LPAREN expr.e RPAREN stmtBlock.c
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 3];
					final Expr e = (Expr) _symbol_e.value;
					final Symbol _symbol_c = _symbols[offset + 5];
					final Cmd c = (Cmd) _symbol_c.value;
					 return new Iterate(e,c);
				}
			},
			new Action() {	// [40] stmt = IF LPAREN expr.e RPAREN stmtBlock.c
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 3];
					final Expr e = (Expr) _symbol_e.value;
					final Symbol _symbol_c = _symbols[offset + 5];
					final Cmd c = (Cmd) _symbol_c.value;
					 return new If(e,c,null);
				}
			},
			new Action() {	// [41] stmt = IF LPAREN expr.e RPAREN stmtBlock.c ELSE stmtBlock.d
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 3];
					final Expr e = (Expr) _symbol_e.value;
					final Symbol _symbol_c = _symbols[offset + 5];
					final Cmd c = (Cmd) _symbol_c.value;
					final Symbol _symbol_d = _symbols[offset + 7];
					final Cmd d = (Cmd) _symbol_d.value;
					 return new If(e,c,d);
				}
			},
			new Action() {	// [42] expr = expr.a AND expr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new And(a ,b);
				}
			},
			new Action() {	// [43] expr = rexpr.e
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 1];
					final Expr e = (Expr) _symbol_e.value;
					 return e;
				}
			},
			new Action() {	// [44] rexpr = aexpr.a LT aexpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Lt(a,b);
				}
			},
			new Action() {	// [45] rexpr = rexpr.a EQ aexpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Eq(a,b);
				}
			},
			new Action() {	// [46] rexpr = rexpr.a NEQ aexpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Eq(a,b);
				}
			},
			Action.RETURN,	// [47] rexpr = aexpr
			new Action() {	// [48] aexpr = aexpr.a PLUS mexpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Add(a ,b);
				}
			},
			new Action() {	// [49] aexpr = aexpr.a SUB mexpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Sub(a ,b);
				}
			},
			new Action() {	// [50] aexpr = mexpr.m
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_m = _symbols[offset + 1];
					final Expr m = (Expr) _symbol_m.value;
					 return m;
				}
			},
			new Action() {	// [51] mexpr = mexpr.a MULT sexpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Mult(a ,b);
				}
			},
			new Action() {	// [52] mexpr = mexpr.a DIV sexpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Div(a ,b);
				}
			},
			new Action() {	// [53] mexpr = mexpr.a MOD sexpr.b
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 1];
					final Expr a = (Expr) _symbol_a.value;
					final Symbol _symbol_b = _symbols[offset + 3];
					final Expr b = (Expr) _symbol_b.value;
					 return new Mod(a ,b);
				}
			},
			new Action() {	// [54] mexpr = sexpr.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final Expr s = (Expr) _symbol_s.value;
					 return s;
				}
			},
			new Action() {	// [55] sexpr = NOT expr.a
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_a = _symbols[offset + 2];
					final Expr a = (Expr) _symbol_a.value;
					 return new Not(a);
				}
			},
			new Action() {	// [56] sexpr = TRUE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new BoolLit(true);
				}
			},
			new Action() {	// [57] sexpr = FALSE
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new BoolLit(false);
				}
			},
			new Action() {	// [58] sexpr = CHAR.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final Character s = (Character) _symbol_s.value;
					 return new CharLit(s);
				}
			},
			new Action() {	// [59] sexpr = INT.i
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_i = _symbols[offset + 1];
					final Integer i = (Integer) _symbol_i.value;
					 return new IntLit(i);
				}
			},
			new Action() {	// [60] sexpr = FLOAT.f
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_f = _symbols[offset + 1];
					final Float f = (Float) _symbol_f.value;
					 return new FloatLit(f);
				}
			},
			new Action() {	// [61] sexpr = NULL
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new NullLit();
				}
			},
			new Action() {	// [62] sexpr = ID.v LPAREN opt$argList.c RPAREN index.e
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final String v = (String) _symbol_v.value;
					final Symbol _symbol_c = _symbols[offset + 3];
					final ArrayList _list_c = (ArrayList) _symbol_c.value;
					final Expr[] c = _list_c == null ? new Expr[0] : (Expr[]) _list_c.toArray(new Expr[_list_c.size()]);
					final Symbol _symbol_e = _symbols[offset + 5];
					final Expr e = (Expr) _symbol_e.value;
					 return new Call(v, c, e);
				}
			},
			new Action() {	// [63] sexpr = location.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final Location v = (Location) _symbol_v.value;
					 return v;
				}
			},
			Action.NONE,  	// [64] opt$index = 
			Action.RETURN,	// [65] opt$index = index
			new Action() {	// [66] sexpr = NEW type.t opt$index.i
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_t = _symbols[offset + 2];
					final SType t = (SType) _symbol_t.value;
					final Symbol _symbol_i = _symbols[offset + 3];
					final Expr i = (Expr) _symbol_i.value;
					 return new Instanciate(t,i);
				}
			},
			new Action() {	// [67] sexpr = LPAREN expr.e RPAREN
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_e = _symbols[offset + 2];
					final Expr e = (Expr) _symbol_e.value;
					 return e;
				}
			},
			new Action() {	// [68] location = ID.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final String v = (String) _symbol_v.value;
					 return new Location(null,new Var(v));
				}
			},
			new Action() {	// [69] location = location.b DOT ID.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_b = _symbols[offset + 1];
					final Location b = (Location) _symbol_b.value;
					final Symbol _symbol_v = _symbols[offset + 3];
					final String v = (String) _symbol_v.value;
					 return new Location(b,new Var(v));
				}
			},
			new Action() {	// [70] location = location.b LBRACKET expr.idx RBRACKET
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_b = _symbols[offset + 1];
					final Location b = (Location) _symbol_b.value;
					final Symbol _symbol_idx = _symbols[offset + 3];
					final Expr idx = (Expr) _symbol_idx.value;
					 return new Location(b,new Index(idx));
				}
			},
			new Action() {	// [71] index = LBRACKET expr.idx RBRACKET
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_idx = _symbols[offset + 2];
					final Expr idx = (Expr) _symbol_idx.value;
					 return idx;
				}
			},
			new Action() {	// [72] argList = expr
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [73] argList = argList COMMA expr
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [74] retList = location.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 1];
					final Location v = (Location) _symbol_v.value;
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
				}
			},
			new Action() {	// [75] retList = retList COMMA location.v
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_v = _symbols[offset + 3];
					final Location v = (Location) _symbol_v.value;
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
				}
			},
			new Action() {	// [76] baseType = TYBOOL
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new TyBool();
				}
			},
			new Action() {	// [77] baseType = TYINT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new TyInt();
				}
			},
			new Action() {	// [78] baseType = TYCHAR
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new TyChar();
				}
			},
			new Action() {	// [79] baseType = TYFLOAT
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new TyFloat();
				}
			},
			new Action() {	// [80] baseType = TYVOID
				public Symbol reduce(Symbol[] _symbols, int offset) {
					 return new TyVoid();
				}
			},
			new Action() {	// [81] baseType = TYID.s
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_s = _symbols[offset + 1];
					final String s = (String) _symbol_s.value;
					 return new TyID(s);
				}
			},
			new Action() {	// [82] type = baseType.t
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_t = _symbols[offset + 1];
					final SType t = (SType) _symbol_t.value;
					 return t;
				}
			},
			new Action() {	// [83] type = type.t LBRACKET RBRACKET
				public Symbol reduce(Symbol[] _symbols, int offset) {
					final Symbol _symbol_t = _symbols[offset + 1];
					final SType t = (SType) _symbol_t.value;
					 return new TyArr(t);
				}
			}
		};
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		return actions[rule_num].reduce(_symbols, offset);
	}
}
