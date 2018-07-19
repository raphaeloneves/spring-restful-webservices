package br.com.raphaelneves.rest.webservices.infra.daos;

import br.com.raphaelneves.rest.webservices.domain.beans.Post;
import br.com.raphaelneves.rest.webservices.domain.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
}
