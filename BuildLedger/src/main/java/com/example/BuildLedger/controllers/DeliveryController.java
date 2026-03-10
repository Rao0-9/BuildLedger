package com.example.BuildLedger.controllers;
import java.net.URI;
import java.util.*;


import com.example.BuildLedger.model.Delivery;
import com.example.BuildLedger.service.DeliveryService;
import jakarta.validation.Valid; //Triggers bean validation on the request body
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; //Allow us to control https status code & header
import org.springframework.web.bind.annotation.*;  //Spring MVC annotation(@RestController,@RequestMapping etc)

@RestController  // Marks this class as REST controller. handle REST API and return data(JSON)
@CrossOrigin  //enables browser clients from another origin to call your Spring REST endpoints.
@RequestMapping("/api/delivery") //A base path for all endpoints in this controller.
public class DeliveryController {

    @Autowired //DeliveryService ka object bana kar yahan inject karega.
    private DeliveryService service;

//    @RequestMapping("/")
//    public String greet(){
//        return "Hello it's working!!!";
//    }


    //Create Delivery
    @PostMapping //Handles HTTP POST to /api/deliveries. Used to create resources.
    public ResponseEntity<Delivery> create(@RequestBody Delivery body) {  //@RequestBody Delivery body: Spring converts incoming JSON → Delivery object.
        Delivery saved = service.create(body);
        // Return 201 Created with Location header
        return ResponseEntity
                .created(URI.create("/api/deliveries/" + saved.getId()))
                .body(saved);
    }


    // List all deliveries
    @GetMapping
    public List<Delivery> list() {
        return service.list();
    }

    // Get one delivery by ID
    @GetMapping("/{id}")
    public ResponseEntity<Delivery> get(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing delivery
    @PutMapping("/{id}")
    public ResponseEntity<Delivery> update(@PathVariable Long id,
                                           @Valid @RequestBody Delivery body) { //@valid - Runs validation annotations in Delivery
        return service.update(id, body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Delete a delivery
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }




}
