package dev.java10x.cadastrodeninjas.ninjas;

import dev.java10x.cadastrodeninjas.missoes.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private Long id;
    private String nome;
    private String email;
    private String imgUrl;
    private Integer idade;
    private String rank;
    private MissoesModel missoes;
}
