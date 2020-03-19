package com.example.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.model.Vehicle;

public interface ElasticVehicleRepository extends ElasticsearchRepository<Vehicle, Integer> {

	Vehicle findByVid(int id);

}
