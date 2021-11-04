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


//    public Transfer getTransfer(long transfer_id){
//        Transfer transfer = null;
//        String sql = "SELECT park_id, park_name, date_established, area, has_camping " +
//                "FROM park " +
//                "WHERE park_id = ?";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
//        if (results.next()) {
//            park = mapRowToPark(results);
//        }
//        return park;
//    }

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
        transfer.setUser_id_From(rowSet.getLong("user_id_From"));
        transfer.setUser_id_To(rowSet.getLong("user_id_To"));


        return transfer;
    }


}


