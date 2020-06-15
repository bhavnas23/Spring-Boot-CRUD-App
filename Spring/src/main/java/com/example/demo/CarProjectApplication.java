package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;

@SpringBootApplication
public class CarProjectApplication {
	@Autowired
	CarService cs;

	
	public static void main(String[] args) {
//		SpringApplication.run(CarProjectApplication.class, Arcs);
		ApplicationContext context = SpringApplication.run(CarProjectApplication.class, args);
		CarService cs = context.getBean(CarService.class);
		
		
//		Car car= new Car();
//		car.setCarId("car");
//		car.setCarName("car");
//		
//		Car car1= new Car();
//		car1.setCarId("carMred");
//		car1.setCarName("car1");
////		
//		Car car2= new Car();
//		car2.setCarId("car2");
//		car2.setCarName("car2");
//
//		cs.insertCar(car);
//
//		List<Car> cars = new ArrayList<>();
//		cars.add(car1);
//		cars.add(car2);
//		cs.insertCars(cars);

		List<Car> carList= cs.getAllCars();
		for (Car c : carList){
			System.out.println(c.getCarId()+c.getCarName());
		}

		Car car=cs.getCarById("carMred");
		System.out.println(car.getCarId()+car.getCarName());
	}
}
