package edu.agh.io.domain;

import edu.agh.io.repository.Repository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class MoviesSystem {
  Ui ui;
  Repository repository;

  public MoviesSystem(Ui ui) {
    this.ui = ui;
    repository = new Repository();
  }

  public void run() throws IOException {
    ui.init();
    runClientMode();
  }

  private void runClientMode() throws IOException {
    Client client = login();
    while (Objects.isNull(client)){
      ui.printMsg("Błąd logowania - spróbuj ponownie");
      client = login();
    }
    boolean exit = false;
    while (!exit) {
      switch (ui.clientMenu()) {
        case 1:
          watchMovie(client);
          break;
        case 2:
          processMovieRent(client);
          break;
        case 3:
          ui.displayPayments(client.getPaymentList());
          break;
        default:
          exit = true;
          ui.printMsg("Dziękujemy za skorzystanie z naszej wypożyczalni.");
          break;
      }
    }
  }

  private void watchMovie(Client client) {
    ui.displayMovies(getClientMovies(client));
    int watchMovieId = ui.chooseMovie();
    Optional<Movie> movie = findMovie(watchMovieId);
    if (movie.isPresent() && client.getClientMovies().contains(movie.get().getId())) {
      ui.playMovie(movie.get());
    } else {
      ui.printMsg("Nie poprawny numer filmu");
    }
  }

  private void processMovieRent(Client client) {
    List<Movie> movesToRentForClient = getMovesToRentForClient(client);
    if(movesToRentForClient.isEmpty()){
      ui.printMsg("Nie mamy nowych filmów dla Ciebie");
    } else {
      ui.displayMovies(movesToRentForClient);
      int rentMovieId = ui.chooseMovie();
      if (movesToRentForClient.stream().map(Movie::getId).anyMatch(id -> id == rentMovieId)) {
        processMovieRent(client, rentMovieId);
      } else {
        ui.printMsg("Nie poprawny numer filmu");
      }
    }
  }
  
  private void processMovieRent(Client client, int rentMovieId) {
    Optional<Movie> rentMovie = findMovie(rentMovieId);
    if (rentMovie.isPresent()) {
      double rentMovieCost = rentMovie.get().getCost();
      ui.processPayment(rentMovieCost);
      if (executePayment()) {
        client.getClientMovies().add(rentMovieId);
        client
            .getPaymentList()
            .add(
                Payment.builder()
                    .cost(rentMovieCost)
                    .date(LocalDate.now())
                    .movieId(rentMovieId)
                    .type("APPROVED")
                    .build());
        ui.printMsg("Wypożyczono film: " + rentMovie);
      } else {
        ui.printMsg("Błąd weryfikacji płatności");
      }
    } else {
      ui.printMsg("Błędny numer filmu");
    }
  }
  
  private List<Movie> getMovesToRentForClient(Client client) {
    return repository.getMovieList().stream()
        .filter(m -> !client.getClientMovies().contains(m.getId()))
        .collect(toList());
  }

  private boolean executePayment() {
    // External provider mock
    System.out.println("\t---EXTERNAL PROVIDER PAYMENT CONFIRM---");
    return true;
  }

  private Optional<Movie> findMovie(int movieId) {
    return repository.getMovieList().stream()
        .filter(movie -> movie.getId() == movieId)
        .findAny();
  }

  private List<Movie> getClientMovies(Client client) {
    return repository.getMovieList().stream()
        .filter(movie -> client.getClientMovies().contains(movie.id))
        .collect(toList());
  }

  private Optional<Client> findClient(String login) {
    return repository.getClients().stream()
        .filter(client -> client.getEmail().equals(login))
        .findAny();
  }

  private Client login() throws IOException {
    Optional<Client> client = findClient(ui.getLogin());
    return client.isPresent() && client.get().getPassword().equals(ui.getPassword())
            ? client.get()
            : null;
  }
}
