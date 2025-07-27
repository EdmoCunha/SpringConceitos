package com.example.cadastroninjas.MissoesMapper;

import com.example.cadastroninjas.MissoesDTO.MissoesDTO;
import com.example.cadastroninjas.MissoesModel.MissoesModel;
import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {

    public MissoesModel map(MissoesDTO missoesDTO) {
        MissoesModel missoesModel = new MissoesModel();

        missoesModel.setId(missoesDTO.getId());
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());
        missoesModel.setNinjas(missoesDTO.getNinjas());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel) {
        MissoesDTO missoesDTO = new MissoesDTO();

        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNome(missoesModel.getNome());
        missoesDTO.setNinjas(missoesModel.getNinjas());
        missoesDTO.setDificuldade(missoesModel.getDificuldade());


        return missoesDTO;
    }
}
