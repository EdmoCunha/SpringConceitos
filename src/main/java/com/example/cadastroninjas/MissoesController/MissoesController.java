package com.example.cadastroninjas.MissoesController;
import com.example.cadastroninjas.MissoesDTO.MissoesDTO;
import com.example.cadastroninjas.MissoesService.MissoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria uma missão ", description = "Rota cria uma missão e insere no banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na criação da missão")
    })
//    public MissoesModel criarMissao(@RequestBody MissoesModel missoesModel) {
//        return missoesService.criarMissao(missoesModel);    }
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO novaMissao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Missão criada com sucesso" + novaMissao.getNome()
                + "(ID): " + novaMissao.getId());
    }



    //Mostrar todos as missoes(Read)
    @GetMapping("/listar")
    @Operation(summary = "Lista todas as Missões ", description = "Rota lista todas missões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão Listada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na listagem das missões")})

    public ResponseEntity<List<MissoesDTO>> listarTodasAsMissoes() {
        List<MissoesDTO> missoes = missoesService.listarTodasAsMissoes();
        return ResponseEntity.ok(missoes);
    }


    //listar missao por id (Update)
    @PutMapping("/listar/{id}")
    @Operation(summary = "Lista a missão pelo Id ", description = "Rota lista missão pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missão não encontrada, verifique os parâmetros e tente novamente")
    })
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
    @Operation(summary = "Deleta Missão ", description = "Rota deleta missão pelo caminho do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro missão não deletada")})
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
    @Operation(summary = "Altera missão Id ", description = "Rota altera missão pelo id e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro missão não foi alterada")
    })
    public ResponseEntity<?> alterarMissao(
            @Parameter(description = "Usuário manda o id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados no corpo da requisição")
            @RequestBody MissoesDTO missoesAlterada) {
    MissoesDTO missoesAlteradasMissoes = missoesService.alterarMissaoPorId(id, missoesAlterada);
    if (missoesAlteradasMissoes != null) {
        return ResponseEntity.ok(missoesAlteradasMissoes);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão id " + id + "não encontrada");
    }
    }


}