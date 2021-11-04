package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated()")

public class TransferController {

    private TransferDao transferDao;

    public TransferController (TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    // Create new transfer

    @PreAuthorize("permitAll")
    @RequestMapping(path = "transfer", method = RequestMethod.POST)//make a user not found exception
    public Transfer createTransfer(@RequestBody Transfer transfer) {

        return transferDao.createTransfer(transfer.getUser_id_From(),transfer.getUser_id_To(),transfer.getAmount());


    }

}
