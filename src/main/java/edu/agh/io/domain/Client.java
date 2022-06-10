package edu.agh.io.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Client {
  String name;
  String email;
  String password;
  List<Integer> ClientMovies;
  List<Payment> paymentList;
}
