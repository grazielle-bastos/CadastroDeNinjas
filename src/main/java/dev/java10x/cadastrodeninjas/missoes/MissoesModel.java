package dev.java10x.cadastrodeninjas.missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.cadastrodeninjas.ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    // @OneToMany - Uma missão pode ter vários ninjas
    @OneToMany(mappedBy = "missoes")
    @Column(name = "ninja")
    @JsonIgnore
    private List<NinjaModel> ninja;

}
