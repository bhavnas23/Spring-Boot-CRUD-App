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
	

	@RequestMapping(value="/welcome", method= RequestMethod.GET)
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
    public String insertCar(Model model, //
            @ModelAttribute("car") Car car) {
 
        String carId = car.getCarId();
        String carName = car.getCarName();
        String carModel=car.getCarModel();
        String manufactureName = car.getManufactureName();
        String manufactureYear=car.getManufactureYear();
        String carColor=car.getCarColor();
 

        if (carId != null && carId.length() > 0 //
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
        model.addAttribute("errorMessage", "Error");
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
 

        if (carId != null && carId.length() > 0 
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
        model.addAttribute("errorMessage", "The record can not be updated");
        return "editCar.html";
    }
    
    
    
//	@RequestMapping(value="/index", method= RequestMethod.GET)
//	public String viewHomePage2(Model model ) {
////		String mv=new ModelAndView("index");
//	    List<Car> listCars = cs.getAllCars();//	   
//	    model.addAttribute("users", listCars);
////	    return "hello";
//	    return "index.html";
//	}
//	@RequestMapping(value = { "/", "/welcome2" }, method = RequestMethod.GET)
//  public String welcomePage(Model model) {
//      model.addAttribute("title", "Welcome");
//      model.addAttribute("message", "This is welcome page!");
//      return "welcomePage";
//  }
//	
//	@RequestMapping("/index2")
//	public String viewHomePage(Map<String, Object> model ) {
//		 model.put("message", "HowToDoInJava Reader !!");
//	        return "index.jsp";
//	}
//	
//	
//	@RequestMapping(value="/save",method = RequestMethod.POST)    
//    public String save(@ModelAttribute("car") Car car){    
//        cs.insertCar(car);    
//        return "redirect:/all";    
//    }      
//	
//	@RequestMapping("/all")
//	public List<Car> getAll(){
//		return cs.getAllCars();
//	}
//	
//	@RequestMapping("/car/{id}")
//	public Car getCar(@PathVariable("id") String id) {
//		return cs.getCarById(id);
//	}
	
//	@RequestMapping(value="/editcar/{id}")    
//    public String edit(@PathVariable String id, Model m){    
//        Car car=cs.getCarById(id);    
//        m.addAttribute("command",car);  
//        return "careditform";    
//    }   
//	
//	@RequestMapping(value="/editsave",method = RequestMethod.POST)    
//    public String editsave(@ModelAttribute("emp") Car car){    
//        cs.update(car);    
//        return "redirect:/viewemp";    
//    }
//	
//	@RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)    
//    public String delete(@PathVariable int id){    
//        dao.delete(id);    
//        return "redirect:/viewemp";    
//    }   
}
