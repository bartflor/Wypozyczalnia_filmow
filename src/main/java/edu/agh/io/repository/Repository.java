package edu.agh.io.repository;

import edu.agh.io.domain.Client;
import edu.agh.io.domain.Movie;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Repository {
	
	List<Movie> movieList;
	List<Client> clients;

	public Repository(){
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
}
