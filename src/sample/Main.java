package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
The Grandaddy is a granular synthesizer
that utilises the http://www.beadsproject.net/ library.
*/

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) {
        Synthesizer synthesizer = new Synthesizer();
        MidiKeyboard midiKeyboard = new MidiKeyboard(synthesizer);
        Controller controller = new Controller(synthesizer);
        View view = new View(synthesizer, controller);
        controller.setView(view);
        synthesizer.setController(controller);
        synthesizer.setView(view);
        synthesizer.setMidiKeyboard(midiKeyboard);

        primaryStage.setTitle("Granular Grandaddy");
        primaryStage.setScene(new Scene(view.asParent(), 800, 600));
        primaryStage.show();

        //new  thread for model
        Thread thread = new Thread(synthesizer);
        thread.start();
    }

    public static void main (String[]args) {launch (args);}{
    }
    @Override
    public void stop() {
        System.exit(0);
    }
}