package org.dani.query.repository;

import org.dani.query.entity.ActiveUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActiveUsersRepository extends CrudRepository<ActiveUsers, UUID> {

    List<ActiveUsers> findAll();

}
