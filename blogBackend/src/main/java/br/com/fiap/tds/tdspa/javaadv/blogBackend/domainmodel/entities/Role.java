package br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="SYS007_ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  @Getter
    @Setter UUID id;

    @Column(name="NAME", length = 15)
    @NotBlank( message = "O nome da Role é obrigatorio.")
    @NotNull( message = "O nome da Role é obrigatorio.")
    @Size(max=15, message="O tamanho máximo da Role é 15.")
    private @Getter @Setter String name;

    @ManyToMany(mappedBy = "roles", fetch=FetchType.LAZY)
    private @Getter @Setter Set<User> users;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
