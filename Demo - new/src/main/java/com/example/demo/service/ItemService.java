package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.demo.model.Data;
import com.example.demo.model.Item;


@Service // Mark this as a Spring service component
public class ItemService {

	//public static final Map<Long, String> dataStore = new ConcurrentHashMap<>();
	private final AtomicLong idCounter = new AtomicLong();

	public Item createItem(String value) {
		// Validation is now handled by ItemValidation in the controller,
		// but we might add more complex business rules here if needed.
		long newId = idCounter.incrementAndGet();
		Data.getDataStore().put(newId, value);
		return new Item(newId, value);
	}

	public Optional<Item> createItemWithProvidedId(Long id, String value) {
		// Business rule: if ID already exists, we consider it a conflict for creation.
		if (Data.getDataStore().containsKey(id)) {
			return Optional.empty(); // Indicate that creation failed due to conflict
		}
		Data.getDataStore().put(id, value);
		return Optional.of(new Item(id, value));
	}

	public Optional<Item> getItemById(Long id) {
		return Optional.ofNullable(Data.getDataStore().get(id)).map(value -> new Item(id, value));
	}

	public List<Item> getAllItems() {
	    return Data.getDataStore().entrySet().stream()
	            .map(entry -> new Item(entry.getKey(), entry.getValue()))
	            .toList();  
	}
	
	public List<Item> getAllItemsWithDemo() {
		return Data.getDataStore().entrySet().stream()
                .map(entry -> new Item(entry.getKey(), entry.getValue()))
                .filter(item -> "demo".contains(item.value()))
                .toList();
	}
	



    public Optional<Item> updateItem(Long id, String newValue) {
        // Validation for newValue would be in controller/validation class.

        // Using computeIfPresent to update the item if the key exists.
        // The lambda function returns the newValue, which will replace the existing value.
        // If the key is not present, the lambda is not executed, and computeIfPresent returns null.
        String updatedValue = Data.getDataStore().computeIfPresent(id, (key, existingValue) -> newValue);

        if (updatedValue != null) {
            // If updatedValue is not null, it means the key was present and the value was updated.
            return Optional.of(new Item(id, updatedValue));
        } else {
            // If updatedValue is null, it means the key was not present.
            return Optional.empty(); // Item not found for update
        }
    }

	public boolean deleteItem(Long id) {
		return Data.getDataStore().remove(id) != null;
	}
}