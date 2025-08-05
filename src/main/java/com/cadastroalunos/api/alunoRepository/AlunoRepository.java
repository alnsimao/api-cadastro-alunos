package com.cadastroalunos.api.alunoRepository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastroalunos.api.controller.DAO.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
Optional <Aluno> findByEmail(String email);
}
