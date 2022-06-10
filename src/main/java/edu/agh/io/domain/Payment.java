package edu.agh.io.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class Payment {
	int movieId;
	double cost;
}
