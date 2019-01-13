package com.fash.aviv.fashinationapp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    String id;
    Date time;
    public String email,phone,address,price,name,product_id,isPaid;


    public Order(Map<String,Object> map, String id) {
        Map<String,String> new_map = new HashMap<>();
        for(String key:map.keySet()) {
            String new_s  = String.valueOf(map.get(key));
            new_map.put(key,new_s);
        }

        this.id = id;
        this.product_id = new_map.get("product_id");
        this.email = new_map.get("email");
        this.phone = new_map.get("phone");
        this.address = new_map.get("address");
        this.price = new_map.get("price");
        this.name = new_map.get("name");
        this.isPaid = new_map.get("paid");
    }


    public Order(String id,String product_id, Date time, String price, String name,String email, String phone_number, String address, boolean isPaid) {
        this.id = id;
        this.product_id = product_id;
        this.time = time;
        this.name = name;
        this.price = price;
        this.email = email;
        this.phone = phone_number;
        this.address = address;
        this.isPaid = String.valueOf(isPaid);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public void save() {
        DB.onUpdate("Orders",id,getOrderMap());
    }

    public Map<String,Object> getOrderMap() {
        Map<String,Object> map = new HashMap<>();

        map.put("product_id",product_id);

        map.put("price",price);
        map.put("email",email);
        map.put("time",time);
        map.put("name",name);
        map.put("phone",phone);
        map.put("address",address);
        map.put("paid",isPaid);

        return map;
    }

}
