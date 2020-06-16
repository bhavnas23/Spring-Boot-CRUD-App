package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Car;

public interface CarDao {
	void insertCar(Car car);
	void insertCars(List<Car> cars);
	List<Car> getAllCars();
	Car getCarById(String id);
	void updateCar(Car car);
	void deleteCar(String carId);
	boolean findCarId(String id);
}
