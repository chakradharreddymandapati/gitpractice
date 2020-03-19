package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Vehicle;
import com.example.repository.ElasticVehicleRepository;
import com.example.service.QueryDslService;

@RestController
public class ElasticController {
	@Autowired
	private ElasticVehicleRepository repository;
    @Autowired
    private QueryDslService dslService;
	@PostMapping("/saveVehicles")
	private List<Vehicle> saveVehicles(@RequestBody List<Vehicle> vehicle) {
		repository.save(vehicle);
		return vehicle;
	}

	@GetMapping("/getDetails")
	private List<Vehicle> getVehicleDetails() {
		List<Vehicle> v = new ArrayList<>();
		Iterable<Vehicle> v1 = repository.findAll();
		for (Vehicle v2 : v1) {
			v.add(v2);
		}
		return v;
	}

	@GetMapping("/serchid/{id}")
	private Vehicle findId(@PathVariable int id) {

		return repository.findByVid(id);
	}

	@GetMapping("/recordDeleted/{id}")
	private String deleted(@PathVariable int id) {
		repository.delete(id);
		return "record deleted";
	}

	@PutMapping("/updateVehicles")
	private List<Vehicle> updateVehicles(@RequestBody List<Vehicle> vehicle) {
		repository.save(vehicle);
		return vehicle;
	}

	@GetMapping("/countVehicles")
	private int countVehicles() {
		int count = (int) repository.count();
		return count;
	}
	@GetMapping("/serchMultipuleFileds/{id}/{name}")
	private List<Vehicle> serchMultipuleFileds(@PathVariable int id,@PathVariable String name) {
		 
		return dslService.serchMultipuleFileds(id,name);
	}
	@GetMapping("/vehicleName/{name}")
	private List<Vehicle> getVehicleNameDetails(@PathVariable String name){
		
		return dslService.getVehicleNameDetails(name);
	}
	@GetMapping("/findAnyFiled/{text}")
	private List<Vehicle> getAnyFiled(@PathVariable String text){
		return dslService.getAnyFiled(text);
	}
	@GetMapping("/rangs/{sal1}/{sal2}")
	private List<Vehicle> getRanges(@PathVariable double sal1,@PathVariable double sal2){
		return dslService.getRanges(sal1,sal2);
	} 
	@GetMapping("/maxPrice")
    private double getMaxPrice(){
		return dslService.getMaxPrice();  
	} 
}
