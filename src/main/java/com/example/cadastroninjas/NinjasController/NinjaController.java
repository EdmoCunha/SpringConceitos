package com.example.cadastroninjas.NinjasController;
import com.example.cadastroninjas.NinjasDTO.NinjaDTO;
import com.example.cadastroninjas.NinjasModel.NinjaModel;
import com.example.cadastroninjas.NinjasService.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // adicionar ninja (Create)

//    public NinjaModel criarNinja(@RequestBody NinjaModel ninja){ //TODO aqui eu criei com a model, pega os dados do corpo da requisição que vai ser enviada pelo usuário
//        return ninjaService.criarNinja(ninja); aqui tambem muda sai do model e vai DTO na service

    //    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninjaDTO){ // TODO: AQUI EU TO PASSANDO A DTO DIRETo, pega os dados do corpo da requisição que vai ser enviada pelo usuário
//       return ninjaService.criarNinja(ninjaDTO);
//    }
    @PostMapping("/criar")
    @Operation(summary = "Cria uma ninja ", description = "Rota cria um ninja e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do Ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ninja criado com sucesso" + novoNinja.getNome()
                + "(ID): " + novoNinja.getId());

    }


    //Listar todos os ninjas(Read)
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas ", description = "Rota lista todos os ninjas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas listados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na listagem do ninja")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }
//    public List<NinjaDTO> listarNinjas() {
//        return ninjaService.listarNinjas();
//    }

    //Listar ninja por id (Read)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista ninja pelo seu Id ", description = "Rota lista um ninja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão")
    })

    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) { // @pathVariable é para salvar o id que o usuario passar
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);// aqui no "?" estou passando como generic porque string não deixava eu voltar o json
        if(ninja != null){
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
//    public NinjaDTO listarNinjasPorId(@PathVariable Long id) { // @pathVariable é para salvar o id que o usuario passar
//        return ninjaService.listarNinjasPorId(id);
//    }


    //Alterar dados (Update)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Cria uma missão ", description = "Rota cria uma missão e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não alterado")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuário manda o ID no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados no corpo da requisição")
            @RequestBody NinjaDTO ninjaAlterado) {
        NinjaDTO ninjaAlteradoNinja = ninjaService.alterarNinjaPorId(id, ninjaAlterado);
        if(ninjaAlteradoNinja != null) {
            return ResponseEntity.ok(ninjaAlteradoNinja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja id " + id + "não encontrado");



        }


    }
//    public NinjaDTO alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAlterado) {
//        return ninjaService.alterarNinjaPorId(id, ninjaAlterado);
//
//    }


    //deletar Ninja(Deletar)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninka com o id " + id +"não encontrado");
        }

//     public void deletarNinjaPorId(@PathVariable Long id){
//         ninjaService.deletarNinjaPorId(id);}

    }
}