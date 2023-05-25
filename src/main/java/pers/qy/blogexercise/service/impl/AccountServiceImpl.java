package pers.qy.blogexercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.qy.blogexercise.dao.AccountDao;
import pers.qy.blogexercise.model.entity.Account;
import pers.qy.blogexercise.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;

    /**
     * 创建用户
     * @param userName
     * @param email
     * @param password
     * @return
     */
    @Override
    public Boolean createAccount(String userName, String email, String password) {
        Account account = accountDao.findByUserEmail(email);
        if (account == null) {
            Account newAccount = new Account();
            newAccount.setUserName(userName);
            newAccount.setUserEmail(email);
            newAccount.setPassword(password);

            Account res = accountDao.save(newAccount);
            return res != null;
        }
        return false;
    }

    /**
     * 获取所有的Account信息
     * @return
     */
    @Override
    public List<Account> getAllAccounts() {
        return accountDao.findAll();
    }

    /**
     * 通过用户email获取Account信息
     * @param email
     * @return
     */
    @Override
    public Account getByEmail(String email) {
        return accountDao.findByUserEmail(email);
    }
}
