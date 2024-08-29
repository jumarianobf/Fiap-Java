package br.com.fiap.aula004.service;

import br.com.fiap.aula004.entity.Word;
import br.com.fiap.aula004.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository wordRepository;

    @Override
    public Page<Word> searchWords(String ptWord, int page, int size) {
        return wordRepository.findByPtWordContaining(ptWord, PageRequest.of(page, size));
    }

    @Override
    public Word saveWord(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public Word updateWord(Long id, Word word) {
        Word existingWord = wordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Word not found"));
        existingWord.setPtWord(word.getPtWord());
        existingWord.setEnWord(word.getEnWord());
        existingWord.setPtMeaning(word.getPtMeaning());
        return wordRepository.save(existingWord);
    }

    @Override
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public List<Word> createDummyWords(int qty) {
        List<Word> dummyWords = new ArrayList<>();
        for (int i = 0; i < qty; i++) {
            Word word = new Word();
            word.setPtWord("Palavra" + i);
            word.setEnWord("Word" + i);
            word.setPtMeaning("Significado " + i);
            dummyWords.add(wordRepository.save(word));
        }
        return dummyWords;
    }
}