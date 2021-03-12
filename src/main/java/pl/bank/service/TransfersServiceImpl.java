package pl.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bank.dao.TransfersDao;
import pl.bank.entity.Transfer;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransfersServiceImpl implements TransfersService {
    private TransfersDao transfersDao;

    @Autowired
    public TransfersServiceImpl(TransfersDao transfersDao) {
        this.transfersDao = transfersDao;
    }

    @Override
    public List<Transfer> getTransfers() {
        return transfersDao.getTransfers();
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        return transfersDao.createTransfer(transfer);
    }

    @Override
    public String deleteTransfer(int id) {
        return transfersDao.deleteTransfer(id);
    }

    @Override
    public Transfer getTransfer(int id) {
        return transfersDao.getTransfer(id);
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        return transfersDao.updateTransfer(transfer);
    }
}
