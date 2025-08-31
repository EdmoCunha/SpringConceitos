package com.example.cadastroninjas.MissoesService;
import com.example.cadastroninjas.MissoesDTO.MissoesDTO;
import com.example.cadastroninjas.MissoesMapper.MissoesMapper;
import com.example.cadastroninjas.MissoesModel.MissoesModel;
import com.example.cadastroninjas.MissoesRepository.MissoesRepository;
import com.example.cadastroninjas.NinjasModel.NinjaModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesMapper missoesMapper;
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //Listar todas as minhas missões
    public List<MissoesDTO> listarTodasAsMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream().map(missoesMapper::map).collect(Collectors.toList());
    }

    //Listar missoes por id (Read)
    public  MissoesDTO listarMissaoPorId(Long id) {
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.map( missoesMapper::map).orElse(null);

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
    public MissoesDTO alterarMissaoPorId(Long id, MissoesDTO missaoAlterada) {
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        if (missaoPorId.isPresent()) {
            MissoesModel missoesAlteradasModel = missoesMapper.map(missaoAlterada);
            missaoAlterada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missoesAlteradasModel);
            return missoesMapper.map(missoesAlteradasModel);
        }
        return null;
    }
}
