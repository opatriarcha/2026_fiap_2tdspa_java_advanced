package br.com.fiap.tds.tdspa.javaadv.blogBackend.services;

import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(UUID id);

    User persist(User user);

    void delete(User user);

    void deleteById(UUID id);

    Page<User> findAll(Pageable pageable);

    boolean existsById(UUID id);

    boolean existsById(User user);

    Optional<User> partialUpdate(UUID id, Map<String, Object> updates);
}
