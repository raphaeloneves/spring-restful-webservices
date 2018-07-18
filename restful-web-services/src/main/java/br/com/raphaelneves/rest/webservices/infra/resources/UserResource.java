package br.com.raphaelneves.rest.webservices.infra.resources;

import br.com.raphaelneves.rest.webservices.domain.beans.User;
import br.com.raphaelneves.rest.webservices.domain.services.UserService;
import br.com.raphaelneves.rest.webservices.infra.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final UserService service;

    @Autowired
    public UserResource(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public User findOneById(@PathVariable Long id){
        User foundUser = service.findOne(id);
        if(Objects.isNull(foundUser))
            throw new NotFoundException("id: " + id);
        return foundUser;
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
}