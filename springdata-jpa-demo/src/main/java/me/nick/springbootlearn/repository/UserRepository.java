package me.nick.springbootlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nick.springbootlearn.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
}