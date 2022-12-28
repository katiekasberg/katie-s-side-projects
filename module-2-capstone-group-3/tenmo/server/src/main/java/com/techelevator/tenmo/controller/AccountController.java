package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public Account getAccountById(Principal principal) {
        int id = userDao.findIdByUsername(principal.getName());
        return accountDao.getAccountBalance(id);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<Username> getListOfUsers(Principal principal){
       String currentUser = principal.getName();
        return userDao.displayUsernames(currentUser);
    }
}
