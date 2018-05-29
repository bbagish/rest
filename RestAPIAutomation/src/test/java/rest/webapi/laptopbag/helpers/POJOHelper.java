package rest.webapi.laptopbag.helpers;

import java.util.Arrays;

import rest.webapi.laptopbag.pojos.Features;
import rest.webapi.laptopbag.pojos.Laptop;

public class POJOHelper {
public static Laptop buildNewLaptop (String brand, String laptopName, String featuresOfLaptop) {
	int id=(int)(Math.random()*1000);
	
	Laptop laptop=new Laptop();
	laptop.setBrandName(brand);
	laptop.setLaptopName(laptopName);
	laptop.setId(id);
	
//	Features features = new Features();
//	features.setFeature(Arrays.asList(featuresOfLaptop.split(",")));
//	laptop.setFeatures(features);
	
	//Features features=new Features(Arrays.asList(featuresOfLaptop.split(",")));
	//One line code instead of 3 lines
	laptop.setFeatures(new Features(Arrays.asList(featuresOfLaptop.split(","))));
	return laptop;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
