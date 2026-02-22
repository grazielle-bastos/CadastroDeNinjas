package dev.java10x.cadastrodeninjas.missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService){
        this.missoesService = missoesService;
    }

// GET - Mandar uma requisição para mostrar as missões
  @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissao() {
        List<MissoesDTO> missoes = missoesService.listarMissao();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id) {
        MissoesDTO missoes = missoesService.listarMissaoPorId(id);

        if (missoes != null) {
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com o ID " + id + " não existe nos nossos registros");
        }
    }

// POST - Mandar uma requisição para criar as missões
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoes) {
        MissoesDTO novaMissao = missoesService.criarMissao(missoes);
        return ResponseEntity.status(HttpStatus.CREATED).body("Missão criada com sucesso: " + novaMissao.getTitulo() + " (ID): " + novaMissao.getId());
    }

// PUT - Mandar uma requisição para alterar as missões
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesAtualizado) {
        MissoesDTO missoes = missoesService.atualizarMissoes(id, missoesAtualizado);

        if (missoes != null){
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com o ID " + id + " não existe nos nossos registros");
        }
    }

  //DELETE - Mandar uma requisicão para deletar as missões
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id) {
        if (missoesService.listarMissaoPorId(id) != null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Missão com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com o ID " + id + " não existe nos nossos registros");
        }
    }
}
