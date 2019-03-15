package br.com.ragnelli.app.ejb;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ApplicationEJB {

	private List<String> estados;
		
		@PostConstruct
		public void init() {
				estados = Arrays.asList(
				"Acre",
				"Alagoas",
				"Amapá",
				"Amazonas",
				"Bahia",
				"Ceará",
				"Distrito Federal",
				"Espírito Santo",
				"Goiás",
				"Maranhão",
				"Mato Grosso",
				"Mato Grosso do Sul",
				"Minas Gerais",
				"Pará",
				"Paraíba",
				"Paraná",
				"Pernambuco",
				"Piauí",
				"Rio de Janeiro",
				"Rio Grande do Norte",
				"Rio Grande do Sul",
				"Rondônia",
				"Roraima",
				"Santa Catarina",
				"São Paulo",
				"Sergipe",
				"Tocantins");
		
		}

		public List<String> getEstados() {
			return estados;
		}

		public void setEstados(List<String> estados) {
			this.estados = estados;
		}
		
	
	
}
