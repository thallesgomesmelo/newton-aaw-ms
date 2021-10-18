package com.newton.aaw.rh.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.newton.aaw.rh.domain.entity.User;

//Foi feito esse repositorio pra interagir com o repositorio do mongoDb
public interface UserRepository extends MongoRepository<User, String>{
	Optional<User> findOneByName(String name);
}
