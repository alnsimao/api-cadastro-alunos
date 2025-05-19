package com.cadastroalunos.api.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cadastroalunos.api.alunoRepository.AlunoRepository;
import com.cadastroalunos.api.controller.DAO.Aluno;

@RestController
public class CadastroPost {
	
	
	@Autowired
	private AlunoRepository alunoRepo;
	
	@PostMapping("/cadastro")
	public ResponseEntity<Aluno> cadastro(@RequestBody Aluno aluno) {
		Aluno alunoSalvo = alunoRepo.save(aluno);
		System.out.println("ID gerado (no servidor): " + alunoSalvo.getId());
		return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
	
		
	}
	

	

}
