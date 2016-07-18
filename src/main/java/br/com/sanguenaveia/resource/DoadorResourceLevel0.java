package br.com.sanguenaveia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sanguenaveia.domain.Doador;
import br.com.sanguenaveia.repository.DoadorRepository;

@RestController
@RequestMapping
public class DoadorResourceLevel0 {
	
	@Autowired
	private DoadorRepository doadorRepository;

	@RequestMapping(value="/listarDoador", method = RequestMethod.GET)
	public List<Doador> listar() {
		return doadorRepository.findAll();
	}
	
	@RequestMapping(value="/salvarDoador", method = RequestMethod.POST)
	public void salvar(@RequestBody Doador doador) {
		doadorRepository.save(doador);
	}
	
	@RequestMapping(value="/recuperarDoador/{id}", method = RequestMethod.GET)
	public Doador buscar(@PathVariable("id") Long id) {
		return doadorRepository.findOne(id);
	}
	
	@RequestMapping(value = "deletarDoador/{id}", method = RequestMethod.DELETE)
	public void deletar(@PathVariable("id") Long id) {
		doadorRepository.delete(id);
	}
	
	@RequestMapping(value="/atualizarDoador", method = RequestMethod.PUT)
	public void atualizar(@RequestBody Doador doador) {
//		doador.setId(id);
		doadorRepository.save(doador);
	}
}
