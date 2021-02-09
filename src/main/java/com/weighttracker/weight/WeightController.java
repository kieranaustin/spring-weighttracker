package com.weighttracker.weight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weighttracker.helper.CsvWriter;

import java.util.Map;
import java.util.Collection;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/weights")
public class WeightController
{
	@Autowired
	private WeightRepository weightRepository;
	
	@PostMapping("/add")
	public String addNewWeight(
			@RequestParam String date, 
			@RequestParam String time, 
			@RequestParam float weight)
	{
		Weight w = new Weight();
		w.setDate(LocalDate.parse(date));
		w.setTime(LocalTime.parse(time));
		w.setWeight(weight);
		
		weightRepository.save(w);
		
		return "redirect:/weights/weights.html";
	}
	
	@PostMapping("/remove")
	public String deleteWeight(@RequestParam Integer weightId)
	{
		weightRepository.deleteById(weightId);
		
		return "redirect:/weights/weights.html";
	}
	
	@GetMapping("/weights.html")
	public String showWeightList(Map<String, Object> model)
	{
		Weights weights = new Weights();
		weights.getWeights().addAll((Collection<Weight>) this.weightRepository.findAll());
		model.put("weights",  weights);
		return "weights/weightList";
	}

	@GetMapping(path="/raw/json")
	public @ResponseBody Iterable<Weight> getAllWeightsJson()
	{
		return weightRepository.findAll();
	}

	@GetMapping(path="/raw/csv")
	public @ResponseBody String getAllWeightsCsv()
	{
		String result = new String("empty, empty, 0.0");
		try
		{
			result = CsvWriter.makeWeightCsvString(weightRepository.findAll());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
