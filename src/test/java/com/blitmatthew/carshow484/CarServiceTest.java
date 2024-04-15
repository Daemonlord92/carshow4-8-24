package com.blitmatthew.carshow484;


import com.blitmatthew.carshow484.entity.Car;
import com.blitmatthew.carshow484.repository.CarRepository;
import com.blitmatthew.carshow484.service.CarService;
import com.blitmatthew.carshow484.service.CarServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void afterEach() throws Exception {
        closeable.close();
    }

    @Test
    public void updateCarShouldSucceedReturnCar() {
        Car car = Car.builder()
                .id(1l)
                .vin("vnb-425v7h24-09hv78945")
                .make("Ford")
                .model("Raptor")
                .year(2010)
                .mileage(100453.00)
                .build();

        Mockito.when(carRepository.save(car)).thenReturn(car);

        Car car1 = carService.updateCar(car);

        assertThat(car1).isEqualTo(car);
    }

    @Test
    public void updateCarShouldFailThrowExceptionNoId() {
        Car car = Car.builder()
                .mileage(2000.00)
                .year(1992)
                .vin("43fby8b4h0]34-3gh032g4uh234")
                .make("Ford")
                .model("F-150")
                .build();
        Throwable throwable = assertThrows(Exception.class, () -> carService.updateCar(car));

        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Car does not exist");
    }

    @Test
    public void deleteCarShouldSucceedVerifyCall() {

        long id = 1l;

        carService.deleteById(id);

        Mockito.verify(carRepository).deleteById(id);
    }

    @Test
    public void deleteCarShouldFailThrowExceptionInvalidId() {
        long id = 1542l;
        Throwable throwable = assertThrows(Exception.class, () -> carService.deleteById(id));

        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Car does not exist");
    }
}
