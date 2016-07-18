package br.com.sanguenaveia.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import br.com.sanguenaveia.domain.Doador;
import br.com.sanguenaveia.repository.DoadorRepository;
import br.com.sanguenaveia.service.exception.DoadorNaoEncontradoException;
import br.com.sanguenaveia.service.exception.IdentificadorNullException;

@Service
public class DoadorService {

	private static final String MENSAGEM_DOADOR_NAO_ENCONTRADO = "O doador não foi encontrado";
	private static final String MENSAGEM_ID_NULL = "O id não pode ser nulo";
	@Autowired
	private DoadorRepository doadorRepository;
	
	public List<Doador> listar() {
		return doadorRepository.findAll();
	}
	
	public Doador buscar(Long id) {
		Doador doador = null;
		
		try {
			doador = doadorRepository.findOne(id);
		} catch (InvalidDataAccessApiUsageException e) {
			throw new IdentificadorNullException(MENSAGEM_ID_NULL);
		}
		
		if(Objects.isNull(doador)) {
			throw new DoadorNaoEncontradoException(MENSAGEM_DOADOR_NAO_ENCONTRADO);
		}
		
		return doador;
	}
	
	public Doador salvar(Doador doador) {
		doador.setId(null);
		doador.setDataCadastro(new Date());
		return doadorRepository.save(doador);		
	}
	
	public void deletar(Long id) {
		try {
			doadorRepository.delete(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new DoadorNaoEncontradoException(MENSAGEM_DOADOR_NAO_ENCONTRADO);
		}
	}
	
	public void atualizar(Doador doador) {
		verificarExistencia(doador);
		doadorRepository.save(doador);
	}
	
	private void verificarExistencia(Doador doador) {
		buscar(doador.getId());
	}
}
