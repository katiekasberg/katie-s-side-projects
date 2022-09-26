package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean transferMoney(int fromUser, int toUser, BigDecimal amount) {
        Transfer transfer = new Transfer();
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, fromUser);
        BigDecimal balance = new BigDecimal("0.00");
        // if (status.equalsIgnoreCase("approved "))
        if (results.next()) {
            balance = results.getBigDecimal("balance");
        }
        if (balance.compareTo(new BigDecimal("0.00")) == 0) {
            return false;
        }
        if (balance.compareTo(amount) == 0 || balance.compareTo(amount) == 1) {
            //subtract amount from balance from the from_user account
            String subtractSql = "UPDATE account SET balance = balance - ? " + "WHERE user_id = ?;";

            //add the amount to balance for the to_user account
            String addSql = "UPDATE account SET balance = balance + ?" + "WHERE user_id = ?;";

            //insert into transfer table, a transfer object
            String insertSql = "INSERT INTO transfer (from_user_id, to_user_id, amount) " +
                    "VALUES(?, ?, ?) RETURNING transfer_id;";
            Integer newId;

            try {
                jdbcTemplate.update(subtractSql, amount, fromUser);
                jdbcTemplate.update(addSql, amount, toUser);
                newId = jdbcTemplate.queryForObject(insertSql, Integer.class, fromUser, toUser, amount);

            } catch (DataAccessException e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public List<Transfer> getTransferByUserId(String username) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, amount, from_user_id, to_user_id, status " +
                "FROM transfer " +
                "JOIN tenmo_user as tu ON transfer.from_user_id = tu.user_id " +
                "JOIN tenmo_user as te ON transfer.to_user_id = te.user_id " +
                "WHERE te.username = ? OR tu.username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, username);

        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(int transferId) {
        Transfer transfer = new Transfer();
        String sql = "SELECT amount, from_user_id, to_user_id, status " +
                "FROM transfer " +
                "WHERE transfer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);

        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    @Override
    public boolean requestTransfer(int fromUser, int toUser, BigDecimal amount) {
        String status = "Pending";
        String sql = "INSERT INTO transfer (amount, from_user_id, to_user_id, status) " +
                "VALUES (?, ?, ?, ?) RETURNING transfer_id;";

        Integer newId;
        try {
            newId = jdbcTemplate.queryForObject(sql, Integer.class, amount, fromUser, toUser, status);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    /*@Override
    public boolean approveTransfer(int transferId, String status){
        Transfer transfer = new Transfer();
        String sql = "SELECT from_user_id, to_user_id, amount, status FROM transfer WHERE transfer_id = ?";
            //jdbcTemplate.queryForRowset(sql, transferId);
            //getTransferById(transferId);

           // int fromUser = transfer.getFrom_user(result.getInt("from_user_id"));
           // int toUser = transfer.setFrom_user();
           // BigDecimal amount = new BigDecimal();
          // jdbcTemplate.queryForObject(sql, Integer.class, fromUser, toUser, amount);

            if (status.equalsIgnoreCase("approved")) {
               // transferMoney(fromUser, toUser, amount);
                String sql2 = "UPDATE transfer SET status = ? WHERE transfer_id = ?";
                jdbcTemplate.update(sql, status);
                return true;
            } else {
                String sql3 = "UPDATE transfer SET status = ? WHERE transfer_id = ?";
                status = "Rejected";
                jdbcTemplate.update(sql3, status);
                return false;
*/




private Transfer mapRowToTransfer(SqlRowSet result){
        Transfer transfer=new Transfer();
        transfer.setAmount(result.getBigDecimal("amount"));
        transfer.setFrom_user(result.getInt("from_user_id"));
        transfer.setTo_user(result.getInt("to_user_id"));
        transfer.setTransfer_id(result.getInt("transfer_id"));
        transfer.setStatus(result.getString("status"));
        return transfer;
        }
        }
