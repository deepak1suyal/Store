package com.store.store.Service;

import com.store.store.Model.Emp;

public interface Serv {
   public String getEmp();
    public String addEmp(Emp emp);
    public String deleteEmp(String email);
    public String updateEmp(Emp emp);
}
