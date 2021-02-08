package com.weighttracker.weight;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weights
{
	private List<Weight> weights;
	
	@XmlElement
	public List<Weight> getWeights()
	{
		if (weights == null)
		{
			weights = new ArrayList<>();
		}
		return weights;
	}

}
