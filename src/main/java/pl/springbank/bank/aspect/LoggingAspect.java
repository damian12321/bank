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
    @Autowired
    AccountsService accountsService;

    @Pointcut("execution(* pl.springbank.bank.service.AccountsService.getAccount(int,int))")
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

    @Around("beforeGetAccount()||beforeTransfer()||beforeWithdraw()||beforeDeposit()")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (NoAccessException e) {

            Object[] objects = proceedingJoinPoint.getArgs();
            int accountNumber = (int) objects[0];
            Account account = accountsService.getAccountByOnlyAccountNumber(accountNumber);
            int loginAttempts = account.getLoginAttempts();
            if (loginAttempts > 0) {
                account.setLoginAttempts(--loginAttempts);
                if (loginAttempts == 0) {
                    account.setIsActive(false);
                }
                accountsService.updateAccount(account);
            }
            throw new NoAccessException(e.getMessage());
        }

        return result;
    }

}
