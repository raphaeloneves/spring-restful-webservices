package br.com.raphaelneves.rest.webservices.domain.services;

import br.com.raphaelneves.rest.webservices.domain.beans.Post;
import br.com.raphaelneves.rest.webservices.domain.beans.User;
import br.com.raphaelneves.rest.webservices.infra.daos.PostDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostDao dao;

    public PostService(PostDao dao) {
        this.dao = dao;
    }

    public List<Post> findAllByUser(User user){
        return dao.findAllByUser(user);
    }

    public Post findOne(Long id){
        return dao.findOne(id);
    }

    public Post save(Post post) {
        return dao.save(post);
    }
}
