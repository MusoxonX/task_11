package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Client;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

//    add
    public Result addClient(@RequestBody Client clientDto){
        boolean exists = clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber());
        if (exists){
            return new Result("like this phone number already exists",false);
        }
        clientRepository.save(clientDto);
        return new Result("Client added to repository",true);
    }

//    get
    public List<Client> getClient(){
        List<Client> all = clientRepository.findAll();
        return all;
    }

//    get by Id
    public Client getById(@PathVariable Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()){
            return optionalClient.get();
        }
        return new Client();
    }

//    update
    public Result editClient(@PathVariable Integer id, @RequestBody Client clientDto){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            return new Result("Client not found",false);
        }
        Client client = optionalClient.get();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
        return new Result("Client updated",true);
    }

//    delete
    public Result deleteClient(@PathVariable Integer id){
        boolean exists = clientRepository.existsById(id);
        if (exists){
            clientRepository.deleteById(id);
            return new Result("Client deleted",true);
        }
        return new Result("Client not found",false);
    }
}
