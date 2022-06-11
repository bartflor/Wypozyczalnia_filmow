package edu.agh.io.ui;

import edu.agh.io.domain.Movie;
import edu.agh.io.domain.Payment;
import edu.agh.io.domain.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static edu.agh.io.domain.Ui.Mode.ADMIN;
import static edu.agh.io.domain.Ui.Mode.CLIENT;
import static java.lang.Integer.parseInt;

public class Console implements Ui {
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public Mode init() throws IOException {

    System.out.println("Wypozyczalnia filmow\n Zaloguj jako:\n1.Klient\n2.Admin");
    String mode = reader.readLine();
    if (mode.equals("2")) {
      return ADMIN;
    } else {
      return CLIENT;
    }
  }

  @Override
  public String getLogin() throws IOException {
    System.out.println("\n\tLogin:");
    String login = reader.readLine();

    System.out.println("\n\tPass:");
    String pass = reader.readLine();

    return login;
  }

  @Override
  public int clientMenu() throws IOException {
    System.out.println("\n-----------------------------------");
    System.out.println("\n----------Wybierz opcje:-----------");
    System.out.println("\n\t1. Twoje filmy");
    System.out.println("\n\t2. wypożycz film");
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
    System.out.println(paymentList);
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
    System.out.println("! :"+msg+" !");
  }
  
  private void adminMenu() {
    System.out.println("\n\tAdmin\n");
  }

}
