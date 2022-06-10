package edu.agh.io.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class Movie {
	int id;
	String title;
	double cost;
}
