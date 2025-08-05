package com.cadastroalunos.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import at.favre.lib.crypto.bcrypt.*;

import com.cadastroalunos.api.alunoRepository.AlunoRepository;
import com.cadastroalunos.api.controller.DAO.Aluno;

import dto.ChangePasswordDTO;
import dto.LoginDTO;

@RestController
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepo;

	@PostMapping("/alunos")
	public ResponseEntity<Aluno> cadastro(@RequestBody Aluno aluno) {
		String currentPassword = aluno.getSenha();
		String hashPassword = BCrypt.withDefaults().hashToString(12, currentPassword.toCharArray());
		aluno.setSenha(hashPassword);
		Aluno alunoSalvo = alunoRepo.save(aluno);
		return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
	Optional<Aluno> alunoLogin = alunoRepo.findByEmail(loginDTO.getEmail());
	
	
	if( alunoLogin.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);	
	} 
	
	Aluno aluno = alunoLogin.get();
	
	String hashedPassword = aluno.getSenha();
	boolean correctPassword = BCrypt.verifyer().verify(loginDTO.getSenha().toCharArray(), hashedPassword).verified;
	
	if(!correctPassword) {
		return new ResponseEntity<>("Senha incorreta",HttpStatus.UNAUTHORIZED);
	}
	
	return ResponseEntity.ok("Bem-vindo, " + aluno.getNome());
		
	}
	
	
	@PostMapping("/trocarsenha")
	public ResponseEntity<String> passwordChange(@RequestBody ChangePasswordDTO changePasswordDTO){
		Optional<Aluno> alunoNovaSenha = alunoRepo.findByEmail(changePasswordDTO.getEmail());
		if(alunoNovaSenha.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Aluno aluno = alunoNovaSenha.get();
		
		String senhaAtual = aluno.getSenha();
		boolean confirmPassword = BCrypt.verifyer().verify(changePasswordDTO.getSenhaAtual().toCharArray(), senhaAtual).verified;
		if(!confirmPassword) {
			return new ResponseEntity<>("As senhas não batem", HttpStatus.UNAUTHORIZED);
		} else {
			 String novaSenha = changePasswordDTO.getSenhaNova();
			 String hashedNovaSenha = BCrypt.withDefaults().hashToString(12, novaSenha.toCharArray());
			 
			 aluno.setSenha(hashedNovaSenha);
			 alunoRepo.save(aluno);
			 
			 return ResponseEntity.ok("A senha foi alterada com sucesso");
		}
		
		
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
		System.out.println("Aluno: " + aluno);
		if (!aluno.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
	}

	@PatchMapping("/alunos/{id}")
	public ResponseEntity<Aluno> alterarEmail(@PathVariable String id, @RequestBody Aluno aluno) {
		System.out.println("ID: " + id);
		Optional<Aluno> alunoOptional = alunoRepo.findById(Long.parseLong(id));

		if (alunoOptional.isPresent()) {
			Aluno alunoExistente = alunoOptional.get();
			alunoExistente.setEmail(aluno.getEmail());
			System.out.println("Aluno: " + alunoExistente);
			alunoRepo.save(alunoExistente);

			return ResponseEntity.ok(alunoExistente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/alunos/{id}")
	public ResponseEntity<Aluno> alterarDados(@PathVariable String id, @RequestBody Aluno aluno) {
		System.out.println("TESTE PARA VERIFICAR SE ESTÁ RODANDO O ID: " + id);
		Optional<Aluno> alunoOP = alunoRepo.findById(Long.parseLong(id));
		if (alunoOP.isPresent()) {
			Aluno alunoEx = alunoOP.get();
			alunoEx.setNome(aluno.getNome());
			alunoEx.setEmail(aluno.getEmail());
			alunoEx.setData_nascimento(aluno.getData_nascimento());
			alunoEx.setTelefone(aluno.getTelefone());

			alunoRepo.save(alunoEx);

			return ResponseEntity.ok(alunoEx);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/alunos/{id}")
	public ResponseEntity<Void> deletarAluno(@PathVariable String id) {
		Optional<Aluno> alunoOP1 = alunoRepo.findById(Long.parseLong(id));
		if (alunoOP1.isPresent()) {
			alunoRepo.delete(alunoOP1.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
