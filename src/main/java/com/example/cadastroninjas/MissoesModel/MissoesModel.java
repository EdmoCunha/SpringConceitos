package com.example.cadastroninjas.MissoesModel;
import com.example.cadastroninjas.NinjasModel.NinjaModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity// transforma uma classe em uma entidade do bd
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_cadastro_missoes")
public class MissoesModel  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dificuldade;

    // uma missão pode ter muitos ninjas
    @OneToMany(mappedBy = "missoes")
    @JsonIgnore // ignora o loop infinito da serialização que é um chamando o outro
    private List<NinjaModel> ninjas;




}
