package com.DevSuperior.Estudo.Service;

import com.DevSuperior.Estudo.DTO.UserDTO;
import com.DevSuperior.Estudo.Entity.Role;
import com.DevSuperior.Estudo.Entity.User;
import com.DevSuperior.Estudo.Projection.UserDetailsProjection;
import com.DevSuperior.Estudo.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> projections = userRepository.searchUserAndRolesByEmail(username);

        if (projections.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User user = new User();
        user.setEmail(username);
        user.setPassword(projections.get(0).getPassword());

        for(UserDetailsProjection p : projections) {
            user.addRole(new Role(p.getRoleId(), p.getAuthority()));
        }
        return user;
    }


    protected User autenticated() {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return userRepository.findByEmail(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Email not Found");
        }

    }

    @Transactional(readOnly = true)
    public UserDTO getMe() {
        User user = autenticated();

        return new UserDTO(user);
    }
}
