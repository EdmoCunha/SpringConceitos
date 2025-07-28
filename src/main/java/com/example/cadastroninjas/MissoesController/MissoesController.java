package com.example.cadastroninjas.MissoesController;
import com.example.cadastroninjas.MissoesDTO.MissoesDTO;
import com.example.cadastroninjas.MissoesModel.MissoesModel;

import com.example.cadastroninjas.MissoesService.MissoesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {


    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    //Mostrar todos as missoes(Read)
    @GetMapping("/listar")
    public List<MissoesModel> listarTodasAsMissoes() {
        return missoesService.listarTodasAsMissoes();
    }


    // adicionar missão (Create)
    @PostMapping("/criar")
//    public MissoesModel criarMissao(@RequestBody MissoesModel missoesModel) {
//        return missoesService.criarMissao(missoesModel);    }
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missoesDTO) {
        return missoesService.criarMissao(missoesDTO);
    }

    //listar missao por id (Update)
    @PutMapping("/listar/{id}")
    public MissoesModel listarMissaoPorId(@PathVariable Long id) {
        return missoesService.listarMissaoPorId(id);
    }


    //deletar Ninja(Deletar)
    @DeleteMapping("/deletar/{id}")
    public void deletarMissaoPorId(@PathVariable Long id) {
        missoesService.deletarMissaoPorId(id);
    }


    //alterar misao  por id (Read)
    @PutMapping("/alterar/{id}")
    public MissoesModel alterarMissao(@PathVariable Long id, @RequestBody MissoesModel missoesModel) {
        return missoesService.alterarMissaoPorId(id, missoesModel);
    }


}




