package dev.java10x.cadastrodeninjas.missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
@Tag(name = "Missões", description = "Operações de cadastro e gerenciamento de missões")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @Operation(summary = "Listar todas as missões", description = "Retorna a lista completa de missões cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = MissoesDTO.class)))
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissao() {
        List<MissoesDTO> missoes = missoesService.listarMissao();
        return ResponseEntity.ok(missoes);
    }

    @Operation(summary = "Buscar missão por ID", description = "Retorna os dados de uma missão específica pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão encontrada",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada", content = @Content)
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(
            @Parameter(description = "ID da missão", required = true, example = "1")
            @PathVariable Long id) {
        MissoesDTO missoes = missoesService.listarMissaoPorId(id);

        if (missoes != null) {
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe nos nossos registros");
        }
    }

    @Operation(summary = "Criar missão", description = "Cadastra uma nova missão no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da missão a ser criada",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))
            )
            @RequestBody MissoesDTO missoes) {
        MissoesDTO novaMissao = missoesService.criarMissao(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso: " + novaMissao.getTitulo() + " (ID): " + novaMissao.getId());
    }

    @Operation(summary = "Alterar missão", description = "Atualiza os dados de uma missão existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada", content = @Content)
    })
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(
            @Parameter(description = "ID da missão a ser alterada", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados da missão",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))
            )
            @RequestBody MissoesDTO missoesAtualizado) {
        MissoesDTO missoes = missoesService.atualizarMissoes(id, missoesAtualizado);

        if (missoes != null) {
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe nos nossos registros");
        }
    }

    @Operation(summary = "Deletar missão", description = "Remove uma missão do sistema pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada", content = @Content)
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "ID da missão a ser deletada", required = true, example = "1")
            @PathVariable Long id) {
        if (missoesService.listarMissaoPorId(id) != null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Missão com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe nos nossos registros");
        }
    }
}