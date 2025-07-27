package com.example.cadastroninjas.NinjasRepository;
import com.example.cadastroninjas.NinjasModel.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {


}


