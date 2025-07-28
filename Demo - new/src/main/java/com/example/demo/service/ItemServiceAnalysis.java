package com.example.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Data;
import com.example.demo.model.Item;

//TODO: SpringBoot:Practical 5 (Group) - Search function restful services


@Service // Mark this as a Spring service component
public class ItemServiceAnalysis {
	
	//define the model need to be map
	//private final Map<Long, String> dataStore = new ConcurrentHashMap<>();
	// TODO: Complete the code below
	
	// This method filters and returns only items with "demo" in their value
	public List<Item> getAllItemsWithDemo() {
		return Data.getDataStore().entrySet().stream()
                .map(entry -> new Item(entry.getKey(), entry.getValue()))
                .filter(item -> "demo".contains(item.value()))
                .toList();
	}
	
	public List<Item> getAllItems() {
	    return Data.getDataStore().entrySet().stream()
	            .map(entry -> new Item(entry.getKey(), entry.getValue()))
	            .toList();  
	}
	

}
