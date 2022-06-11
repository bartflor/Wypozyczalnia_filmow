package edu.agh.io.domain;

import java.io.IOException;
import java.util.List;

public interface Ui {
	void init() throws IOException;
	
	String getLogin() throws IOException;
	
	int clientMenu() throws IOException;
	
	void displayMovies(List<Movie> clientMovies);
	
	void displayPayments(List<Payment> paymentList);
	
	int chooseMovie();
	
	void playMovie(Movie movieId);
	
	void printMsg(String msg);
	
	void processPayment(double rentMovieCost);
	
	String getPassword() throws IOException;
}
