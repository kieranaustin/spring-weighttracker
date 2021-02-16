package com.weighttracker.weight;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.weighttracker.weight.Weight;

public interface WeightRepository extends CrudRepository<Weight, Integer>
{
	public List<Weight> findAllByOrderByDateAsc();
	public Weight findTopByOrderByDateDesc();
}
