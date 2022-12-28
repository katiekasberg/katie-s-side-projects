package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransferDao transferDao;

    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean transferMoney(@RequestBody Transfer transfer){
        return transferDao.transferMoney(transfer.getFrom_user(), transfer.getTo_user(), transfer.getAmount());
    }

    @RequestMapping(path = "/transfer/view", method = RequestMethod.GET)
    public List<Transfer> viewTransfers(Principal principal){
        String currentUser = principal.getName();
        return transferDao.getTransferByUserId(currentUser);

    }

    @RequestMapping(path = "/transfer/request", method =  RequestMethod.POST)
    public boolean requestTransfer(@RequestBody Transfer transfer){
        return transferDao.requestTransfer(transfer.getFrom_user(), transfer.getTo_user(), transfer.getAmount());
    }


}
