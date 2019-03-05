package br.com.ragnelli.app.model;

import br.com.ragnelli.app.model.Condominio.TipoCondominio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-24T03:21:13.818-0300")
@StaticMetamodel(Condominio.class)
public class Condominio_ {
	public static volatile SingularAttribute<Condominio, Integer> id;
	public static volatile SingularAttribute<Condominio, String> nome;
	public static volatile SingularAttribute<Condominio, Endereco> endereco;
	public static volatile SingularAttribute<Condominio, Telefone> telefone;
	public static volatile SingularAttribute<Condominio, TipoCondominio> tipo;
	public static volatile ListAttribute<Condominio, Morador> moradores;
	public static volatile ListAttribute<Condominio, Bloco> blocos;
}
