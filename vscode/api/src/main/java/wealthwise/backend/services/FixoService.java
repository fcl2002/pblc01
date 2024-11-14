package wealthwise.backend.services;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Fixo;
import wealthwise.backend.repositories.FixoRepository;

@Service
public class FixoService extends BaseService <Fixo, Long, FixoRepository> {
}