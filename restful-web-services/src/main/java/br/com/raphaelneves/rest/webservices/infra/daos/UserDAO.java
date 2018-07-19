package br.com.raphaelneves.rest.webservices.infra.daos;

import br.com.raphaelneves.rest.webservices.domain.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
}
