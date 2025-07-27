package com.example.cadastroninjas.NinjasService;
import com.example.cadastroninjas.NinjasModel.NinjaModel;
import com.example.cadastroninjas.NinjasRepository.NinjaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }



    //Listar todos os meus ninjas
    public List<NinjaModel> listarNinjas() {
        return ninjaRepository.findAll();
    }

    //Listar ninja por id (Read)
    public  NinjaModel listarNinjasPorId(Long id) {
    Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
     return ninjaPorId.orElse(null);

    }

    // adicionar ninja (Create)
    public NinjaModel criarNinja(NinjaModel ninjaCriado) {// passando
      return ninjaRepository.save(ninjaCriado);
    }


    //deletar Ninja(Deletar) tem que ser metodo void e ele também não retorna nada
    public void deletarNinjaPorId(Long id) {
         ninjaRepository.deleteById(id);
    }

    //Alterar dados (Update)
    public NinjaModel alterarNinjaPorId(Long id, NinjaModel ninjaAlterado) {
      if(ninjaRepository.existsById(id)){
          ninjaAlterado.setId(id);
          return ninjaRepository.save(ninjaAlterado);
      }
          return null;
    }

}
