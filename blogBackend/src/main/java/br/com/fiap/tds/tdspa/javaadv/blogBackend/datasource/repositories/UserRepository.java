package br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends
        JpaRepository<User, UUID>,
        QuerydslPredicateExecutor<User>,
        UserRepositoryCustom{

    @Query("SELECT DISTINCT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findByRole(String roleName);


}