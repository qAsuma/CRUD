package solo.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import solo.model.Account;
import solo.model.Client;
import solo.model.interfaces.AccountInterface;

import java.util.List;
import java.util.Optional;

@Component
public class AccountDAO implements AccountInterface {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Account> index() {
        return jdbcTemplate.query("select * from Account", new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public Account show(int id) {
        return jdbcTemplate.query("select * from Account where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Account.class))
                .stream().findAny().orElse(null);
    }


    @Override
    public void save(Account account) {
        jdbcTemplate.update("INSERT INTO Account(email,password) VALUES (?,?)", account.getEmail(), account.getPassword());
    }

    @Override
    public void update(int id, Account updateAccount) {
        jdbcTemplate.update("UPDATE Account SET email=?,password=? WHERE id=?", updateAccount.getEmail(), updateAccount.getPassword(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Account WHERE id=?", id);
    }

    @Override
    public Optional<Client> getClientAccount(int id) {
        return jdbcTemplate.query("select Client.* from Account join Client on Account.client_id = Client.id where Account.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Client.class))
                .stream().findAny();
    }


}
