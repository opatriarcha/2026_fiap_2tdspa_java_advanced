package br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.IdGeneratorType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SYS001_USER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotBlank(message="O ID é Obrigatório")
    private @Getter @Setter UUID id;

    @Column(name = "NAME", length = 60)
    @NotBlank(message="O Nome é obrigatório")
    @Size(max=60, message="O tamanho máximo do nome é 60 caractereres")
    private @Getter @Setter String name;

    @Column(name = "EMAIL", length = 60)
    @Size(max = 60, min=8, message = "O email deve ter entre 8 e 60 caracteres.")
    @NotBlank(message="O email é Obrigatório")
    private @Getter @Setter String email;

    @Size(max = 20, min=8, message = "O email deve ter entre 8 e 20 caracteres.")
    @NotBlank(message="O password é obrigatório")
    @Column(name = "PASSWORD", length = 20)
    private @Getter @Setter String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private @Getter @Setter Profile profile;//exatamente 1


    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter Set<Post> posts = new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="SYS008_SYSTEM_USERS_ROLES",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name = "role_id")
    )
    private @Getter @Setter Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter Set<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profile=" + profile +
                ", posts=" + posts +
                ", roles=" + roles +
                '}';
    }
}
