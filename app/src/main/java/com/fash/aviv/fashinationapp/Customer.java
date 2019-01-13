package com.fash.aviv.fashinationapp;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    public String email,password,is_activated,phone,name,address,id;
    public static Customer cus;

    private Customer(String email,String password) {
        this.email = email;
        this.id = email;
        this.password = password;
    }

    private Customer(Map<String,Object> map, String id) {
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
        this.address = new_map.get("address");
        this.is_activated = new_map.get("is_activated");
    }

    public static Customer SetCustomer(String email,String pass) {
        cus = new Customer(email,pass);
        return cus;
    }

    public static Customer SetCustomer(Map<String,Object> map, String id) {
        cus = new Customer(map,id);
        return cus;
    }

    public void save() {
        DB.onUpdate("Customers",id,getOrderMap());
    }

    public Map<String,Object> getOrderMap() {
        Map<String,Object> map = new HashMap<>();


        map.put("email",email);
        map.put("password",password);
        map.put("name",name);
        map.put("phone",phone);
        map.put("address",address);

        return map;
    }

}
