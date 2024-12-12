package wealthwise.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import wealthwise.backend.domain.Quote;
import wealthwise.backend.dtos.quote.QuoteRequestDTO;
import wealthwise.backend.dtos.quote.QuoteResponseDTO;
import wealthwise.backend.repositories.QuoteRepository;
import wealthwise.backend.services.QuoteService;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteRepository repository;

    @Autowired
    private QuoteService service;

    @GetMapping
    public ResponseEntity<List<QuoteResponseDTO>> getAll() {
        
        List<QuoteResponseDTO> quotes = this.repository.findAll().stream().map(QuoteResponseDTO::new).toList();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponseDTO> getId(@PathVariable String id) {
        
        Quote quote = service.getQuoteById(id);
        QuoteResponseDTO quoteDTO = new QuoteResponseDTO(quote);
        return ResponseEntity.ok(quoteDTO);
    }

    @PostMapping
    public ResponseEntity<QuoteResponseDTO> postUser(@RequestBody @Valid QuoteRequestDTO request) {
        
        QuoteResponseDTO quoteDTO = service.createQuote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(quoteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuote(@PathVariable String id) {
        
        service.deleteQuote(id);
        return ResponseEntity.ok("Quote deleted with id '" + id + "'");
    }
}
