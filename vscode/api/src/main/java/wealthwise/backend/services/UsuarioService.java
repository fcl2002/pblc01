package wealthwise.backend.services;


import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Usuario;
import wealthwise.backend.repositories.UsuarioRepository;

@Service
public class UsuarioService extends BaseService <Usuario, String, UsuarioRepository> {

}
