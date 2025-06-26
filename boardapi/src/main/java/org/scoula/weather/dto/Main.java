package org.scoula.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Main{
	private Object temp;
	@JsonProperty("temp_min")
	private Object tempMin;
	private int grndLevel;
	private int humidity;
	private int pressure;
	private int seaLevel;
	private Object feelsLike;
	private Object tempMax;
}