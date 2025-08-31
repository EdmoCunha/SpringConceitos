package com.example.cadastroninjas.MissoesController;
import com.example.cadastroninjas.MissoesDTO.MissoesDTO;
import com.example.cadastroninjas.MissoesService.MissoesService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {


    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }


    // adicionar missão (Create)
    @PostMapping("/criar")
//    public MissoesModel criarMissao(@RequestBody MissoesModel missoesModel) {
//        return missoesService.criarMissao(missoesModel);    }
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO novaMissao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Missão criada com sucesso" + novaMissao.getNome()
                + "(ID): " + novaMissao.getId());
    }

    //Mostrar todos as missoes(Read)
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarTodasAsMissoes() {
        List<MissoesDTO> missoes = missoesService.listarTodasAsMissoes();
        return ResponseEntity.ok(missoes);
    }


    //listar missao por id (Update)
    @PutMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id) {
        MissoesDTO missoes = missoesService.listarMissaoPorId(id);
        if (missoes == null) {
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //deletar Ninja(Deletar)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id) {
        if (missoesService.listarMissaoPorId(id) != null) {
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missãp com o ID " + id + "deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A missão com o id" + id + "não foi encontrada");
        }
    }


    //alterar misao  por id (Read)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesAlterada) {
    MissoesDTO missoesAlteradasMissoes = missoesService.alterarMissaoPorId(id, missoesAlterada);
    if (missoesAlteradasMissoes != null) {
        return ResponseEntity.ok(missoesAlteradasMissoes);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão id " + id + "não encontrada");
    }
    }


}