package com.zemoso.user.controller;

import com.zemoso.user.valueobjects.ResponseTemplateVO;
import com.zemoso.user.entity.Users;
import com.zemoso.user.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/")
    public Users saveUsers(@RequestBody Users users) {
        log.info("Inside saveUser method of UserController");
        return usersService.saveUsers(users);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(
            @PathVariable("id") Long usersId) {
        log.info("Inside getUserWithDepartment method of UserController");
        return usersService.getUserWithDepartment(usersId);
    }
}
