package com.example.cadastroninjas.MissoesRepository;
import com.example.cadastroninjas.MissoesModel.MissoesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissoesRepository extends JpaRepository<MissoesModel, Long> {
}
