package wealthwise.backend.services;


import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Carteira;
import wealthwise.backend.repositories.CarteiraRepository;

@Service
public class CarteiraService extends BaseService<Carteira, Long, CarteiraRepository> {

}
