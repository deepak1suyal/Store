package com.store.store.Service;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.store.store.Jwt.Jwt;
import com.store.store.Model.Emp;
import com.store.store.Model.Dao;

import jakarta.transaction.Transactional;
@org.springframework.stereotype.Service
public class Service implements Serv {
    @Autowired
    Dao dao;

@Autowired
UserService userService;

@Autowired
Jwt jwt;
@Autowired
AuthenticationManager authenticationManager;
@Autowired
AuthenticationProvider authProvider;


    @Override
    public String getEmp() {
        // TODO Auto-generated method stub
        try {
            ArrayList<Emp> emp=(ArrayList<Emp>) dao.findAll();
            if(emp!=null&&emp.size()>0){
                return emp.toString();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return e.getMessage();
        }
        return "No Employee found";
    }

    @Override
    public String addEmp(Emp emp) {
        // TODO Auto-generated method stub
        try {
            if(emp!=null){
dao.save(emp);
return "Employee added successfully";
            }
        } catch (Exception e) {
            // TODO: handle exception
            return e.getMessage();
        }
        return "Failed to add employee";
    }
    @Transactional
    @Override
    public String deleteEmp(String email) {
        // TODO Auto-generated method stub
       try {
        System.out.println(" Service "+email);
        if(email!=null){
            Optional<Emp> e=dao.findByEmail(email);
            if(e.isPresent()){
                System.out.println("Found employee: " + e.get());
                // dao.deleteById(e.get().getId());
                dao.deleteByEmail(email);
                return "Employee deleted successfully";
            }else{
                return "Employee not found";
            }
        }
        return "Invalid id";
       } catch (Exception e) {
        // TODO: handle exception
        return "Error occured";
       }
    }

    @Override
    public String updateEmp(Emp emp) {
        // TODO Auto-generated method stub
       try {
        if(emp!=null){
            Optional<Emp> e=dao.findByEmail(emp.email);
            if(e.isPresent()){
                Emp oldemp=e.get();
                oldemp.setAge(emp.getAge());
                oldemp.setEmail(emp.getEmail());
                oldemp.setName(emp.getName());
                oldemp.setRole(emp.getRole());
                oldemp.setPassword(emp.getPassword());
                dao.save(oldemp);
                return "Employee updated successfully";
            }else{
                return "Employee not found";
            }
        }
       } catch (Exception e) {
            // TODO: handle exception
            return e.getMessage();
       }
       return "Failed to update employee";
    }

    public String login(String email, String password) {
        // TODO Auto-generated method stub
        try {
         /*if(email!=null&&password!=null){
                Optional<Emp> e=dao.findByEmail(email);
                if(e.isPresent()){
                    if(e.get().getPassword().equals(password)){
                        return "Login successful";
                    }else{
                        return "Invalid password";
                    }
                }else{
                    return "Employee not found";
                }
            } */   
      //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
       
         System.out.println("Loaded user: " + userService.loadUserByUsername(email));
       UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(email, password);
        System.out.println("Auth Token: " + authToken);
        authProvider.authenticate(authToken);
        String token=jwt.generateToken(email);
        System.out.println("Generated token: " + token);
        return token;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
       
    }

    public String getUserInfo(String token) {
        // TODO Auto-generated method stub
       try {
        if(token!=null)
        {System.out.println("Received token: " + token);
        // remove Bearer prefix
 if(token.startsWith("Bearer "))
 {
  token = token.substring(7);
 }

 System.out.println("Actual token: " + token);

        System.out.println("Extracted username: " + jwt.extractUsername(token));
            return "Hellow " +jwt.extractUsername(token);        
        }
        else{
            return "Invalid token";
        }
       } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        return e.getMessage();
       }
	  
    }
    
}
