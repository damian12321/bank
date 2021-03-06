package pl.springbank.bank.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.springbank.bank.entity.Account;
import pl.springbank.bank.exception.NoAccessException;
import pl.springbank.bank.service.AccountsService;

@Aspect
@Component
public class LoggingAspect {

    private AccountsService accountsService;

    @Autowired
    public LoggingAspect(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @Pointcut("execution(* pl.springbank.bank.service.AccountsService.getAccount(int,String))")
    public void beforeGetAccount() {

    }

    @Pointcut("execution(* pl.springbank.bank.service.AccountsService.transferMoney(int,int,int,float,String))")
    public void beforeTransfer() {

    }

    @Pointcut("execution(* pl.springbank.bank.service.AccountsService.withdrawMoney(int,int,float))")
    public void beforeWithdraw() {

    }

    @Pointcut("execution(* pl.springbank.bank.service.AccountsService.depositMoney(int,int,float))")
    public void beforeDeposit() {

    }

    @Pointcut("execution(* pl.springbank.bank.service.AccountsService.getAccountsTransactions(int,String))")
    public void beforeGetAccountsTransactions() {

    }

    @Pointcut("execution(* pl.springbank.bank.service.AccountsService.changePassword(int,String,String))")
    public void beforeChangePassword() {

    }

    @Pointcut("execution(* pl.springbank.bank.service.AccountsService.changePinNumber(int,int,int))")
    public void beforeChangePinNumber() {

    }

    @Around("beforeTransfer()||beforeWithdraw()||beforeDeposit()||beforeChangePinNumber()")
    public Object pinNotCorrect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (NoAccessException e) {
            Object[] objects = proceedingJoinPoint.getArgs();
            int accountNumber = (int) objects[0];
            Account account = accountsService.getAccountByAccountNumber(accountNumber);
            return decreaseLoginAttempts(e, account);
        }
        return result;
    }

    @Around("beforeGetAccount()||beforeGetAccountsTransactions()||beforeChangePassword()")
    public Object passwordNotCorrect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (NoAccessException e) {
            Object[] objects = proceedingJoinPoint.getArgs();
            int accountId = (int) objects[0];
            Account account = accountsService.getAccountByAccountId(accountId);
            decreaseLoginAttempts(e, account);
        }
        return result;
    }

    private Object decreaseLoginAttempts(NoAccessException e, Account account) {
        int loginAttempts = account.getLoginAttempts();
        if (loginAttempts > 0) {
            account.setLoginAttempts(--loginAttempts);
            if (loginAttempts == 0) {
                System.out.println("wtf");
                account.setIsActive(false);
            }
            accountsService.saveOrUpdateAccount(account);
        }
        throw new NoAccessException(e.getMessage());
    }

}
