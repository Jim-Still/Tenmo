package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component

public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Transfer getSingleTransfer(long transfer_id){
        Transfer transfer = null;
        String sql = "SELECT *, " +

        "(SELECT accounts.user_id " +
        "FROM transfers " +
        "JOIN accounts on transfers.account_from = accounts.account_id " +
        "WHERE transfers.transfer_id = ?) AS from_user_id, " +

        "(SELECT accounts.user_id " +
        "FROM transfers " +
        "JOIN accounts on transfers.account_to = accounts.account_id " +
        "WHERE transfers.transfer_id = ?) AS to_user_id, " +

        "(SELECT transfer_types.transfer_type_desc " +
        "FROM transfers " +
        "JOIN transfer_types ON transfer_types.transfer_type_id = transfers.transfer_type_id " +
        "WHERE transfers.transfer_id = ?) AS transfer_type, " +

        "(SELECT transfer_statuses.transfer_status_desc " +
        "FROM transfers " +
        "JOIN transfer_statuses ON transfer_statuses.transfer_status_id = transfers.transfer_status_id " +
        "WHERE transfers.transfer_id = ?) AS transfer_status " +

        "FROM transfers " +

        "WHERE transfer_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transfer_id, transfer_id, transfer_id, transfer_id, transfer_id);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }


    @Override
    public Transfer createTransfer( long from_user_id,long to_user_id, BigDecimal amount){

        Transfer transfer = new Transfer();

        String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)\n" +
                "VALUES (2, 2,\n" +
                "        (SELECT account_id from accounts\n" +
                "        WHERE user_id = ?),\n" +
                "        (SELECT account_id from accounts\n" +
                "        WHERE user_id = ?),\n" +
                "        ?) RETURNING transfer_id";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, from_user_id, to_user_id, amount);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }



    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();

        transfer.setTransfer_id(rowSet.getLong("transfer_id"));
        transfer.setTransfer_type_id(rowSet.getInt("transfer_type_id"));
        transfer.setTransfer_status_id(rowSet.getInt("transfer_status_id"));
        transfer.setAccount_from(rowSet.getLong("account_from"));
        transfer.setAccount_to(rowSet.getLong("account_to"));
        transfer.setAmount(rowSet.getBigDecimal("amount"));
        transfer.setUser_id_From(rowSet.getLong("from_user_id"));
        transfer.setUser_id_To(rowSet.getLong("to_user_id"));
        transfer.setTransfer_type(rowSet.getString("transfer_type"));
        transfer.setTransfer_status(rowSet.getString("transfer_status"));


        return transfer;
    }


}


