package edu.agh.io.domain;

public class MoviesSystem {
	Ui ui;
	
	public MoviesSystem(Ui ui) {
		this.ui = ui;
	}
	
	public void run(){
		ui.init();
	}
}
