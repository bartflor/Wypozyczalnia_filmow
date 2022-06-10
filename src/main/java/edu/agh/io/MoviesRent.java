package edu.agh.io;

import edu.agh.io.domain.MoviesSystem;
import edu.agh.io.domain.Ui;
import edu.agh.io.ui.Console;

import java.io.IOException;

public class MoviesRent {
	Ui ui = new Console();
	MoviesSystem system = new MoviesSystem(ui);
	
	public void run() throws IOException {
		system.run();
	}
}
