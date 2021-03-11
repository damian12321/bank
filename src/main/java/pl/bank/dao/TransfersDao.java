package pl.bank.dao;

import pl.bank.entity.Transfer;

import java.util.List;

public interface TransfersDao {
    public List<Transfer> getTransfers();

    public Transfer createTransfer(Transfer transfer);

    public String deleteTransfer(int id);

    public Transfer getTransfer(int id);

    public Transfer updateTransfer(Transfer transfer);
}
