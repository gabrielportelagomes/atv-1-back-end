package br.com.dev.show.dto;

import br.com.dev.show.model.Technology;

public record TechnologyResponseDTO(Long id, String name) {
    
	public static TechnologyResponseDTO fromEntity(Technology tech) {
        return new TechnologyResponseDTO(tech.getId(), tech.getName());
    }
	
}