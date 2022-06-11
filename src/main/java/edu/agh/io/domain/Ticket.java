package edu.agh.io.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Ticket {
	private int tickerId;
	private Client client;
	private Admin agent;
	LocalDate date;
	String status;
	
	public void changeOwner(Admin admin){
		agent = admin;
	}
	
	public void closeTicket(){
		this.status = "Closed";
	}
}
