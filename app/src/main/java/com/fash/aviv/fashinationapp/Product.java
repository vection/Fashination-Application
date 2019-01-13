package com.fash.aviv.fashinationapp;

import java.util.HashMap;
import java.util.Map;

public class Product {

    String  id;
    String category_name;
    String name;
    String price;
    String description;
    String photoindex;

    public Product(String id,String name, String price, String description, String photoindex, String category_name) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.photoindex = photoindex;
        this.category_name = category_name;
    }

    public Product(Map<String,Object> map,String id) {
        Map<String,String> new_map = new HashMap<>();
        for(String key:map.keySet()) {
            String new_s  = String.valueOf(map.get(key));
            new_map.put(key,new_s);
        }

        this.id = id;
        this.name = new_map.get("name");
        this.price = new_map.get("price");
        this.description = new_map.get("description");
        this.photoindex = new_map.get("photoindex");
        this.category_name = new_map.get("category");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoindex() {
        return photoindex;
    }

    public void setPhotoindex(String photoindex) {
        this.photoindex = photoindex;
    }

    public Map<String,Object> getProductMap() {
        Map<String,Object> map = new HashMap<>();

        map.put("name",name);
        map.put("price",price);
        map.put("description",description);
        map.put("photoindex",photoindex);
        map.put("category",category_name);

        return map;
    }

    public String getCategory() {
        return this.category_name;
    }

    public void save() {
        DB.onUpdate("Products",id,getProductMap());
    }
}
