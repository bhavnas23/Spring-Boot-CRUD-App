package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CarDao;
import com.example.demo.model.Car;
import com.example.demo.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarDao carDao;

	@Override
	public void insertCar(Car car) {
		carDao.insertCar(car);
	}

	@Override
	public void insertCars(List<Car> cars) {
		carDao.insertCars(cars);
	}

	public List<Car> getAllCars() {
		List<Car> cars = carDao.getAllCars();
		return cars;
	}

	@Override
	public Car getCarById(String carId) {
		Car car = carDao.getCarById(carId);
		return car;
	}
	
	@Override
	public void updateCar(Car car) {
		carDao.updateCar(car);
	}
	
	public void deleteCar(String carId) {
		carDao.deleteCar(carId);
	}
	
	public boolean findCarId(String carId) {
		return carDao.findCarId(carId);
	}
}
