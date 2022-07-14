package com.gft.word.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.word.entities.Palavra;

@Repository
public interface PalavraRepository extends JpaRepository<Palavra, Long>{

	Page<Palavra> findAll(Pageable pageable);
}
