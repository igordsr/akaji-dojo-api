package br.com.akaji.dojo.interfaces;

public interface ValueObject <Type>{
	abstract Type map();
	abstract Type map(Type obj);
}
