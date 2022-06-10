package edu.agh.io.domain;

import edu.agh.io.domain.Ui.Mode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static edu.agh.io.domain.Ui.Mode.ADMIN;

public class MoviesSystem {
  Ui ui;
  List<Movie> movieList;
  List<Client> clients;

  public MoviesSystem(Ui ui) {
    this.ui = ui;
    movieList = new ArrayList<>();
    movieList.add(Movie.builder().id(0).title("Władca pierścieni").cost(100).build());
    movieList.add(Movie.builder().id(1).title("Harry Potter").cost(23).build());
    movieList.add(Movie.builder().id(2).title("Szczęki 2").cost(41).build());
    movieList.add(Movie.builder().id(3).title("Rocky 4").cost(244).build());
    movieList.add(Movie.builder().id(4).title("Thor").cost(5).build());

    clients = new ArrayList<>();
    clients.add(
        Client.builder()
            .name("Jan")
            .email("Jan@mail.com")
            .password("pass")
            .ClientMovies(List.of(1, 3))
            .build());
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
          break;
        case 2:
          ui.displayMovies(movieList);
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

  private List<Movie> getClientMovies(Client client) {
    return movieList.stream()
        .filter(movie -> client.ClientMovies.contains(movie.id))
        .collect(Collectors.toList());
  }

  private Client findClient(String login) {
    return clients.stream()
        .filter(client -> client.getName().equals(login))
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
