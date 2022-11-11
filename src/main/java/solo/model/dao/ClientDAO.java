package solo.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import solo.model.Account;
import solo.model.Client;
import solo.model.interfaces.ClientInterface;

import java.util.List;

@Component
public class ClientDAO implements ClientInterface {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Client> index() {
        return jdbcTemplate.query("select * from Client", new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public Client show(int id) {
        return jdbcTemplate.query("select * from Client where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Client.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public void save(Client client) {
        jdbcTemplate.update("INSERT INTO Client(name,surname,age) VALUES (?,?,?)", client.getName(), client.getSurname(), client.getAge());
    }

    @Override
    public void update(int id, Client updateClient) {
        jdbcTemplate.update("UPDATE Client SET name=?,surname=?,age=? WHERE id=?", updateClient.getName(), updateClient.getSurname(), updateClient.getAge(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Client WHERE id=?", id);
    }




}
