package com.example.demo.service;


import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.model.Car;


public interface CarService{
	void insertCar(Car car);
	void insertCars(List<Car> cars);
	List<Car> getAllCars();
	Car getCarById(String id);
	void updateCar(Car car);
	void deleteCar(String carId);
}
