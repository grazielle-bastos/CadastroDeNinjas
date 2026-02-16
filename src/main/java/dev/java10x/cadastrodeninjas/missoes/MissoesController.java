package dev.java10x.cadastrodeninjas.missoes;

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
    public List<MissoesModel> listarMissao() {
      return missoesService.listarMissao();
    }

    @GetMapping("/listar/{id}")
    public MissoesModel listarMissaoPorId(@PathVariable Long id) {
        return missoesService.listarMissaoPorId(id);
    }

// POST - Mandar uma requisição para criar as missões
    @PostMapping("/criar")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missoes) {
        return missoesService.criarMissao(missoes);
    }

// PUT - Mandar uma requisição para alterar as missões
    @PutMapping("/alterar/{id}")
    public MissoesModel alterarMissao(@PathVariable Long id, @RequestBody MissoesModel missoesAtualizado) {
        return missoesService.atualizarMissoes(id, missoesAtualizado);
    }

  //DELETE - Mandar uma requisicão para deletar as missões
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissao(id);
    }
}
