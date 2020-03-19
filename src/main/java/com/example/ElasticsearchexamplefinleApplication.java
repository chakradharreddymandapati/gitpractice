package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Vehicle;
import com.example.repository.ElasticVehicleRepository;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")

public class ElasticsearchexamplefinleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchexamplefinleApplication.class, args);
		System.out.println("OKOk");

	}
}
