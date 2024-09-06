package com.TintaTotal.Login.Repository;


import com.TintaTotal.Login.Modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsuario(String username);
}
