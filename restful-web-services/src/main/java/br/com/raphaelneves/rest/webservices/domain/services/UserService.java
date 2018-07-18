package br.com.raphaelneves.rest.webservices.domain.services;

import br.com.raphaelneves.rest.webservices.domain.beans.User;
import br.com.raphaelneves.rest.webservices.infra.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO dao;

    @Autowired
    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public User findOne(Long id){
        return dao.findOne(id);
    }

    public User deleteById(Long id){
        User user = dao.findOne(id);
        return delete(user);
    }
    public User delete(User user){
        return dao.delete(user);
    }

    public List<User> findAll(){
        return dao.findAll();
    }

    public User save(User user){
        return dao.save(user);
    }


}
