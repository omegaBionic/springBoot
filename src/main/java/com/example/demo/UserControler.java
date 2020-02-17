/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author omega
 */
@RestController
@RequestMapping("/users")
public class UserControler {
    @Autowired
    private UsersService service;
    
    @GetMapping
    public List<Users> getUsers() {
        return service.getUsers();
    }
    @PostMapping
    public void postUsers(@RequestBody UsersDto dto) {
        service.add(dto);
    }
    @GetMapping("/{id}")
    public Users getById(@PathVariable(required = true) long id) {
        return service.getUsersById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
    
    @PutMapping("/{id}")
    public void put(@RequestBody UsersDto dto, @PathVariable(required = true) long id) {
        service.put(dto, id);
    }
}
