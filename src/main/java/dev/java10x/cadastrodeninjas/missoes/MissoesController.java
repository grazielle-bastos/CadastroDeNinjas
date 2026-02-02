package dev.java10x.cadastrodeninjas.missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

// GET - Mandar uma requisição para mostrar as missões
  @GetMapping("/listar")
    public String listarMissao() {
        return "Missões listadas com sucesso";
    }

// POST - Mandar uma requisição para criar as missões
    @PostMapping("/criar")
    public String criarMissao() {
        return "Missao criada com sucesso";
    }

// PUT - Mandar uma requisição para alterar as missões
    @PutMapping("/alterar")
    public String alterarMissao() {
        return "Missao alterada com sucesso";
    }

  //DELETE - Mandar uma requisicão para deletar as missões
    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "Missao deletada com sucesso";
    }
}
