package com.weighttracker.accessmysql;

import org.springframework.data.repository.CrudRepository;

import com.weighttracker.accessmysql.Weight;

public interface WeightRepository extends CrudRepository<Weight, Integer>
{

}
