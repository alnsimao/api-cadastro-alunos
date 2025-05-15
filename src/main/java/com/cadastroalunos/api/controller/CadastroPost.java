package com.cadastroalunos.api.controller;


import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cadastroalunos.api.controller.DAO.Aluno;

@RestController
public class CadastroPost {
	
	@PostMapping("/cadastro")
	public String cadastro(@RequestBody Aluno aluno) {
		String nome = aluno.getNome();
		String telefone = aluno.getTelefone();
		String email = aluno.getEmail();
		LocalDate data_nascimento = aluno.getData_nascimento();
		String senha = aluno.getSenha();
		
		return "Aluno cadastrado: " + nome 
	               + ", Telefone: " + telefone
	               + ", Email: " + email
	               + ", Data de Nascimento: " + data_nascimento
	               + ", Senha: " + senha;
		
		
		
		
	}
	

	

}
