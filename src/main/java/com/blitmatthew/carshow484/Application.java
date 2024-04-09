package com.blitmatthew.carshow484;

import com.blitmatthew.carshow484.entity.Car;
import com.blitmatthew.carshow484.entity.Owner;
import com.blitmatthew.carshow484.repository.CarRepository;
import com.blitmatthew.carshow484.repository.OwnerRepository;
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

	@Autowired
	private OwnerRepository ownerRepository;

	private final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner = Owner.builder()
				.firstName("Carlos")
				.lastName("ChaoticLord")
				.email("carlos.chaoticlord@gmail.com")
				.phone("9072728359")
				.build();
		owner = ownerRepository.save(owner);
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
		Car car2 = Car.builder()
						.make("Ford")
						.model("Model-T")
						.vin("fn07893hn20fj[230ug8290-hg29485hg2-4g")
						.year(1908)
						.mileage(6000.00)
						.owner(owner)
						.build();
		carRepository.saveAll(List.of(car, car1, car2));

		carRepository.findAll().forEach(x -> logger.info(x.toString()));

		logger.warn(car.toString());
		logger.error(car1.toString());
		logger.info(car1.toString());
		logger.debug(car1.toString());
	}
}
