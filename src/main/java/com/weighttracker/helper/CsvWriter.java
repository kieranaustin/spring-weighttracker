package com.weighttracker.helper;

import com.weighttracker.weight.Weight;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvWriter
{

	public static String makeWeightCsvString(Iterable<Weight> weights) throws IOException
	{
		Writer csvWriter = new StringBuilderWriter();

		try 
		(
				CSVPrinter csvPrinter = new CSVPrinter(
						csvWriter, 
						CSVFormat.DEFAULT
							.withHeader( "date", "time", "value" )
							.withRecordSeparator("\r\n"));
		)
		{
			for(Weight weight : weights)
			{
				csvPrinter.printRecord( 
						weight.getDate(), 
						weight.getTime(), 
						weight.getValue());
			}
		}

		return csvWriter.toString();
	}
}
