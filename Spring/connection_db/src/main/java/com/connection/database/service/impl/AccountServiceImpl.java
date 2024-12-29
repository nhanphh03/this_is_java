package com.connection.database.service.impl;

import com.connection.database.entity.Account;
import com.connection.database.repository.jdbc.AccountJdbcBasicRepository;
import com.connection.database.repository.jdbc.AccountJdbcTemplateRepository;
import com.connection.database.repository.jpa.AccountJPARepository;
import com.connection.database.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountJPARepository accountJPARepository;

    private final AccountJdbcBasicRepository accountJdbcBasicRepository;

    private final AccountJdbcTemplateRepository jdbcTemplateRepository;

//    @Autowired
//    private AccountHibernateRepository accountHibernateRepository;

    public AccountServiceImpl( AccountJdbcBasicRepository accountJdbcBasicRepository,
                               AccountJPARepository accountJPARepository,
                               AccountJdbcTemplateRepository jdbcTemplateRepository) {
        this.accountJdbcBasicRepository = accountJdbcBasicRepository;
        this.accountJPARepository = accountJPARepository;
        this.jdbcTemplateRepository = jdbcTemplateRepository;
    }

    @Override
    public Integer countAllAccounts() {
        Integer a = accountJdbcBasicRepository.getListAccountV3();
        Integer b = accountJdbcBasicRepository.getListAccount().size();
        return a + b;
    }

    @Override
    @Transactional
    public List<Account> getAccounts(String name) {
        return List.of();
    }

    public Page<Account> getAllAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountJPARepository.findAll(pageable);
    }

    @Override
    public List<Account> getAllAccounts() {
        return List.of();
    }

    private List<Account> getAccountsJDBCTemplate() {
        return jdbcTemplateRepository.getAllAccountJDBCTemplate();
    }

    // FIXME: Hàm này chưa xử lý.
    private Account createNewAccountHibernate(Account account){
//        account.setId(accountHibernateRepository.createAccount(account));
        return account;
    }


}
