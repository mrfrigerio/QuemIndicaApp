package br.com.ragnelli.app.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("telefoneValidator")
public class TelefoneValidator implements Validator<String> {

	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

		Pattern pattern = Pattern.compile("9?\\d{4}-?\\d{4}");
		Matcher matcher = pattern.matcher(value);
		
		if (!value.isBlank() && !matcher.matches()) {
			throw new ValidatorException(new FacesMessage("O telefone digitado é inválido!"));
		}
	}
	
}
