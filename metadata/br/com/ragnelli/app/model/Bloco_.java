package br.com.ragnelli.app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-24T03:31:13.877-0300")
@StaticMetamodel(Bloco.class)
public class Bloco_ {
	public static volatile SingularAttribute<Bloco, String> nome;
	public static volatile ListAttribute<Bloco, Integer> unidades;
	public static volatile SingularAttribute<Bloco, Integer> id;
	public static volatile SingularAttribute<Bloco, Condominio> condominio;
}
