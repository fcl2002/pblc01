package wealthwise.backend.services;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Variavel;
import wealthwise.backend.repositories.VariavelRepository;

@Service
public class VariavelService extends BaseService <Variavel, Long, VariavelRepository> {

}
