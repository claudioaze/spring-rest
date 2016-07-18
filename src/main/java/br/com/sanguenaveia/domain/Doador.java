package br.com.sanguenaveia.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="doador")
@PrimaryKeyJoinColumn(name = "id") 
public class Doador extends PessoaFisica {

	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_NULL)
	private Boolean doadorMedula;

	public Boolean getDoadorMedula() {
		return doadorMedula;
	}

	public void setDoadorMedula(Boolean doadorMedula) {
		this.doadorMedula = doadorMedula;
	}
	
}