package edu.agh.io.domain;

import java.io.IOException;
import java.util.List;

public interface Ui {
	Mode init() throws IOException;
	
	String getLogin() throws IOException;
	
	int clientMenu() throws IOException;
	
	void displayMovies(List<Movie> clientMovies);
	
	void displayPayments(List<Payment> paymentList);
	
	enum Mode{
		CLIENT, ADMIN;
	}
}
