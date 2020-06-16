package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.CarService;
import com.example.demo.model.Car;


@Controller
@EnableAutoConfiguration
public class CarController {
	@Autowired
	CarService cs;
	

	@RequestMapping(value= {"/welcome","/"}, method= RequestMethod.GET)
	public String viewWelcomePage(Model model ) {
	    model.addAttribute("message", "Welcome");
	    return "welcome.html";
	}
	
	
	@RequestMapping(value = { "/carList" }, method = RequestMethod.GET)
    public String carList(Model model) {
		List<Car> cars = cs.getAllCars();
        model.addAttribute("cars", cars);
 
        return "carList.html";
    }
 
    @RequestMapping(value = { "/addCar" }, method = RequestMethod.GET)
    public String showAddCarPage(Model model) {
 
        Car car = new Car();
        model.addAttribute("car", car);
 
        return "addCar.html";
    }
 
    @RequestMapping(value = { "/addCar" }, method = RequestMethod.POST)
    public String insertCar(Model model,
            @ModelAttribute("car") Car car) {
 
        String carId = car.getCarId();
        String carName = car.getCarName();
        String carModel=car.getCarModel();
        String manufactureName = car.getManufactureName();
        String manufactureYear=car.getManufactureYear();
        String carColor=car.getCarColor();
 

        if (carId != null && cs.findCarId(carId)==false && carId.length() > 0 //
                && carName != null && carName.length() > 0) {
            Car newCar = new Car();
	        newCar.setCarId(carId);
	        newCar.setCarName(carName);
	        newCar.setCarModel(carModel);
	        newCar.setManufactureName(manufactureName);
	        newCar.setManufactureYear(manufactureYear);
	        newCar.setCarColor(carColor);
	        cs.insertCar(newCar);
	        return "redirect:/carList";
        }
        model.addAttribute("errorMessage", "The record can not be added due to empty or duplicate CarId or empty car name");
        return "addCar.html";
    }
	
    @RequestMapping(value = { "/deleteCar/{id}" }, method = RequestMethod.GET)
    public String deleteCar(@PathVariable("id") String id, Model model) {
    	Car car = cs.getCarById(id);
//        model.addAttribute("car", car);
    	cs.deleteCar(car.getCarId());
        return "redirect:/carList";
    }
    
    @RequestMapping(value = { "/deleteCar" }, method = RequestMethod.POST)
    public String deleteCar(Model model, 
            @ModelAttribute("car") Car car) {
    	String carId=car.getCarId();
    	cs.deleteCar(carId);
    	return "redirect:/carList";
    			
    }
    
    
    
    @RequestMapping(value = { "/editCar/{id}" }, method = RequestMethod.GET)
    public String showUpdateCarPage(Model model) {
 
        Car car = new Car();
        model.addAttribute("car", car);
 
        return "editCar.html";
    }

    @RequestMapping(value = { "/editCar/{id}" }, method = RequestMethod.POST)
    public String updateCar(Model model, //
            @ModelAttribute("car") Car car) {
 
        String carId = car.getCarId();
        String carName = car.getCarName();
        String carModel=car.getCarModel();
        String manufactureName = car.getManufactureName();
        String manufactureYear=car.getManufactureYear();
        String carColor=car.getCarColor();
 
        if (carId != null && cs.findCarId(carId)==true && carId.length() > 0 
                && carName != null && carName.length() > 0) {
            Car newCar = new Car();
	        newCar.setCarId(carId);
	        newCar.setCarName(carName);
	        newCar.setCarModel(carModel);
	        newCar.setManufactureName(manufactureName);
	        newCar.setManufactureYear(manufactureYear);
	        newCar.setCarColor(carColor);
	        cs.updateCar(newCar);
	        return "redirect:/carList";
        }
        model.addAttribute("carId", carId);
        model.addAttribute("carName", carName);
        model.addAttribute("errorMessage", "The record can not be updated due to invalid or duplicate CarId or car name");
        return "editCar.html";
    }

}
