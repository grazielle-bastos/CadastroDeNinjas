package dev.java10x.cadastrodeninjas.missoes;

import dev.java10x.cadastrodeninjas.ninjas.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissoesDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private List<NinjaModel> ninja;
}
