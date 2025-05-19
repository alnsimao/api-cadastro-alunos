package com.cadastroalunos.api.alunoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastroalunos.api.controller.DAO.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	

}
