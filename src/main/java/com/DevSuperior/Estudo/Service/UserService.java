package com.DevSuperior.Estudo.Service;

import com.DevSuperior.Estudo.Projection.UserDetailsProjection;
import com.DevSuperior.Estudo.Entity.Role;
import com.DevSuperior.Estudo.Entity.User;
import com.DevSuperior.Estudo.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
