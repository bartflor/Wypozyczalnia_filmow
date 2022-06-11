package edu.agh.io.ui;

import edu.agh.io.domain.Movie;
import edu.agh.io.domain.Payment;
import edu.agh.io.domain.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Console implements Ui {
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public void init(){
    System.out.println("Wypozyczalnia filmow\n Zaloguj się:");
  }

  @Override
  public String getLogin() throws IOException {
    System.out.println("\n\tLogin:");
    String login = reader.readLine();
    return login;
  }
  
  @Override
  public String getPassword() throws IOException {
    System.out.println("\n\tPass:");
    String pass = reader.readLine();
    return pass;
  }
  
  @Override
  public int clientMenu() throws IOException {
    System.out.println("\n-----------------------------------");
    System.out.println("\n----------Wybierz opcje:-----------");
    System.out.println("\n\t1. Twoje filmy - oglądaj");
    System.out.println("\n\t2. Wypożycz film");
    System.out.println("\n\t3. Historia płatności");
    System.out.println("\n\t4. Wyjście");
    try {
	    String option = reader.readLine();
	    return parseInt(option);
    } catch (Exception e) {
	    System.out.println("\nBłędna opcja");
		return clientMenu();
    }
  }
  
  @Override
  public void displayMovies(List<Movie> Movies) {
    for (Movie movie: Movies)
    System.out.println("\t" + movie.getId() + " " + movie.getTitle()+" "+movie.getCost());
    
  }
  
  @Override
  public void displayPayments(List<Payment> paymentList) {
    System.out.println("\t---Twoje płatności--------");
    for (Payment payment: paymentList)
      System.out.println("\t" + payment.getDate() + ", " + payment.getCost()+", Film id:" + payment.getMovieId()+" -> "+payment.getType());
  }
  
  @Override
  public int chooseMovie() {
    System.out.println("\n-----------------------------------");
    System.out.println("\n----------Wybierz film:-----------");
    try {
      String movie = reader.readLine();
      return parseInt(movie);
    } catch (Exception e) {
      System.out.println("\nBłędna opcja");
      return chooseMovie();
    }
  }
  
  @Override
  public void playMovie(Movie movie) {
    System.out.println("~~~~~~~STREAM:"+movie.getTitle()+"~~~~~~~~~");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
  }
  
  @Override
  public void printMsg(String msg) {
    System.out.println("\t-"+msg+"-");
  }
  
  @Override
  public void processPayment(double rentMovieCost) {
    System.out.println("\tPotwierdz płatność w serwisie swojego banku");
    System.out.println("\tprzelew na kwote: "+rentMovieCost);
    System.out.println("\t _________________________________");
  }
  
}
