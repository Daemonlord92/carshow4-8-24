package com.blitmatthew.carshow484.service;

import com.blitmatthew.carshow484.entity.Car;
import com.blitmatthew.carshow484.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        carRepository.findById(car.getId()).orElseThrow(() -> new EntityNotFoundException("Car does not exist"));
        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car does not exist"));
        carRepository.deleteById(id);
    }
}
