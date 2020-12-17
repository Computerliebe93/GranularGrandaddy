package sample;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/*
View class to handle settings and instantiations for GUI.
Returns parent object as start view,
which includes all the node children.
 */

public class View {
    Synthesizer model;
    Controller control;
    public View(Synthesizer model, Controller control){
        this.model = model;
        this.control = control;
        createAndConfigure();
    }

    private HBox header;
    private BorderPane startView;
    private GridPane grid;

    // Sample
    Label sampleLbl = new Label("Sample: ");
    Button selectSampleBtn = new Button("Select sample");
    TextArea samplePath = new TextArea();
    Button loadSampleBtn = new Button("Load sample");
    ToggleButton pauseToggleBtn = new ToggleButton("▶ / Ⅱ");

    // Pitch
    Label pitchNameLbl = new Label("Pitch");
    Label pitchValueLbl = new Label("0");
    Button pitchBtn = new Button("Set Pitch");
    Spinner<Float> pitchInput = new Spinner<>(0, 500, 0);

    // GrainSize
    Label grainSizeNameLbl = new Label("GrainSize");
    Label grainSizeValueLbl = new Label("0");
    Button grainSizeBtn = new Button("Set Grain");
    Spinner<Float> grainSizeInput = new Spinner<>(0, 500, 0);

    // GrainInterval
    Label grainIntervalNameLbl = new Label("GrainInterval");
    Label grainIntervalValueLbl = new Label("0");
    Button grainIntervalBtn = new Button("Set Grain Interval");
    Spinner<Float> grainIntervalInput = new Spinner<>(0, 500, 0);

    // Randomness
    Label randomnessNameLbl = new Label("Randomness");
    Label randomnessValueLbl = new Label("0");
    Button randomnessBtn = new Button("Set Randomness");
    Spinner<Float> randomnessInput = new Spinner<>(0, 500, 0);

    // Start point
    Label startNameLbl = new Label("Start");
    Label startValueLbl = new Label("0");
    Button startBtn = new Button("Set Start");
    Spinner<Float> startInput = new Spinner<>(0, 500, 0);

    // End point
    Label endNameLbl = new Label("End");
    Label endValueLbl = new Label("0");
    Button endBtn = new Button("Set End");
    Spinner<Float> endInput = new Spinner<>(0, 500, 0);

    // Spray
    Label sprayNameLbl = new Label("Spray");
    Label sprayValueLbl = new Label("0");
    Button sprayBtn = new Button("Set Spray");
    Spinner<Integer> sprayInput = new Spinner<>(0, 500, 0);

    // Loop types
    String loopTypes [] = {"Forwards", "Backwards", "Alternating", "Reset"};
    ComboBox<String> selectLoopComb = new ComboBox<>(FXCollections.observableArrayList(loopTypes));

    //Visualizer
    Slider slider = new Slider(0, 100, 50);
    Label sliderNameLbl = new Label("Progress bar");

    // Exit
    Button exitBtn = new Button("Exit");

    private void createAndConfigure(){
        header = new HBox();
        grid = new GridPane();
        startView = new BorderPane();
        startView.setTop(header);
        startView.setCenter(grid);
        grid.setHgap(5);
        grid.setVgap(5);

        // Sample
        header.getChildren().addAll(sampleLbl, selectSampleBtn, samplePath, loadSampleBtn, pauseToggleBtn);
        samplePath.setMaxHeight(2);
        samplePath.setMinWidth(90);

        // Pitch
        grid.add(pitchNameLbl, 2, 5);
        pitchInput.setMaxSize(60, 10);
        pitchInput.setEditable(true);
        grid.add(pitchInput, 2, 6);
        grid.add(pitchBtn, 2, 7);
        pitchValueLbl.setMinWidth(40);
        pitchValueLbl.setMaxWidth(40);
        grid.add(pitchValueLbl, 3, 6);

        // GrainSize
        grid.add(grainSizeNameLbl, 2, 8);
        grainSizeInput.setMaxSize(60, 20);
        grainSizeInput.setEditable(true);
        grid.add(grainSizeInput, 2, 9);
        grid.add(grainSizeBtn, 2, 10);
        grid.add(grainSizeValueLbl, 3, 9);
        grainSizeValueLbl.setMinWidth(40);
        grainSizeValueLbl.setMaxWidth(40);

        // GrainSize
        grid.add(grainIntervalNameLbl, 2, 11);
        grainIntervalInput.setMaxSize(60, 20);
        grainIntervalInput.setEditable(true);
        grid.add(grainIntervalInput, 2, 12);
        grid.add(grainIntervalBtn, 2, 13);
        grainIntervalValueLbl.setMinWidth(40);
        grainIntervalValueLbl.setMaxWidth(40);
        grid.add(grainIntervalValueLbl, 3, 12);

        // Randomness
        grid.add(randomnessNameLbl, 2, 14);
        randomnessInput.setMaxSize(60, 20);
        randomnessInput.setEditable(true);
        grid.add(randomnessInput, 2, 15);
        grid.add(randomnessBtn, 2, 16);
        randomnessValueLbl.setMinWidth(40);
        randomnessValueLbl.setMaxWidth(40);
        grid.add(randomnessValueLbl, 3, 15);

        // Start
        grid.add(startNameLbl, 5, 5);
        startInput.setMaxSize(60, 20);
        startInput.setEditable(true);
        grid.add(startInput, 5, 6);
        grid.add(startBtn, 5, 7);
        startValueLbl.setMinWidth(40);
        startValueLbl.setMaxWidth(40);
        grid.add(startValueLbl, 6, 6);

        // End
        grid.add(endNameLbl, 5, 8);
        endInput.setMaxSize(60, 20);
        endInput.setEditable(true);
        grid.add(endInput, 5, 9);
        grid.add(endBtn, 5, 10);
        endValueLbl.setMinWidth(40);
        endValueLbl.setMaxWidth(40);
        grid.add(endValueLbl, 6, 9);

        // Spray
        grid.add(sprayNameLbl, 5, 11);
        sprayInput.setMaxSize(60,20);
        sprayInput.setEditable(true);
        grid.add(sprayInput,5,12);
        grid.add(sprayBtn,5,13);
        grid.add(sprayValueLbl, 6,12);

        // Loop types
        selectLoopComb.setMinWidth(90);
        grid.add(selectLoopComb, 0, 3);
        selectLoopComb.getSelectionModel().selectFirst();

        // Exit
        grid.add(exitBtn, 21, 20);

        // Visualizer
        grid.add(slider, 21, 11);
        grid.add(sliderNameLbl,21, 10);

        //maxValue
        Label sliderMaxValueLbl  = new Label();
        sliderMaxValueLbl.textProperty().bind(
                Bindings.format(
                        "Max value: %.2f",
                        model.maxValueProperty()
                )
        );
        slider.maxProperty().bind(model.maxValueProperty());
        grid.add(sliderMaxValueLbl, 21,13);


        //Current value label
        Label sliderValueLbl  = new Label();
        sliderValueLbl.textProperty().bind(
                Bindings.format(
                        "Current value: %.2f",
                        model.currentValueProperty()
                )
        );
        slider.valueProperty().bindBidirectional(model.currentValueProperty());
        grid.add(sliderValueLbl, 21,12);
    }

    public Parent asParent() {
        return startView;
    }
}