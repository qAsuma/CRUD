package solo.model.interfaces;

import solo.model.Account;
import solo.model.Client;

import java.util.List;
import java.util.Optional;

public interface AccountInterface {
    List<Account> index();

    Account show(int id);
    void save(Account account);


    void  update(int id, Account updateAccount);


    void delete(int id);

    Optional<Client> getClientAccount(int id);


}
