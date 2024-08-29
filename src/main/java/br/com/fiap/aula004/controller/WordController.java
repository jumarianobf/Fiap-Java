package br.com.fiap.aula004.controller;


import br.com.fiap.aula004.entity.Word;
import br.com.fiap.aula004.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping
    public ResponseEntity<Page<Word>> searchWords(
            @RequestParam(value = "ptWord", required = false, defaultValue = "") String ptWord,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Word> result = wordService.searchWords(ptWord, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        Word savedWord = wordService.saveWord(word);
        return new ResponseEntity<>(savedWord, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable Long id, @RequestBody Word word) {
        Word updatedWord = wordService.updateWord(id, word);
        return new ResponseEntity<>(updatedWord, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/dummy/{qty}")
    public ResponseEntity<List<Word>> createDummyWords(@PathVariable int qty) {
        List<Word> dummyWords = wordService.createDummyWords(qty);
        return new ResponseEntity<>(dummyWords, HttpStatus.CREATED);
    }
}