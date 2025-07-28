package com.example.cadastroninjas.NinjasDTO;
import com.example.cadastroninjas.MissoesModel.MissoesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private Long id;// id não precisa ser declarado no construtor porque o h2 vai fazer isso auto
    private String nome;
    private String email;
    private String img_url;
    private int idade;
    private String rank;
    private MissoesModel missoes;



}
