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

    private HBox Header;
    private BorderPane StartView;
    private GridPane Grid;

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
        Header = new HBox();
        Grid = new GridPane();
        StartView = new BorderPane();
        StartView.setTop(Header);
        StartView.setCenter(Grid);
        Grid.setHgap(5);
        Grid.setVgap(5);

        // Sample
        Header.getChildren().addAll(sampleLbl, selectSampleBtn, samplePath, loadSampleBtn, pauseToggleBtn);
        samplePath.setMaxHeight(2);
        samplePath.setMinWidth(90);

        // Pitch
        Grid.add(pitchNameLbl, 2, 5);
        pitchInput.setMaxSize(60, 10);
        pitchInput.setEditable(true);
        Grid.add(pitchInput, 2, 6);
        Grid.add(pitchBtn, 2, 7);
        pitchValueLbl.setMinWidth(40);
        pitchValueLbl.setMaxWidth(40);
        Grid.add(pitchValueLbl, 3, 6);

        // GrainSize
        Grid.add(grainSizeNameLbl, 2, 8);
        grainSizeInput.setMaxSize(60, 20);
        grainSizeInput.setEditable(true);
        Grid.add(grainSizeInput, 2, 9);
        Grid.add(grainSizeBtn, 2, 10);
        Grid.add(grainSizeValueLbl, 3, 9);
        grainSizeValueLbl.setMinWidth(40);
        grainSizeValueLbl.setMaxWidth(40);

        // GrainSize
        Grid.add(grainIntervalNameLbl, 2, 11);
        grainIntervalInput.setMaxSize(60, 20);
        grainIntervalInput.setEditable(true);
        Grid.add(grainIntervalInput, 2, 12);
        Grid.add(grainIntervalBtn, 2, 13);
        grainIntervalValueLbl.setMinWidth(40);
        grainIntervalValueLbl.setMaxWidth(40);
        Grid.add(grainIntervalValueLbl, 3, 12);

        // Randomness
        Grid.add(randomnessNameLbl, 2, 14);
        randomnessInput.setMaxSize(60, 20);
        randomnessInput.setEditable(true);
        Grid.add(randomnessInput, 2, 15);
        Grid.add(randomnessBtn, 2, 16);
        randomnessValueLbl.setMinWidth(40);
        randomnessValueLbl.setMaxWidth(40);
        Grid.add(randomnessValueLbl, 3, 15);

        // Start
        Grid.add(startNameLbl, 5, 5);
        startInput.setMaxSize(60, 20);
        startInput.setEditable(true);
        Grid.add(startInput, 5, 6);
        Grid.add(startBtn, 5, 7);
        startValueLbl.setMinWidth(40);
        startValueLbl.setMaxWidth(40);
        Grid.add(startValueLbl, 6, 6);

        // End
        Grid.add(endNameLbl, 5, 8);
        endInput.setMaxSize(60, 20);
        endInput.setEditable(true);
        Grid.add(endInput, 5, 9);
        Grid.add(endBtn, 5, 10);
        endValueLbl.setMinWidth(40);
        endValueLbl.setMaxWidth(40);
        Grid.add(endValueLbl, 6, 9);

        // Spray
        Grid.add(sprayNameLbl, 5, 11);
        sprayInput.setMaxSize(60,20);
        sprayInput.setEditable(true);
        Grid.add(sprayInput,5,12);
        Grid.add(sprayBtn,5,13);
        Grid.add(sprayValueLbl, 6,12);

        // Loop types
        selectLoopComb.setMinWidth(90);
        Grid.add(selectLoopComb, 0, 3);
        selectLoopComb.getSelectionModel().selectFirst();

        // Exit
        Grid.add(exitBtn, 21, 20);

        // Visualizer
        Grid.add(slider, 21, 11);
        Grid.add(sliderNameLbl,21, 10);

        //maxValue
        Label sliderMaxValueLbl  = new Label();
        sliderMaxValueLbl.textProperty().bind(
                Bindings.format(
                        "Max value: %.2f",
                        model.maxValueProperty()
                )
        );
        slider.maxProperty().bind(model.maxValueProperty());
        Grid.add(sliderMaxValueLbl, 21,13);


        //Current value label
        Label sliderValueLbl  = new Label();
        sliderValueLbl.textProperty().bind(
                Bindings.format(
                        "Current value: %.2f",
                        model.currentValueProperty()
                )
        );
        slider.valueProperty().bindBidirectional(model.currentValueProperty());
        Grid.add(sliderValueLbl, 21,12);
    }

    public Parent asParent() {
        return StartView;
    }
}