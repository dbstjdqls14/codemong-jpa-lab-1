package com.codemong.jpa.lab.repository;

import com.codemong.jpa.lab.domain.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = {"member", "comments", "comments.member"})
    Optional<Post> findDetailById(Long id);

    @Query(
            value = "select distinct p from Post p join fetch p.member left join fetch p.comments c order by p.createdAt desc",
            countQuery = "select count(p) from Post p"
    )
    Page<Post> findAllWithMemberAndComments(Pageable pageable);
}
