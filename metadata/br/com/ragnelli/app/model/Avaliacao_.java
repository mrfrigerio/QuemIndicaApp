package br.com.ragnelli.app.model;

import br.com.ragnelli.app.model.Avaliacao.TipoAvaliacao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-19T05:26:31.688-0300")
@StaticMetamodel(Avaliacao.class)
public class Avaliacao_ {
	public static volatile SingularAttribute<Avaliacao, Integer> id;
	public static volatile SingularAttribute<Avaliacao, Morador> morador;
	public static volatile SingularAttribute<Avaliacao, Prestador> prestador;
	public static volatile SingularAttribute<Avaliacao, TipoAvaliacao> tipo;
	public static volatile SingularAttribute<Avaliacao, Date> data;
	public static volatile SingularAttribute<Avaliacao, String> motivo;
	public static volatile SingularAttribute<Avaliacao, Double> nota;
}
