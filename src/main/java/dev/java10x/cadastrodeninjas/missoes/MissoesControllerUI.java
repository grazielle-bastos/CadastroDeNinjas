package dev.java10x.cadastrodeninjas.missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUI {

    private final MissoesService missoesService;

    public MissoesControllerUI(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissao(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissao();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/listar/{id}")
    public String listarMissaoPorId(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissaoPorId(id);

        if (missao != null) {
            model.addAttribute("missao", missao);
            return "detalhesmissoes";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada.");
            return "listarMissoes";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model) {
        model.addAttribute("missao", new MissoesDTO());
        return "adicionarMissoes";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.criarMissao(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão salva com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissaoPorId(@PathVariable Long id) {
        missoesService.deletarMissao(id);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String mostrarFormularioAlterarMissao(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissaoPorId(id);

        if (missao != null) {
            model.addAttribute("missao", missao);
            return "alterarMissoes";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada.");
            return "listarMissoes";
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarMissao(@PathVariable Long id, @ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.atualizarMissoes(id, missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão atualizada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

}