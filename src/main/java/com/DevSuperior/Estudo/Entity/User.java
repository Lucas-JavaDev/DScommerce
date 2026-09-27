package com.DevSuperior.Estudo.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    private String name;

    @Column(unique = true) // Columa unica, não permite repetição
    private String email;

    private String phone;
    private LocalDate birthDate;
    private String password;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "tb_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean hasHole(String roleName) {
        for(Role role : roles) {
            if(role.getAuthority().equals(roleName)) {
                return true;
            }

        }
        return false;
    }
}
