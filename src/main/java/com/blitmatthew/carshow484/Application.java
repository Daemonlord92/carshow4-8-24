package com.blitmatthew.carshow484;

import com.blitmatthew.carshow484.entity.Car;
import com.blitmatthew.carshow484.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CarRepository carRepository;

	private final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Car car = Car.builder()
				.make("Ford")
				.model("F150")
				.year(1992)
				.vin("fh03782h4f8ygh4082f83507h20")
				.mileage(150000.454)
				.build();
		Car car1 = Car.builder()
				.make("BWM")
				.model("330i")
				.year(2020)
				.vin("028754h38gh038245hv204h0")
				.mileage(5000.469)
				.build();

		carRepository.saveAll(List.of(car, car1));

		carRepository.findAll().forEach(x -> logger.info(x.toString()));
	}
}
