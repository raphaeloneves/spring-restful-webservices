package br.com.raphaelneves.rest.webservices.infra.resources;

import br.com.raphaelneves.rest.webservices.domain.beans.Post;
import br.com.raphaelneves.rest.webservices.domain.beans.User;
import br.com.raphaelneves.rest.webservices.domain.services.UserService;
import br.com.raphaelneves.rest.webservices.infra.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService service;

    @Autowired
    public UserResource(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public Resource<User> findOneById(@PathVariable Long id){
        User foundUser = service.findOne(id);
        if(Objects.isNull(foundUser))
            throw new NotFoundException("id: " + id);

        Resource<User> resource = new Resource<>(foundUser);
        ControllerLinkBuilder linkToUserPosts = linkTo(methodOn(this.getClass()).findAllPostsByUser(id));
        resource.add(linkToUserPosts.withRel("user-posts"));
        return resource;
    }

    @GetMapping(value = "")
    public Collection findAll(){
        return service.findAll();
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> save(@Valid @RequestBody User user){
        User createdUser = service.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id){
        User deletedUser = service.deleteById(id);
        if(Objects.isNull(deletedUser))
            throw new NotFoundException("id: " + id);
    }

    @GetMapping(value = "{userId}/posts")
    public List<Post> findAllPostsByUser(@PathVariable Long userId){
        List<Post> posts = service.findPostsByUserId(userId);
        if(Objects.isNull(posts))
            throw new NotFoundException("id: " + userId);
        return posts;
    }

    @PostMapping(value = "{userId}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable Long userId, @RequestBody Post post){
        Post createdPost = service.createUserPost(userId, post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}").buildAndExpand(createdPost.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "{userId}/posts/{postId}")
    public Post findUserPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId){
        return service.findUserPostByPostId(postId);
    }


}
