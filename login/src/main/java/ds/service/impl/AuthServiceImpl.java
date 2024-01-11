package ds.service.impl;


import ds.dao.AccountMapper;
import ds.pojo.Account;
import ds.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author Qinghan Huang
 * @Desc
 * @Version 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AccountMapper accountMapper;


    @Override
    public boolean login(String email, String password) {
        Account account = accountMapper.findAccountByUsernameOrEmail(email);

        if (account != null && account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean register(String username, String password, String email) {
        try {
            int result = accountMapper.creatAccount(username, password, email);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
