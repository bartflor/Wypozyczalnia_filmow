import edu.agh.io.domain.MoviesSystem;
import edu.agh.io.domain.Ui;
import edu.agh.io.ui.Console;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    Ui ui = new Console();
    MoviesSystem system = new MoviesSystem(ui);
    system.run();
  }
}
