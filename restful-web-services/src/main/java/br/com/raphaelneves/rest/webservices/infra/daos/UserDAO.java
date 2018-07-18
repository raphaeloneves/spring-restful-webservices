package br.com.raphaelneves.rest.webservices.infra.daos;

import br.com.raphaelneves.rest.webservices.domain.beans.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class UserDAO {
    private static List<User> users = new ArrayList<>();
    private static Long userCount = 4L;

    static {
        users.add(new User(1L, "Adam", new Date()));
        users.add(new User(2L, "John", new Date()));
        users.add(new User(3L, "Martha", new Date()));
        users.add(new User(4L, "Jack", new Date()));
    }

    public User findOne(Long id){
        return users.stream().filter(user -> id.equals(user.getId())).findFirst().orElse(null);
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(Objects.isNull(user.getId()))
            user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User delete(User user) {
        users.remove(user);
        return user;
    }
}
