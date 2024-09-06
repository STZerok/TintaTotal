package com.TintaTotal.Login.Service;

import com.TintaTotal.Login.Modelo.User;
import com.TintaTotal.Login.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        createDefaultUsers();
    }

    private void createDefaultUsers() {
        User user1 = new User();
        user1.setUsuario("user1");
        user1.setPassword(passwordEncoder.encode("password1"));
        user1.setRol("USER");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsuario("admin");
        user2.setPassword(passwordEncoder.encode("adminpass"));
        user2.setRol("ADMIN");
        userRepository.save(user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsuario(username);  // Buscar usuario por el campo "usuario"
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsuario())
                .password(user.getPassword())
                .roles(user.getRol())  // Roles deben ser separados por comas
                .build();
    }

    public User saveUser(User user) {
        // Codificar la contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
