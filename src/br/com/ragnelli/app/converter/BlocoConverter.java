package br.com.ragnelli.app.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ragnelli.app.ejb.CadastroCondominioEJB;
import br.com.ragnelli.app.model.Bloco;

@FacesConverter(value = "blocoConverter", managed = true)
public class BlocoConverter implements Converter<Bloco> {
	
	@EJB
	CadastroCondominioEJB cadastro;
	
	@Override
	public Bloco getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isBlank()) {
			Bloco bloco = cadastro.buscarBlocoPorId(Integer.valueOf(value.trim()));
			return bloco;
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Bloco value) {
		if (value != null) {
			return value.toString();
		}
		return null;
	}
}
