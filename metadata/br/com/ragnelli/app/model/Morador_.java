package br.com.ragnelli.app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-24T02:39:52.426-0300")
@StaticMetamodel(Morador.class)
public class Morador_ extends Pessoa_ {
	public static volatile SingularAttribute<Morador, String> senha;
	public static volatile SingularAttribute<Morador, Condominio> condominio;
	public static volatile SingularAttribute<Morador, Bloco> bloco;
	public static volatile SingularAttribute<Morador, Integer> unidade;
	public static volatile ListAttribute<Morador, Avaliacao> avaliacoes;
}
