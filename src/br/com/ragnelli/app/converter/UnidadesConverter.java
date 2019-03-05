package br.com.ragnelli.app.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ragnelli.app.util.StringUtils;

@FacesConverter("unidadesConverter")
public class UnidadesConverter implements Converter<List<Integer>> {

	@Override
	public List<Integer> getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isBlank()) {
			return null;
		}
		return StringUtils.unidadesParser(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, List<Integer> value) {
		if (value == null) {
			return null;
		}
		return value.stream().map(e -> e.toString()).collect(Collectors.joining(", "));
	}

}
