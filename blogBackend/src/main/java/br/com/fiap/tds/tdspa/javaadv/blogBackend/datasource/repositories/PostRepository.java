package br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories;

import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.Post;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID>{

    List<Post> findByUserId( UUID userId);
    List<Post> findByUser( User user);

    Page<Post> findByUser(User user, Pageable pageable);

    //Sort.by("title")
    List<Post> findByUser( User user, Sort sort);

    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.name IN :tagNames")
    Page<Post> findByTags(@Param("tagNames")Collection<String> tagNames, Pageable pageable);

    @Query( value="SELECT p.TITLE, p.CONTENT, p.user_id FROM POSTS p WHERE p.title ILIKE %:title%", nativeQuery = true)
    List<Post> findByTitleNative(@Param("title") String title);
}