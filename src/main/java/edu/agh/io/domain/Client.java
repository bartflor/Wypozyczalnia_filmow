package edu.agh.io.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Client {
  private String email;
  private String password;
  private List<Integer> ClientMovies;
  private List<Payment> paymentList;
}
