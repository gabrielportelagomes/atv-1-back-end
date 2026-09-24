package br.com.dev.show.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.show.dto.TechnologyResponseDTO;
import br.com.dev.show.service.TechnologyService;

@RestController @RequestMapping("/api/technologies")
public class TechnologyControlle {

	private final TechnologyService service;

	TechnologyControlle(TechnologyService service) {
		this.service = service;
	}
	
	@GetMapping
	 public ResponseEntity<List<TechnologyResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
	}
}

