package edu.agh.io.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Admin {
	private String mail;
	private String password;
	private List<Ticket> TicketListForMe;
	
	public List<Ticket> showTicketListForMe(){
		return TicketListForMe;
	}
	
	public void logout(){
		//TODO
	}
}
