package br.com.raphaelneves.rest.webservices.domain.services;

import br.com.raphaelneves.rest.webservices.domain.beans.Post;
import br.com.raphaelneves.rest.webservices.domain.beans.User;
import br.com.raphaelneves.rest.webservices.infra.daos.UserDAO;
import br.com.raphaelneves.rest.webservices.infra.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserDAO dao;
    private final PostService postService;

    @Autowired
    public UserService(UserDAO dao, PostService postService) {
        this.dao = dao;
        this.postService = postService;
    }

    public User findOne(Long id){
        return dao.findOne(id);
    }

    public User deleteById(Long id){
        User user = dao.findOne(id);
        return delete(user);
    }
    public User delete(User user){
        dao.delete(user);
        return user;
    }

    public List<User> findAll(){
        return dao.findAll();
    }

    public User save(User user){
        return dao.save(user);
    }

    public List<Post> findPostsByUserId(Long userId){
        User user = findOne(userId);
        if(Objects.isNull(user))
            throw new NotFoundException("id: " + userId);
        return postService.findAllByUser(user);
    }

    public Post createUserPost(Long userId, Post post){
        User user = findOne(userId);
        if(Objects.isNull(user))
            throw new NotFoundException("id: " + userId);

        post.setUser(user);
        return postService.save(post);
    }

    public Post findUserPostByPostId(Long postId) {
        Post post = postService.findOne(postId);
        if(Objects.isNull(post))
            throw new NotFoundException("post id: " + postId);
        return post;
    }
}
