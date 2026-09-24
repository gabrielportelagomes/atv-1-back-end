package br.com.dev.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.dev.show.model.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long>{
	
}
