package br.com.ivanilsonjr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@Value("${application.version}")
	private String projectVersion;

	@GetMapping
	public ResponseEntity<String> retorna200(){
		return ResponseEntity.ok("Versão: " + projectVersion);
		
	}
}
