package dev.java10x.cadastrodeninjas.ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaDTO ninjaDTO) {
        NinjaModel ninjaModel = new NinjaModel();
        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setEmail(ninjaDTO.getEmail());

        Integer idadeDTO = ninjaDTO.getIdade();
        ninjaModel.setIdade(idadeDTO == null ? 0 : idadeDTO);

        ninjaModel.setImgUrl(ninjaDTO.getImgUrl());
        ninjaModel.setMissoes(ninjaDTO.getMissoes());
        ninjaModel.setRank(ninjaDTO.getRank());

        return ninjaModel;
    }

    public NinjaDTO map(NinjaModel ninjaModel) {
        NinjaDTO ninjaDTO = new NinjaDTO();
        ninjaDTO.setId(ninjaModel.getId());
        ninjaDTO.setNome(ninjaModel.getNome());
        ninjaDTO.setEmail(ninjaModel.getEmail());
        ninjaDTO.setImgUrl(ninjaModel.getImgUrl());

        Integer idadeModel = ninjaModel.getIdade();
        ninjaDTO.setIdade(idadeModel == null ? 0 : idadeModel);  // trata null -> 0

        ninjaDTO.setMissoes(ninjaModel.getMissoes());
        ninjaDTO.setRank(ninjaModel.getRank());

        return ninjaDTO;
        }
}
