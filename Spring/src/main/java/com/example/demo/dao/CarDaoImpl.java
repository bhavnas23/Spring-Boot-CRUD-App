package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Jdbc;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Car;
@Repository
public class CarDaoImpl extends JdbcDaoSupport implements CarDao{
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void insertCar(Car car) {
		String sql = "INSERT INTO cardetails " +
				"(CarId, CarName, ModelName, ManufactureName, ManufactureYear, color) VALUES (?, ?, ?, ?, ?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				car.getCarId(), car.getCarName(), car.getCarModel(), car.getManufactureName(), car.getManufactureYear(), car.getCarColor()
		});
	}
	
	@Override
	public void insertCars(List<Car> cars) {
		String sql ="INSERT INTO cardetails " +
				"(CarId, CarName, ModelName, ManufactureName, ManufactureYear, color) VALUES (?, ?, ?, ?, ?, ?)" ;
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Car car = cars.get(i);
				ps.setString(1, car.getCarId());
				ps.setString(2, car.getCarName());
				ps.setString(3, car.getCarModel());
				ps.setString(4, car.getManufactureName());
				ps.setString(5, car.getManufactureYear());
				ps.setString(6, car.getCarColor());
			}
			
			public int getBatchSize() {
				return cars.size();
			}
		});

	}
	
	@Override
	public List<Car> getAllCars(){
		String sql = "SELECT * FROM cardetails";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Car> result = new ArrayList<Car>();
		for(Map<String, Object> row:rows){
			Car car = new Car();
			car.setCarId((String)row.get("CarId"));
			car.setCarName((String)row.get("carName"));
			car.setCarModel((String)row.get("Modelname"));
			car.setManufactureName((String)row.get("manufactureName"));
			car.setManufactureYear((String)row.get("manufactureYear"));
			car.setCarColor((String)row.get("Color"));
			result.add(car);
		}
		
		return result;
	}

	@Override
	public Car getCarById(String carId) {
		String sql = "SELECT * FROM cardetails WHERE CarId = ?";
		return (Car)getJdbcTemplate().queryForObject(sql, new Object[]{carId}, new RowMapper<Car>(){
			@Override
			public Car mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Car car = new Car();
				car.setCarId(rs.getString("CarId"));
				car.setCarName(rs.getString("CarName"));
				car.setCarModel(rs.getString("ModelName"));
				car.setManufactureName(rs.getString("ManufactureName"));
				car.setManufactureYear(rs.getString("ManufactureYear"));
				car.setCarColor(rs.getString("color"));
				return car;
			}
		});
	}
	
	
	
	@Override
	public void updateCar(Car car) {
		String sql = "Update cardetails SET "+
					"carName=?, Modelname=?, manufactureName=?, manufactureYear=?, color=? where carid = ?";
		getJdbcTemplate().update(sql, new Object[]{
				 car.getCarName(),car.getCarModel(),car.getManufactureName(), car.getManufactureYear(),car.getCarColor(),car.getCarId(),
		});
	}
	public void deleteCar(String carId) {
		String sql="delete from cardetails where carid=?";
		getJdbcTemplate().update(sql, new Object[] {carId});
//		return jdbcTemplate.update(sqlQuery, id) > 0;
	}
}
