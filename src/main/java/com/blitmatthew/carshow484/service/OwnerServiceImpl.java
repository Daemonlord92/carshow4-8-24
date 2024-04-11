package com.blitmatthew.carshow484.service;

import com.blitmatthew.carshow484.entity.Owner;
import com.blitmatthew.carshow484.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> getOwners() {
        return (List<Owner>) ownerRepository.findAll();
    }

    @Override
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }
}
