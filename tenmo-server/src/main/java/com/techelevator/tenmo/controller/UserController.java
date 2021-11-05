package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("IsAuthenticated()")

public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "transfer/users", method = RequestMethod.GET) //this method was not working for some reason
    public List<User> findAll(){

        List<User> listAllUsers = userDao.findAll();

        return listAllUsers;

    }


}
