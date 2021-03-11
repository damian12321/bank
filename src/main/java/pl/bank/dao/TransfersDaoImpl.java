package pl.bank.dao;

import org.springframework.stereotype.Repository;
import pl.bank.entity.Transfer;

import java.util.List;

@Repository
public class TransfersDaoImpl implements TransfersDao {
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
