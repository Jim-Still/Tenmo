package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

//    public JdbcAccountDao(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long getAccountBalance(long account_id){

        Account account = null;
        long balance = 0;

        String sql = "SELECT account_id, user_id, balance " +
                     "FROM accounts " +
                     "WHERE account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, account_id);
        if (results.next()) {
            account = mapRowToAccount(results);
        }
        return account.getBalance()
                ;
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();

        account.setAccount_id(rowSet.getLong("account_id"));
        account.setUser_id(rowSet.getLong("user_id"));
        account.setBalance((long) (rowSet.getDouble("balance") * 100));

        return account;
    }

}
