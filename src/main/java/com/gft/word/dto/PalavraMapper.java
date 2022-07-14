package com.gft.word.dto;

import com.gft.word.entities.Palavra;

public class PalavraMapper {
	
	public static Palavra fromDTO(PalavraDTO dto) {
		return new Palavra(null, dto.getPalavra());		
	}
	
	public static PalavraDTO fromEntity(Palavra palavra) {
		return new PalavraDTO(palavra.getId(), palavra.getPalavra());
	}

}
