package com.example.cqrs.restcontroller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cqrs.event.store.EventStore;
import com.example.cqrs.event.store.EventStoreEntry;
import com.example.cqrs.service.EventStoreService;

@RestController
@RequestMapping(EventStoreAPI.API + EventStoreAPI.EVENT_STORE)
public class EventStoreAPI {
	
	public static final String API = "/api";
	public static final String EVENT_STORE = "/eventstores";
	
	private static final String DECODE = "/decode";
	private static final String OBJECTS = "/objects";
	private static final String AGGREGATE_ID = "/{aggregateId}";
	
	@Autowired
	private EventStoreService service;
	
	@GetMapping 
    public List<EventStore> getEventStores() {
		return service.getAllEventStores();
	}
	
	@GetMapping(AGGREGATE_ID) 
    public List<EventStore> getEventsForAggregate(@PathVariable String aggregateId) {
		return service.getEventsForAggregate(UUID.fromString(aggregateId));
	}
	
	@GetMapping(AGGREGATE_ID + DECODE) 
    public List<EventStoreEntry> getEventEntryForAggregate(@PathVariable String aggregateId) {
		return service.getEventEntriesForAggregate(UUID.fromString(aggregateId));
	}
	
	@GetMapping(OBJECTS)
	public List<Object> getPayloadsOfEventStores() {
		return service.getPayloadsOfEventStores();
	}
	
	@GetMapping(OBJECTS + AGGREGATE_ID)
	public List<Object> getPayloadsOfEventStores(@PathVariable String aggregateId) {
		return service.getPayloadsForAggregate(UUID.fromString(aggregateId));
	}
	
	@GetMapping(DECODE)
	public List<EventStoreEntry> getDecodedEventStores() {
		return service.getDecodedEventStores();
	}

}
