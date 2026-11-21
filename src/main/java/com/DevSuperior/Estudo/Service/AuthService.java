package com.DevSuperior.Estudo.Service;


import com.DevSuperior.Estudo.Entity.User;
import com.DevSuperior.Estudo.Exception.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validateSelfOrAdmin(Long userId) {
        User userMe = userService.autenticated();

        if (!userMe.hasRole("ROLE_ADMIN") && !userMe.getId().equals(userId)) {
            throw new ForbiddenException("Não Autorizado");
        }

    }


}
