package com.cadastroalunos.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cadastroalunos.api.alunoRepository.AlunoRepository;
import com.cadastroalunos.api.controller.DAO.Aluno;



@RestController
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepo;

	@PostMapping("/alunos")
	public ResponseEntity<Aluno> cadastro(@RequestBody Aluno aluno) {
		Aluno alunoSalvo = alunoRepo.save(aluno);
		return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);

	}

	@GetMapping("/alunos")
	public List<Aluno> listaDeAluno() {
		List<Aluno> alunos = alunoRepo.findAll();
		return alunos;
	}
	
	@GetMapping("/alunos/{id}")
	public ResponseEntity<Aluno> obterAluno(@PathVariable String id) {
		System.out.println("ID: " + id);	
		 Optional<Aluno> aluno = alunoRepo.findById(Long.parseLong(id));
		 System.out.println("Aluno: "+ aluno);
		 if (!aluno.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 } 
		 return new ResponseEntity<> (aluno.get(), HttpStatus.OK);
		
	
		
	}
	

}
