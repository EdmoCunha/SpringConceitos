package com.example.cadastroninjas.MissoesService;
import com.example.cadastroninjas.MissoesModel.MissoesModel;
import com.example.cadastroninjas.MissoesRepository.MissoesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
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
    public MissoesModel criarMissao(MissoesModel missaoCriada) {// passando
        return missoesRepository.save(missaoCriada);
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
