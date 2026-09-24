package br.com.dev.show.controller;

import br.com.dev.show.exception.ApiError;
import br.com.dev.show.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    
    public record UsuarioRequestDTO(@NotBlank(message = "O nome é obrigatório") String nome) {}
    public record UsuarioResponseDTO(Long id, String nome) {}

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar usuário por ID",
        description = "Retorna os detalhes de um usuário específico a partir do seu identificador numérico.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetro inválido",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
        }
    )
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID informado deve ser maior que zero.");
        }
        if (id == 999L) {
            throw new ResourceNotFoundException("Usuário com ID " + id + " não encontrado.");
        }
        return ResponseEntity.ok(new UsuarioResponseDTO(id, "Exemplo de Usuário"));
    }

    @PostMapping
    @Operation(
        summary = "Cadastrar novo usuário",
        description = "Cria um novo usuário na base de dados validando os campos obrigatórios.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
        }
    )
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioResponseDTO(1L, request.nome()));
    }
}