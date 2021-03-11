package pl.bank.service;

import pl.bank.entity.Transfer;

import java.util.List;

public interface TransfersService {
    public List<Transfer> getTransfers();

    public Transfer createTransfer(Transfer transfer);

    public String deleteTransfer(int number);

    public Transfer getTransfer(int id);

    public Transfer updateTransfer(Transfer transfer);
}
