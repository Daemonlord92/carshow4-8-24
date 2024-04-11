package com.blitmatthew.carshow484.controller;

import com.blitmatthew.carshow484.entity.Owner;
import com.blitmatthew.carshow484.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Owner>> getOwners() {
        return new ResponseEntity<>(ownerService.getOwners(), HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.addOwner(owner), HttpStatus.CREATED);
    }
}
