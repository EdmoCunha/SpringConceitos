package com.example.cadastroninjas.NinjasModel;
import com.example.cadastroninjas.MissoesModel.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // transforma uma classe em uma entidade do bd
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_cadastro")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
   private Long id;// id não precisa ser declarado no construtor porque o h2 vai fazer isso auto

   @Column(name = "nome")
   private String nome;

   @Column(unique = true)
   private String email;

   @Column(name = "img_url")
   private String img_url;

   @Column(name = "idade")
   private int idade;

   @Column (name = "rank")
   private String rank;


   // muitos ninjas tem uma missão
   @ManyToOne
   @JoinColumn(name = "missoes_id")// foreign key a famosa chave estrangeira
   private MissoesModel missoes;



}
