package br.com.ragnelli.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Avaliacao implements Serializable {
	
	public enum TipoAvaliacao {
		Positiva, Negativa;
	}

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_Id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "prestador_id")
	private Prestador prestador;
	
	@Enumerated(EnumType.STRING)
	private TipoAvaliacao tipo;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	private String motivo;
	private Double nota;
	
	public Avaliacao() {
	}
	
	public Avaliacao(Date data, Usuario usuario, TipoAvaliacao tipo, Prestador prestador, String motivo, Double nota) {
		this.data = data;
		this.usuario = usuario;
		this.tipo = tipo;
		this.prestador = prestador;
		this.motivo = motivo;
		this.nota = nota;
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public TipoAvaliacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoAvaliacao tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Avaliacao))
			return false;
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
