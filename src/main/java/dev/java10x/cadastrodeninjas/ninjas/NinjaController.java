package dev.java10x.cadastrodeninjas.ninjas;

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
@RequestMapping("/ninjas")
@Tag(name = "Ninjas", description = "Operações de cadastro e gerenciamento de ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @Operation(summary = "Boas-vindas", description = "Retorna uma mensagem de boas-vindas da API")
    @ApiResponse(responseCode = "200", description = "Mensagem retornada com sucesso")
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    @Operation(summary = "Criar ninja", description = "Cadastra um novo ninja no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do ninja a ser criado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))
            )
            @RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    @Operation(summary = "Listar todos os ninjas", description = "Retorna a lista completa de ninjas cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = NinjaDTO.class)))
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @Operation(summary = "Buscar ninja por ID", description = "Retorna os dados de um ninja específico pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja encontrado",
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado", content = @Content)
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "ID do ninja", required = true, example = "1")
            @PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID " + id + " não existe nos nossos registros");
        }
    }

    @Operation(summary = "Alterar ninja", description = "Atualiza os dados de um ninja existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado", content = @Content)
    })
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaPorID(
            @Parameter(description = "ID do ninja a ser alterado", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados do ninja",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NinjaDTO.class))
            )
            @RequestBody NinjaDTO ninjaAtualizado) {
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID " + id + " não existe nos nossos registros");
        }
    }

    @Operation(summary = "Deletar ninja", description = "Remove um ninja do sistema pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado", content = @Content)
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorID(
            @Parameter(description = "ID do ninja a ser deletado", required = true, example = "1")
            @PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID " + id + " não encontrado");
        }
    }
}