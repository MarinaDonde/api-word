package com.gft.word.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.word.dto.PalavraDTO;
import com.gft.word.dto.PalavraMapper;
import com.gft.word.entities.Palavra;
import com.gft.word.services.PalavraService;

@RestController
@RequestMapping("v1/palavras")
public class PalavraController {
	
	private final PalavraService palavraService;
	
	public PalavraController(PalavraService palavraService) {
		this.palavraService = palavraService;
	}
	
	@GetMapping
	public ResponseEntity<Page<PalavraDTO>> buscarTodosAsPalavras(@PageableDefault Pageable pageable) {
		
		return ResponseEntity.ok(palavraService.listarTodosAsPalavras(pageable).map(PalavraMapper::fromEntity));
	}
	
	@PostMapping
	public ResponseEntity<PalavraDTO> salvarPalavra(@RequestBody PalavraDTO dto) {
		
		Palavra palavra = palavraService.salvarPalavra(PalavraMapper.fromDTO(dto));
		
		return ResponseEntity.ok(PalavraMapper.fromEntity(palavra));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PalavraDTO> buscarPalavra(@PathVariable Long id) {
		
		Palavra palavra = palavraService.buscarPalavra(id);
		
		return ResponseEntity.ok(PalavraMapper.fromEntity(palavra));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<PalavraDTO> alterarPalavra(@RequestBody PalavraDTO dto, @PathVariable Long id) {
		
		try {
			Palavra palavra = palavraService.atualizarPalavra(PalavraMapper.fromDTO(dto), id);
			
			return ResponseEntity.ok(PalavraMapper.fromEntity(palavra));
			
		} catch (RuntimeException e) {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<PalavraDTO> excluirPalavra(@PathVariable Long id) {
		
		try {
			palavraService.excluirPalavra(id);
			
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
