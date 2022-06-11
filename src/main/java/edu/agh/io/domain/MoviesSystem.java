package edu.agh.io.domain;

import edu.agh.io.domain.Ui.Mode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static edu.agh.io.domain.Ui.Mode.ADMIN;
import static java.util.stream.Collectors.toList;

public class MoviesSystem {
  Ui ui;
  List<Movie> movieList;
  List<Client> clients;
  List<Ticket> ticketList;

  public MoviesSystem(Ui ui) {
    this.ui = ui;
    movieList = new ArrayList<>();
    movieList.add(Movie.builder().id(0).title("Władca pierścieni").cost(100).build());
    movieList.add(Movie.builder().id(1).title("Harry Potter").cost(23).build());
    movieList.add(Movie.builder().id(2).title("Szczęki 2").cost(41).build());
    movieList.add(Movie.builder().id(3).title("Rocky 4").cost(244).build());
    movieList.add(Movie.builder().id(4).title("Thor").cost(5).build());

    clients = new ArrayList<>();
    ArrayList<Integer> clientMovies = new ArrayList<>();
    clientMovies.add(1);
    clientMovies.add(3);
    clients.add(
        Client.builder().email("Jan@mail.com").ClientMovies(clientMovies).password("pass").build());
  }

  public void run() throws IOException {
    Mode runningMode = ui.init();
    if (runningMode == ADMIN) {
      runAdminMode();
    } else {
      runClientMode();
    }
  }

  private void runClientMode() throws IOException {
    Client client = findClient(login());
    boolean exit = false;
    while (!exit) {
      switch (ui.clientMenu()) {
        case 1:
          ui.displayMovies(getClientMovies(client));
          int watchMovieId = ui.chooseMovie();
          Movie movie = findMovie(watchMovieId);
          if (client.getClientMovies().contains(movie.getId())) {
            ui.playMovie(movie);
          } else {
            ui.printMsg("Nie poprawny numer filmu");
          }
          break;
        case 2:
          ui.displayMovies(movieList);
          int rentMovieId = ui.chooseMovie();
          Movie rentMovie = findMovie(rentMovieId);
          client.getClientMovies().add(rentMovieId);
          ui.printMsg("Wypożyczono film: " + rentMovie);
          break;
        case 3:
          ui.displayPayments(client.getPaymentList());
          break;
        default:
          exit = true;
          break;
      }
    }
  }

  private Movie findMovie(int movieId) {
    return movieList.stream().filter(movie -> movie.getId() == movieId).collect(toList()).get(0);
  }

  private List<Movie> getClientMovies(Client client) {
    return movieList.stream()
        .filter(movie -> client.getClientMovies().contains(movie.id))
        .collect(toList());
  }

  private Client findClient(String login) {
    return clients.stream()
        .filter(client -> client.getEmail().equals(login))
        .findAny()
        .orElse(clients.get(0));
  }

  private String login() throws IOException {
    return ui.getLogin();
  }

  private void runAdminMode() throws IOException {
    login();
  }
}
