package com.example.cadastroninjas.MissoesService;
import com.example.cadastroninjas.MissoesDTO.MissoesDTO;
import com.example.cadastroninjas.MissoesMapper.MissoesMapper;
import com.example.cadastroninjas.MissoesModel.MissoesModel;
import com.example.cadastroninjas.MissoesRepository.MissoesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private final MissoesMapper missoesMapper;
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //Listar todas as minhas missões
    public List<MissoesModel> listarTodasAsMissoes() {
        return missoesRepository.findAll();
    }

    //Listar missoes por id (Read)
    public  MissoesModel listarMissaoPorId(Long id) {
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.orElse(null);

    }

    // adicionar missao (Create)
//    public MissoesModel criarMissao(MissoesModel missaoCriada) {// passando
//        return missoesRepository.save(missaoCriada);}

    public MissoesDTO criarMissao(MissoesDTO missaocriada) {
        MissoesModel missao = missoesMapper.map(missaocriada);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
    }




    //deletar missão (Deletar) tem que ser metodo void e ele também não retorna nada
    public void deletarMissaoPorId(Long id) {
        missoesRepository.deleteById(id);
    }

    //Alterar dados (Update)
    public MissoesModel alterarMissaoPorId(Long id, MissoesModel missaoAlterada) {
        if(missoesRepository.existsById(id)){
            missaoAlterada.setId(id);
            return missoesRepository.save(missaoAlterada);
        }
        return null;
    }
}
