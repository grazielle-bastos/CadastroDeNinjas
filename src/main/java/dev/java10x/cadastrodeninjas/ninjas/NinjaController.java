package dev.java10x.cadastrodeninjas.ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    @GetMapping("/todos")
    public String mostrarTodosOsNinjas() {
        return "Todos os ninjas";
    }

    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorID() {
        return "Todos Ninja por ID";
    }

    @PutMapping("/alterarID")
    public String alterarNinjaPorID() {
        return "Alterar ninja por ID";
    }

    @DeleteMapping("/deletarID")
    public String deletarNinjaPorID() {
        return "Deletar ninja por ID";
    }
}
