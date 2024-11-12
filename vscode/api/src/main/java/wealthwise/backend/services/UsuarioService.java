package wealthwise.backend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Usuario;
import wealthwise.backend.repositories.UsuarioRepository;

@Service
public class UsuarioService extends BaseService <Usuario, UUID, UsuarioRepository> {

}
