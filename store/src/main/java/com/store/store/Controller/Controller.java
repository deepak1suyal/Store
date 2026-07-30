package com.store.store.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.store.store.Model.Emp;
import com.store.store.Service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    Service service;
    @PostMapping("/addemp")
    public String postMethodName(@RequestBody Emp entity) {
        //TODO: process POST request 
        return  service.addEmp(entity);
    }
  @GetMapping("/Login")
  public String UserLogin(@RequestParam String email,@RequestParam String password){ {
      return service.login(email, password);}
  }

  @GetMapping("/userinfo")
  public String getUserInfo(@RequestHeader("Authorization") String token)
  {System.out.println("Received token in controller: " + token);
return service.getUserInfo(token);
  }
  
    @GetMapping("/getallemp")
    public String getMethodName() {
        return service.getEmp();
    }
    @PutMapping("/updateemp")
    public String putMethodName(@RequestBody Emp emp) {
        //TODO: process PUT request

        return service.updateEmp(emp);
    }
    @DeleteMapping("/deleteemp")
    public String deleteMethodName(@RequestBody Emp email) {
                System.out.println("Hi" + email);
        return service.deleteEmp(email.getEmail());
        }
    }

