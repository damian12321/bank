package pl.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bank.dao.AccountsDao;
import pl.bank.entity.Transfer;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransfersServiceImpl implements TransfersService {
    private AccountsDao accountsDao;

    @Autowired
    public TransfersServiceImpl(AccountsDao accountsDao) {
        this.accountsDao = accountsDao;
    }

    @Override
    public List<Transfer> getTransfers() {
        return null;
    }

    @Override
    public String createTransfer(Transfer transfer) {
        return null;
    }

    @Override
    public String deleteTransfer(int number) {
        return null;
    }

    @Override
    public Transfer getTransfer(int id) {
        return null;
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        return null;
    }
}
