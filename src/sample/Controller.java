package sample;

import javafx.application.Platform;
import javafx.stage.Window;
import java.io.File;

/*
Controller class for handling data received from GUI.
*/

public class Controller {
    Synthesizer model;

    public Controller(Synthesizer model) {
        this.model = model;
    }

    public void setView(View view) {
        // Exit button
        view.exitBtn.setOnAction(e -> Platform.exit());
        view.exitBtn.setOnAction(e -> System.exit(0));

        // Play / pause sample toggle button
        view.pauseToggleBtn.setOnAction(e ->{
            if (view.pauseToggleBtn.isSelected()){
                model.pauseSample();
            }else{
                model.playSample();
            }
        });

        // Pitch button
        view.pitchBtn.setOnAction(e -> {
            if(view.pitchInput.getText().isEmpty()) {
                System.out.println("Please write a number");
                view.pitchInput.setText("");
            } else if(!view.pitchInput.getText().matches("\\d+")){
                System.out.println("Please write a positive number");
            } else if(view.pitchInput.getText().matches("\\d+")) {
                model.updateGUI(view.pitchValueLbl, view.pitchInput, 1);
                model.setPitch(Float.parseFloat(view.pitchValueLbl.getText()));
            }
        });

        // Set grain size button
        view.grainSizeBtn.setOnAction(e -> {
            if(view.pitchInput.getText().isEmpty()) {
                System.out.println("Please write a number");
                view.pitchInput.setText("");
            } else if(!view.pitchInput.getText().matches("\\d+")){
                System.out.println("Please write a positive number");
            } else if(view.pitchInput.getText().matches("\\d+")) {
                model.updateGUI(view.grainSizeValueLbl, view.grainSizeInput, 2);
                model.setGrainSize(Float.parseFloat(view.grainSizeValueLbl.getText()));
            }
        });

        // Set grain interval
        view.grainIntervalBtn.setOnAction(e -> {
            if(view.pitchInput.getText().isEmpty()) {
                System.out.println("Please write a number");
                view.pitchInput.setText("");
            } else if(!view.pitchInput.getText().matches("\\d+")){
                System.out.println("Please write a positive number");
            } else if(view.pitchInput.getText().matches("\\d+")) {
                model.updateGUI(view.grainIntervalValueLbl, view.grainIntervalInput, 3);
                model.setGrainInterval(Float.parseFloat(view.grainIntervalValueLbl.getText()));
            }
        });

        // Set randomness
        view.randomnessBtn.setOnAction(e -> {
            if(view.pitchInput.getText().isEmpty()) {
                System.out.println("Please write a number");
                view.pitchInput.setText("");
            } else if(!view.pitchInput.getText().matches("\\d+")){
                System.out.println("Please write a positive number");
            } else if(view.pitchInput.getText().matches("\\d+")) {
                model.updateGUI(view.randomnessValueLbl, view.randomnessInput, 4);
                model.setRandomness(Float.parseFloat(view.randomnessValueLbl.getText()));
            }
        });

        // Set start point
        view.startBtn.setOnAction(e -> {
            if(view.pitchInput.getText().isEmpty()) {
                System.out.println("Please write a number");
                view.pitchInput.setText("");
            } else if(!view.pitchInput.getText().matches("\\d+")){
                System.out.println("Please write a positive number");
            } else if(view.pitchInput.getText().matches("\\d+")) {
                model.updateGUI(view.startValueLbl, view.startInput, 5);
                model.setStart(Float.parseFloat(view.startValueLbl.getText()));
            }
        });

        // Set end point
        view.endBtn.setOnAction(e -> {
            if(view.pitchInput.getText().isEmpty()) {
                System.out.println("Please write a number");
                view.pitchInput.setText("");
            } else if(!view.pitchInput.getText().matches("\\d+")){
                System.out.println("Please write a positive number");
            } else if(view.pitchInput.getText().matches("\\d+")) {
                model.updateGUI(view.endValueLbl, view.endInput, 6);
                model.setEnd(Float.parseFloat(view.endValueLbl.getText()));
            }
        });

        // Loop type
        view.selectLoopComb.setOnAction(e -> {
            switch(view.selectLoopComb.getValue()) {
                case "Forwards":
                    model.setLoopForwards();
                    System.out.println("Forwards");
                    break;
                case "Backwards":
                    model.setLoopBackwards();
                    System.out.println("Backwards");
                    break;
                case "Alternating":
                    model.setLoopAlternating();
                    System.out.println("Alternating");
                    break;
                case "Reset":
                    model.setReset();
                    System.out.println("Reset");
                    break;
            }
        });

        // Select sample button
        view.selectSampleBtn.setOnAction( e ->{
            Window primaryStage = null;
            File selectedFile = model.selectSample().showOpenDialog(primaryStage);
            if(selectedFile != null) {
                model.setSample(selectedFile);
                view.samplePath.setText(model.getSample());
                model.updateAudioContext();
            }
        });

        // Load sample button
        view.loadSampleBtn.setOnAction(e ->{
            if(model.samplePath != null) {
                model.mountGspSample();
            }
            else{
                System.out.println("Please select a sample in 16 bit 44.1kHz");
            }
        });
    }
}