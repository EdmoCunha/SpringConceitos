package com.example.cadastroninjas.NinjasController;
import com.example.cadastroninjas.NinjasDTO.NinjaDTO;
import com.example.cadastroninjas.NinjasModel.NinjaModel;
import com.example.cadastroninjas.NinjasService.NinjaService;
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
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ninja criado com sucesso" + novoNinja.getNome()
                + "(ID): " + novoNinja.getId());

    }


    //Listar todos os ninjas(Read)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }
//    public List<NinjaDTO> listarNinjas() {
//        return ninjaService.listarNinjas();
//    }

    //Listar ninja por id (Read)
    @GetMapping("/listar/{id}")

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
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAlterado) {
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