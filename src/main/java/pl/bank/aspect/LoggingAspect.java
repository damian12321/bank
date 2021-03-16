package pl.bank.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bank.entity.Account;
import pl.bank.exception.PinNumberException;
import pl.bank.service.AccountsService;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    @Autowired
    AccountsService accountsService;

    @Pointcut("execution(* pl.bank.service.AccountsService.getAccount(int,int))")
    public void beforeGetAccount() {

    }
    @Pointcut("execution(* pl.bank.service.AccountsService.transferMoney(int,int,int,float))")
    public void beforeTransfer() {

    }
    @Around("beforeGetAccount()||beforeTransfer()")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {

        Object result=null;
        try {
            result = proceedingJoinPoint.proceed();
        }catch (PinNumberException e)
        {

            Object[]objects=proceedingJoinPoint.getArgs();
            int accountNumber=(int)objects[0];
            Account account=accountsService.getAccountByOnlyAccountNumber(accountNumber);
                int loginAttempts = account.getLoginAttempts();
                if (loginAttempts > 0) {
                    account.setLoginAttempts(--loginAttempts);
                    if (loginAttempts == 0) {
                        account.setIsActive(false);
                    }
                    accountsService.updateAccount(account);
                }
                throw new PinNumberException(e.getMessage());
            }

        return result;
    }

}
