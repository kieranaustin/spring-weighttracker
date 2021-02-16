package com.weighttracker.weight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.weighttracker.helper.CsvWriter;

import java.util.Map;
import java.util.List;
import java.io.IOException;

@Controller
@RequestMapping("/weights")
public class WeightController
{
	@Autowired
	private WeightRepository weightRepository;
	
	private Weight lastWeightEntered;
	
	private final String REDIRECT_WEIGHT_LIST = "redirect:/weights/list.html";
	
	@PostConstruct
	private void setupWeightController()
	{
		lastWeightEntered = weightRepository.findTopByOrderByDateDesc();
	}

	@PostMapping("/add")
	public String addNewWeight(@Valid Weight weight)
	{
		weightRepository.save(weight);
		lastWeightEntered = weight;
		
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
		List<Weight> weights = this.weightRepository.findAllByOrderByDateAsc();
		model.put("weights",  weights);
		model.put("lastWeightEntered", lastWeightEntered);

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
