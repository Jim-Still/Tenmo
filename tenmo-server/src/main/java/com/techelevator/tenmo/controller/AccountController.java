package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.zip.DataFormatException;

@RestController
@PreAuthorize("isAuthenticated()")

public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;

    }


// Return current balance

    @PreAuthorize("permitAll")
    @RequestMapping(path = "balance/{id}", method = RequestMethod.GET)
    public BigDecimal getAccountBalance(@PathVariable long id) {

        BigDecimal balance = accountDao.getAccountBalance(id);
        return balance;

    }

}
