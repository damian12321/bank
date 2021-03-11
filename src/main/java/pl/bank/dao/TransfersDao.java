package pl.bank.dao;

public interface TransfersDao {
    public List<Transfer> getTransfers();
    public String createTransfer(Transfer transfer);
    public String deleteTransfer(int number);
    public Account getTransfer(int id);
    public Account updateTransfer(Transfer transfer);
}
