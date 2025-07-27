package com.example.cadastroninjas.NinjasController;
import com.example.cadastroninjas.NinjasDTO.NinjaDTO;
import com.example.cadastroninjas.NinjasModel.NinjaModel;
import com.example.cadastroninjas.NinjasService.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private NinjaService ninjaService;
    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // adicionar ninja (Create)
    @PostMapping("/criar")
//    public NinjaModel criarNinja(@RequestBody NinjaModel ninja){ // pega os dados do corpo da requisição que vai ser enviada pelo usuário
//        return ninjaService.criarNinja(ninja); aqui tambem muda sai do model e vai DTO na service
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninjaDTO){ // pega os dados do corpo da requisição que vai ser enviada pelo usuário
       return ninjaService.criarNinja(ninjaDTO);
    }

    //Listar todos os ninjas(Read)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }
    //Listar ninja por id (Read)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id){ // @pathVariable é para salvar o id que o usuario passar
        return ninjaService.listarNinjasPorId(id);
    }


    //Alterar dados (Update)
    @PutMapping("/alterar/{id}")
    public NinjaModel alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaModel ninjaAlterado){
      return  ninjaService.alterarNinjaPorId(id, ninjaAlterado);

    }


    //deletar Ninja(Deletar)
     @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id){
          ninjaService.deletarNinjaPorId(id);

     }

}
