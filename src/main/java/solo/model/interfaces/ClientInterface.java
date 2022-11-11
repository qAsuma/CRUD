package solo.model.interfaces;

import solo.model.Account;
import solo.model.Client;

import java.util.List;

public interface ClientInterface {

    List<Client> index();

    Client show (int id);


    void save(Client client);


    void  update(int id, Client updateClient);


    void delete(int id);





}
