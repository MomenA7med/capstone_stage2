package com.example.momen.capstone_stage2.model;

import java.util.List;

public class Response{
	private int responseCode;
	private List<ResultsItem> results;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"response_code = '" + responseCode + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}