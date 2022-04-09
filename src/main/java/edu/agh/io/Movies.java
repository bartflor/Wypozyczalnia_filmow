package edu.agh.io;

import edu.agh.io.domain.MoviesSystem;
import edu.agh.io.domain.Ui;
import edu.agh.io.ui.Console;

public class Movies {
	Ui ui = new Console();
	MoviesSystem system = new MoviesSystem(ui);
	
	public void run(){
		system.run();
	}
}
