package dev.java10x.cadastrodeninjas.ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    final private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    //GET - Mandar uma requisição para mostrar uma mensagem de boas-vindas (READ)
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    //POST - Mandar uma requisição para criar um ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    //GET - Mandar uma requisição para mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    //GET - Mandar uma requisição para mostrar todos os ninjas por ID (READ)
    @GetMapping("/listarID")
    public String mostrarTodosOsNinjasPorID() {
        return "Todos Ninja por ID";
    }

    //PUT - Mandar uma requisição para alterar um ninja por ID (UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinjaPorID() {
        return "Alterar ninja por ID";
    }

    //DELETE - Mandar uma requisição para deletar um ninja por ID (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorID() {
        return "Deletar ninja por ID";
    }
}
