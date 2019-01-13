package com.fash.aviv.fashinationapp;

import java.util.List;
import java.util.Map;

public class Category {
    List<Product> products;

    public String name;
    public String description;

    public static Map<String,Category> all_categories;

    public Category() {

    }

    public static Category getCategoryByName(String name) {
        if(all_categories.containsKey(name)) {
            return all_categories.get(name);
        }
        Category cat = new Category();
        return null;
    }
}
