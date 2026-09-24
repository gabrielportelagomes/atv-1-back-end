package br.com.dev.show.dto;

import br.com.dev.show.model.Project;

public record ProjectResponseDTO(
    Long id,
    String name,
    String technology,
    Long upvotes,
    Double averageRating
) {
    public static ProjectResponseDTO fromEntity(Project project) {
        return new ProjectResponseDTO(
            project.getId(),
            project.getName(),
            project.getTechnology(),
            project.getUpvotes(),
            project.getAverageRating()
        );
    }
}

