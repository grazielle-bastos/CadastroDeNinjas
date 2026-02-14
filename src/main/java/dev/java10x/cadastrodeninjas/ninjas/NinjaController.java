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
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja) {
        return ninjaService.criarNinja(ninja);
    }

    //GET - Mandar uma requisição para mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    //GET - Mandar uma requisição para mostrar todos os ninjas por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    //PUT - Mandar uma requisição para alterar um ninja por ID (UPDATE)
    @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinjaPorID(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado) {
        return ninjaService.atualizarNinja(id, ninjaAtualizado);
    }

    //DELETE - Mandar uma requisição para deletar um ninja por ID (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorID(@PathVariable Long id) {
        ninjaService.deletarNinja(id);
    }
}
