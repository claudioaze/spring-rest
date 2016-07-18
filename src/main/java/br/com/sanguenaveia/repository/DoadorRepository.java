package br.com.sanguenaveia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sanguenaveia.domain.Doador;

public interface DoadorRepository extends JpaRepository<Doador, Long>{

}
