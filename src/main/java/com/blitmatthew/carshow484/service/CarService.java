package com.blitmatthew.carshow484.service;

import com.blitmatthew.carshow484.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car addCar(Car car);
    Car updateCar(Car car);
    void deleteById(Long id);
}
