package br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{

    @Query("SELECT DISTINCT u FROM User u WHERE u.role = :role")
    List<User> findByRole(String role);
}