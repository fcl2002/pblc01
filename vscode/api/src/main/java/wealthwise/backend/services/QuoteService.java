package wealthwise.backend.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Quote;
import wealthwise.backend.domain.VariableIncome;
import wealthwise.backend.dtos.quote.QuoteRequestDTO;
import wealthwise.backend.dtos.quote.QuoteResponseDTO;
import wealthwise.backend.exceptions.IllegalFieldException;
import wealthwise.backend.exceptions.ResourceNotFoundException;
import wealthwise.backend.repositories.QuoteRepository;
import wealthwise.backend.repositories.VariableIncomeRepository;

@Service
public class QuoteService {
    
    @Autowired
    private QuoteRepository repository;

    @Autowired
    private VariableIncomeRepository variableRepository;

    @Autowired
    private VariableIncomeService variableService;

    public Quote getQuoteById(String id) {
        
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote with id '" + id + "' not found."));
    }

    public Quote getQuoteByDate(Date date) {
        
        return repository.findByDate(date)
                .orElseThrow(() -> new ResourceNotFoundException("Quote with date '" + date + "' not found."));
    }

    public QuoteResponseDTO createQuote(QuoteRequestDTO request) {
    
        if (request.assetId() != null && variableRepository.existsById(request.assetId()))
            throw new IllegalFieldException("Variable Income 'id'", "is not valid.");
      
        if (request.value() != null || request.value() < 0.0)
            throw new IllegalFieldException("Quote 'value'", "is not valid.");
        
        VariableIncome variable = variableService.getVariableById(request.assetId());

        Quote quote = new Quote();
        quote.setValue(request.value());
        quote.setDate(request.date());
        quote.setAsset(variable);

        List<Quote> quotes = variable.getQuotes();
        quotes.add(quote);
        variable.setQuotes(quotes);

        QuoteResponseDTO quoteDTO = new QuoteResponseDTO(repository.save(quote));

        return quoteDTO;
    }

    public void deleteQuote(String id) {
        VariableIncome variable = variableService.getVariableById(getQuoteById(id).getAsset().getId());

        if(Objects.isNull(variable))
            throw new ResourceNotFoundException("Variable Income with id '" + id + "' not found.");
        

        List<Quote> quotes = variable.getQuotes();
        for(Iterator<Quote> it = quotes.iterator(); it.hasNext();) {
            Quote quote = it.next();
            if(quote.getId() == id){
                quote.setAsset(null);
                it.remove();
                    break;
            }
        }
        repository.deleteById(id);
    }
}
