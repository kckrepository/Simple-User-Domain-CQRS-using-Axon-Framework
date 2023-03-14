package org.dani.query.repository;

import org.dani.query.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsersRepository extends CrudRepository<Users, UUID> {

    List<Users> findAll();

}
