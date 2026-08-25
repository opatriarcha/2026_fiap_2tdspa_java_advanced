package br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="SYS003_POSTS")
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Column(name = "TITLE", length = 60)
    @NotBlank(message="O Título é obrigatório")
    @Size(max = 60, min = 1, message = "O email deve ter entre 8 e 60 caracteres.")
    private @Getter @Setter String title;

    @Column(name = "CONTENT", length = 255)
    @Size(max=255, message = "O tamanho máximo do Post deve ser 255 caractereres.")
    private @Getter @Setter String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private @Getter @Setter User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="SYS006_POSTS_TAGS",
            joinColumns = @JoinColumn(name="POST_ID"),
            inverseJoinColumns = @JoinColumn(name="TAG_ID")
    )
    private @Getter @Setter Set<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", tags=" + tags +
                '}';
    }
}
