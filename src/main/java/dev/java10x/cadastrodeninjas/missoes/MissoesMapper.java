package dev.java10x.cadastrodeninjas.missoes;

import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {
    public MissoesModel map(MissoesDTO missoesDTO) {
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(missoesDTO.getId());
        missoesModel.setTitulo(missoesDTO.getTitulo());
        missoesModel.setDescricao(missoesDTO.getDescricao());
        missoesModel.setNinja(missoesDTO.getNinja());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel) {
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setTitulo(missoesModel.getTitulo());
        missoesDTO.setDescricao(missoesModel.getDescricao());
        missoesDTO.setNinja(missoesModel.getNinja());

        return missoesDTO;
    }

}