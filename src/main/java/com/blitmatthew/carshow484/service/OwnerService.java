package com.blitmatthew.carshow484.service;

import com.blitmatthew.carshow484.entity.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getOwners();
    Owner addOwner(Owner owner);
}
