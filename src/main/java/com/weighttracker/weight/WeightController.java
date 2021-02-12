package com.weighttracker.weight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	private final String REDIRECT_WEIGHT_LIST = "redirect:/weights/list.html";
	
	@PostMapping("/add")
	public String addNewWeight(
			// TODO: set UI input forms to date/time and pass in here as date/time
			@RequestParam String date, 
			@RequestParam String time, 
			@RequestParam float weight)
	{
		Weight w = new Weight();
		try 
		{
			w.setDate(LocalDate.parse(date));
			w.setTime(LocalTime.parse(time));
			w.setWeight(weight);
			weightRepository.save(w);
		} 
		catch (Exception e)
		{
			// TODO: give feedback to user (i.e. red input form/text) instead of just redirecting
			System.err.println("Wrong input format in Weight Table");
		}
		
		return REDIRECT_WEIGHT_LIST;
	}
	
	@PostMapping("/remove")
	public String deleteWeight(@RequestParam Integer weightId)
	{
		weightRepository.deleteById(weightId);
		
		return REDIRECT_WEIGHT_LIST;
	}
	
	@GetMapping("/list.html")
	public String showWeightList(Map<String, Object> model)
	{
		Weights weights = new Weights();
		Collection<Weight> weightCollection = this.weightRepository.findAllByOrderByDateAsc();
		weights.getWeights().addAll(weightCollection);
		model.put("weights",  weights);
		return "weights/weightList";
	}
	
	@GetMapping("/graph.html")
	public String showWeightGraph()
	{
		return "weights/weightGraph";
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
			result = CsvWriter.makeWeightCsvString(weightRepository.findAllByOrderByDateAsc());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
