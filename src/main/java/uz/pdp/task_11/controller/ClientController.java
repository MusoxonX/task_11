package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.Client;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping()
    public Result addClient(@RequestBody Client clientDto){
        Result result = clientService.addClient(clientDto);
        return result;
    }

    @GetMapping()
    public List<Client> getClient(){
        List<Client> client = clientService.getClient();
        return client;
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id){
        Client client = clientService.getById(id);
        return client;
    }

    @PutMapping("/{id}")
    public Result updateClient(@PathVariable Integer id, @RequestBody Client clientDto){
        Result result = clientService.editClient(id, clientDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable Integer id){
        Result result = clientService.deleteClient(id);
        return result;
    }
}
