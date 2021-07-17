package com.salaryspringmvc.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {
	
	private InputStream inputStream;
	
	public YamlReader(String filePath) {
		super();
		try {
			inputStream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public Map<String, Object> readFile(){
		Yaml yaml = new Yaml();
		return yaml.load(inputStream);
	}
	
	

}
