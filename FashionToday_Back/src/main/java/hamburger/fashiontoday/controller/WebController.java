package hamburger.fashiontoday.controller;

import java.util.Arrays;

import hamburger.fashiontoday.domain.Customer;
import hamburger.fashiontoday.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    CustomerRepository repository;

    @GetMapping("/save")
    public String process(){

        repository.save(new Customer("Kiseong", "Sim"));
        repository.save(new Customer("WonSeok", "Oh"));
        repository.save(new Customer("DongHoon", "Jang"));

        return "Done";
    }


    @GetMapping("/findall")
    public String findAll(){

        String result = "";

        for(Customer cust : repository.findAll()){
            result += cust + "</br>";
        }

        return result;
    }

    @GetMapping("/findbyid")
    public String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        return result;
    }

    @GetMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "";

        for(Customer cust: repository.findByLastName(lastName)){
            result += cust + "</br>";
        }

        return result;
    }
}