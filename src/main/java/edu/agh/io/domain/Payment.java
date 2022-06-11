package edu.agh.io.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Builder
@Data
public class Payment {
	int movieId;
	double cost;
	LocalDate date;
	String type;
}
