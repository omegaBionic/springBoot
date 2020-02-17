/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author omega
 */
@Component
public class UsersService {

    @Autowired
    private UserDao userDao;
    
    public List<Users> getUsers() {
        return (List<Users>) userDao.findAll();
    }

    public Users getUsersById(long id) {
        Optional<Users> optionalUser = userDao.findById(id);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("-----> Couldn't find a User with id: " + id));
    }

    public void delete(long id) {
        userDao.deleteById(id);
    }
    
    public void add(UsersDto dto) {
        userDao.save(toEntity(dto));
    }
    
    private Users toEntity(UsersDto dto) {
        Users entity = new Users();
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setDate(dto.getDate());
        entity.setPays(dto.getPays());
        entity.setVille(dto.getVille());
        entity.setCodePostal(dto.getCodePostal());
        return entity;
    }
    
    public Users put(UsersDto dto, long id) {
         Optional<Users> optionalUser = userDao.findById(id);
      return optionalUser
      .map(entity -> {
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setDate(dto.getDate());
        entity.setPays(dto.getPays());
        entity.setVille(dto.getVille());
        entity.setCodePostal(dto.getCodePostal());
        return userDao.save(entity);
      })
      .orElseGet(() -> {
        dto.setId(id);
        return userDao.save(toEntity(dto));
      });
        
    }
    
}
