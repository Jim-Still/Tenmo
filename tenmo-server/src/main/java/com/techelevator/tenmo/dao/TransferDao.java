package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

Transfer createTransfer(long userTo_id, long userFrom_id, BigDecimal amount);

Transfer getSingleTransfer(long transfer_id);

//List <Transfer> getAllTransfers(long user_id);

//void requestMoney(int userTo_id, int userFrom_id, BigDecimal amount);

}
