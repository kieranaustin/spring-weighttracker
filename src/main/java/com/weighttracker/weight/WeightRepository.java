package com.weighttracker.weight;

import org.springframework.data.repository.CrudRepository;

import com.weighttracker.weight.Weight;

public interface WeightRepository extends CrudRepository<Weight, Integer>
{

}
