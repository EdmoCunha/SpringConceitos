package com.example.cadastroninjas.NinjasService;
import com.example.cadastroninjas.NinjasDTO.NinjaDTO;
import com.example.cadastroninjas.NinjasMapper.NinjaMapper;
import com.example.cadastroninjas.NinjasModel.NinjaModel;
import com.example.cadastroninjas.NinjasRepository.NinjaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

//    //Listar todos os meus ninjas
//    public List<NinjaModel> listarNinjas() {
//        return ninjaRepository.findAll();
//    }


    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());

    }

    //Listar ninja por id (Read)
//    public  NinjaModel listarNinjasPorId(Long id) {
//    Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
//     return ninjaPorId.orElse(null);    }

    public NinjaDTO listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map)
                .orElse(null);
    }

    // adicionar ninja (Create) public NinjaModel criarNinja(NinjaModel ninjaCriado) {// passando
    //      return ninjaRepository.save(ninjaCriado); antes do Dto a responsa estava no model

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }


    //deletar Ninja(Deletar) tem que ser metodo void e ele também não retorna nada
    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id);
    }

    //Alterar dados (Update)
//    public NinjaModel alterarNinjaPorId(Long id, NinjaModel ninjaAlterado) {
//      if(ninjaRepository.existsById(id)){ MUDEI PARA OPTIONAL POR PURO TESTE
//          ninjaAlterado.setId(id);
//          return ninjaRepository.save(ninjaAlterado);
//      }
//          return null;

    public NinjaDTO alterarNinjaPorId(Long id, NinjaDTO ninjaAlterado) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        if (ninjaPorId.isPresent()) {
            NinjaModel ninjaAlteradoModel = ninjaMapper.map(ninjaAlterado);
            ninjaAlterado.setId(id);
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAlteradoModel);
            return ninjaMapper.map(ninjaAlteradoModel);
        }
        return null;


    }

}


