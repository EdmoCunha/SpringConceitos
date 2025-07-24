package com.example.cadastroninjas.MissoesController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {


    //Mostrar todos as missoes(Read)
    @GetMapping("/todos")
    public String mostrarTodasAsMissoes(){
        return "Mostrar todas as missões";
    }


    // adicionar missão (Create)
    @PostMapping("/criar")
    public String criarMissao(){
        return "Criando missão";
    }

    //Alterar dados (Update)
    @PutMapping("/alterarId")
    public String alterarMissaoPorId(){
        return "Miss]ao alterada por id";}

    //deletar Ninja(Deletar)
    @DeleteMapping("/deletarId")
    public String deletarMissaoPorId(){
        return "Deletando missão por id";
    }





    //Mostrar ninja por id (Read)
    @GetMapping("/todosId")
    public String MostrarMissaoPorId(){
        return "Mostrar missão pelo id";
    }







}
