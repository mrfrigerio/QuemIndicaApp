package br.com.ragnelli.app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-24T01:15:50.684-0300")
@StaticMetamodel(Prestador.class)
public class Prestador_ extends Pessoa_ {
	public static volatile SingularAttribute<Prestador, Endereco> endereco;
	public static volatile SingularAttribute<Prestador, String> Ramo;
	public static volatile ListAttribute<Prestador, Avaliacao> avaliacoes;
}
