package mincamlj;

import mincamlj.type.ArrayType;
import mincamlj.type.BoolType;
import mincamlj.type.FloatType;
import mincamlj.type.FunType;
import mincamlj.type.IntType;
import mincamlj.type.TupleType;
import mincamlj.type.Type;
import mincamlj.type.UnitType;
import mincamlj.type.VarType;

public class Id {

	private static int counter = 0;

	public static String prefixOfType(Type t) {
		if (t instanceof UnitType) {
			return "u";
		} else if (t instanceof BoolType) {
			return "b";
		} else if (t instanceof IntType) {
			return "i";
		} else if (t instanceof FloatType) {
			return "d";
		} else if (t instanceof FunType) {
			return "f";
		} else if (t instanceof TupleType) {
			return "t";
		} else if (t instanceof ArrayType) {
			return "a";
		} else if (t instanceof VarType) {
			// TODO
		}
		return "_";
	}

	public static String genTmp(Type t) {
		return "T" + prefixOfType(t) + (counter++);
	}

	public static String genId(String s) {
		return s + "." + (counter++);
	}

}
