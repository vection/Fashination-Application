package com.fash.aviv.fashinationapp;

import java.util.HashMap;
import java.util.Map;

public class Employee {
    public static Employee emp = null;

    public String email,password,name,phone,rank,id;


    private Employee(Map<String,Object> map,String id) {
        Map<String,String> new_map = new HashMap<>();
        for(String key:map.keySet()) {
            String new_s  = String.valueOf(map.get(key));
            new_map.put(key,new_s);
        }

        this.id = id;
        this.name = new_map.get("name");
        this.email = id;
        this.password = new_map.get("password");
        this.phone = new_map.get("phone");
        this.rank = new_map.get("rank");

    }

    public static Employee SetEmployee(Map<String,Object> map,String id) {
        emp = new Employee(map,id);
        return emp;
    }

    public Map<String,Object> getEmployeeMap() {
        Map<String,Object> map = new HashMap<>();

        map.put("name",name);
        map.put("email",email);
        map.put("password",password);
        map.put("phone",phone);
        map.put("rank",rank);

        return map;
    }

    public void save() {
        DB.onUpdate("Employee",id,getEmployeeMap());
    }

}
