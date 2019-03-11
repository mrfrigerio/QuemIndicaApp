package br.com.ragnelli.app.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ragnelli.app.ejb.CadastroMoradorEJB;
import br.com.ragnelli.app.model.Condominio;

@FacesConverter(value = "condominioConverter", managed = true)
public class CondominioConverter implements Converter<Condominio> {
	
	@EJB
	CadastroMoradorEJB cadastro;
	
	@Override
	public Condominio getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isBlank()) {
			Condominio condominio = cadastro.buscarCondominioById(Integer.valueOf(value.trim()));
			return condominio;
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Condominio value) {
		if (value != null) {
			return value.toString();
		}
		return null;
	}
}
