package mincamlj;

import java.util.function.Function;

import mincamlj.knormal.KLet;
import mincamlj.knormal.KNormalExpr;
import mincamlj.knormal.KVar;
import mincamlj.type.Type;
import mincamlj.util.Pair;

public class KNormal {

	public Pair<KNormalExpr, Type> insertLet(Pair<KNormalExpr, Type> et,
			Function<KNormalExpr, Pair<KNormalExpr, Type>> k) {
		KNormalExpr e = et.getLeft();
		Type t = et.getRight();
		if (e instanceof KVar) {
			KVar e1 = (KVar) e;
			return k.apply(e1);
		}
		KVar x = new KVar(Id.genTmp(t));
		Pair<KNormalExpr, Type> newEt = k.apply(x);
		return new Pair<KNormalExpr, Type>(new KLet(new Pair<String, Type>(
				x.getName(), t), e, newEt.getLeft()), newEt.getRight());
	}

}
