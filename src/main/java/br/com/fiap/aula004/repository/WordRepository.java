package br.com.fiap.aula004.repository;

import br.com.fiap.aula004.entity.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
    Page<Word> findByPtWordContaining(String ptWord, Pageable pageable);
}