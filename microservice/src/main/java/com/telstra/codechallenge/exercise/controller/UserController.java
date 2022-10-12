package com.telstra.codechallenge.exercise.controller;


import com.telstra.codechallenge.exercise.domain.dto.UserDto.Items;
import com.telstra.codechallenge.exercise.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RestController
public class UserController {
    @Autowired
    private UserService UserService;

    @GetMapping("/users/{limit}")
    public List<Items> getUsers(@PathVariable Integer limit) {
        log.info("Inside users controller @param limit:" + limit);
        return UserService.getUsers(limit).getItems();
    }


}
