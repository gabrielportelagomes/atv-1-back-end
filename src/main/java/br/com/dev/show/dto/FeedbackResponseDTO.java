package br.com.dev.show.dto;

import br.com.dev.show.model.Feedback;

public record FeedbackResponseDTO(
    Long id,
    Integer rating,
    String comment
) {
    public static FeedbackResponseDTO fromEntity(Feedback feedback) {
        return new FeedbackResponseDTO(feedback.getId(), feedback.getRating(), feedback.getComment());
    }
}

