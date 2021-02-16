package com.weighttracker.weight;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "weights")
public class Weight 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "date")
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate date;

	@Column(name = "time")
	@DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm")
	@NotNull
	private LocalTime time;

	@Column(name = "value")
	@DecimalMin("0.1")
	@NotNull
	private float     value;

	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public float getValue()
	{
		return value;
	}
	
	public void setValue(float value)
	{
		this.value = value;
	}
	
	public LocalDate getDate()
	{
		return date;
	}
	
	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public LocalTime getTime()
	{
		return time;
	}
	
	public void setTime(LocalTime time)
	{
		this.time = time;
	}
}
