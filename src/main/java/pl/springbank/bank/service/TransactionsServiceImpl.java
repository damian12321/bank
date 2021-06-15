package pl.springbank.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.springbank.bank.dao.TransactionsDao;
import pl.springbank.bank.entity.Transaction;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class TransactionsServiceImpl implements TransactionsService {
    private TransactionsDao transactionsDao;

    @Autowired
    public TransactionsServiceImpl(TransactionsDao transactionsDao) {
        this.transactionsDao = transactionsDao;
    }

    @Override
    @Transactional
    public List<Transaction> getTransactions() {
        return transactionsDao.getTransactions();
    }

    @Override
    @Transactional
    public String deleteTransaction(int id) {
        return transactionsDao.deleteTransaction(id);
    }

    @Override
    @Transactional
    public Transaction getTransaction(int id) {
        return transactionsDao.getTransaction(id);
    }

    @Override
    @Transactional
    public Transaction updateTransaction(Transaction transaction) {
        return transactionsDao.updateTransaction(transaction);
    }

}
