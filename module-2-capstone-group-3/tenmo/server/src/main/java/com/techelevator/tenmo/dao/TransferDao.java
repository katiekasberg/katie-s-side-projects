package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    boolean transferMoney(int fromUser, int toUser, BigDecimal amount);

    List<Transfer> getTransferByUserId(String username);

    boolean requestTransfer(int fromUser, int toUser, BigDecimal amount);

    public Transfer getTransferById(int transferId);


}
