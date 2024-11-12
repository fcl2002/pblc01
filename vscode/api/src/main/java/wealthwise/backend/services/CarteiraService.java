package wealthwise.backend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Carteira;
import wealthwise.backend.repositories.CarteiraRepository;

@Service
public class CarteiraService extends BaseService<Carteira, UUID, CarteiraRepository> {
    public CarteiraService(CarteiraRepository repository) {
        super(repository);
    }
}
