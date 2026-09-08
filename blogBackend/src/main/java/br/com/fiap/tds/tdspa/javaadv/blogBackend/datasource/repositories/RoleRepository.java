package br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.Role;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID>{

}