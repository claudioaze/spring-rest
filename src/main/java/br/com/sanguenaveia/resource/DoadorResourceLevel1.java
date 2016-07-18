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
@RequestMapping("/doadores1")
public class DoadorResourceLevel1 {
	
	@Autowired
	private DoadorRepository doadorRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Doador> listar() {
		return doadorRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void salvar(@RequestBody Doador doador) {
		doadorRepository.save(doador);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Doador buscar(@PathVariable("id") Long id) {
		return doadorRepository.findOne(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletar(@PathVariable("id") Long id) {
		doadorRepository.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void atualizar(@RequestBody Doador doador) {
		doadorRepository.save(doador);
	}
}
