package br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.Profile;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import java.util.UUID;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, UUID>{
    public Optional<Profile> findByUserId( UUID userId);
    public Optional<Profile> findByUser( User user);
}