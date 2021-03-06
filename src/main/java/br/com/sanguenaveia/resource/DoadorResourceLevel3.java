package br.com.sanguenaveia.resource;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sanguenaveia.domain.Doador;
import br.com.sanguenaveia.service.DoadorService;

@RestController
@RequestMapping("/doadores3")
public class DoadorResourceLevel3 {
	
	@Autowired
	private DoadorService doadorService;

	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
			//passar no Header o Accept com o valor application/xml para ter o retorno em XML
		})
	public ResponseEntity<List<Doador>> listar() {
		return ResponseEntity.ok(doadorService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Doador doador) {
		
		doador = doadorService.salvar(doador);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(doador.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Doador doador = doadorService.buscar(id);
		Resource<Doador> resource = new Resource<Doador>(doador);
		resource.add(
			ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DoadorResourceLevel3.class).buscar(id)).withRel(RequestMethod.GET.name()),
			ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DoadorResourceLevel3.class).deletar(id)).withRel(RequestMethod.DELETE.name()),
			ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DoadorResourceLevel3.class).atualizar(doador)).withRel(RequestMethod.PUT.name())
		);
		
		//válida por 20 segundos cacheada no cliente		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(resource);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		doadorService.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@RequestBody Doador doador) {
		
		doadorService.atualizar(doador);
		
		Resource<Doador> resource = new Resource<Doador>(doador);
		resource.add(
			ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DoadorResourceLevel3.class).buscar(doador.getId())).withRel(RequestMethod.GET.name()),
			ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DoadorResourceLevel3.class).deletar(doador.getId())).withRel(RequestMethod.DELETE.name())
		);
		
		return ResponseEntity.status(HttpStatus.OK).body(resource);
	}
}
