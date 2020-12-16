package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.stage.FileChooser;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.ugens.GranularSamplePlayer;
import net.beadsproject.beads.ugens.SamplePlayer;
import net.beadsproject.beads.ugens.Static;
import java.io.File;
import java.io.IOException;

/*
Synthesizer class for handling the Beads library.
Handles messages the MidiKeyboard class
which manipulates the sound.
*/

public class Synthesizer {
    AudioContext ac = new AudioContext();
    GranularSamplePlayer gsp;
    MidiKeyboard midiKeyboard;
    Controller controller;
    View view;

    // Midi arrays
    private float[] knobValues = new float[9];
    private int[] padValues = new int[] {0};
    private int[] keyValues = new int[] {0};
    public String samplePath = null;
    boolean sampleReady = false;

    // Knob offsets
    final double pitchOffset = 0.1 / 6.35;
    final double sizeOffset = 0.7;
    final double intervalOffset = 4;
    double spray;
    final double sprayOffset = 10000;
    final double loopOffset = 100;

    public Synthesizer() {
        Sample sourceSample = null;

        try {
            sourceSample = new Sample("Sinus.wav");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        gsp = new GranularSamplePlayer(ac, sourceSample);
        ac.out.addInput(gsp);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void setView(View view){
        this.view = view;
    }

    public void setMidiKeyboard(MidiKeyboard midiKeyboard){
        this.midiKeyboard = midiKeyboard;
    }

    // Knob midi
    public void receiveKnobMidi(byte[] a) {
        // Receives byte[] from midi keyboard and allocated arrays to midi array.
        if (a[1] > 0 && a[1] <= knobValues.length) {
            knobValues[a[1]] = a[2];
            System.out.println("Knob " + a[1] + " value is set to " + knobValues[a[1]]);
        } else {
            System.out.println("Something went wrong");
        }
        if (a[1] == 1){
            setPitch(a[2]);
        }
        else if (a[1] == 2){
            setGrainSize(a[2]);
        }
        else if (a[1] == 3){
            setGrainInterval(a[2]);
        }
        else if (a[1] == 4){
            setRandomness(a[2]);
        }
        else if (a[1] == 5){
            setStart(a[2]);
        }
        else if (a[1] == 6){
            setEnd(a[2]);
        }
        else if (a[1] == 7){
            setSpray(a[2]);
        }

        // Updates the view labels from the entered values.
        Platform.runLater(()-> {
            view.pitchValueLbl.setText(String.valueOf(getKnobValue(1)));
            view.grainSizeValueLbl.setText(String.valueOf(getKnobValue(2)));
            view.grainIntervalValueLbl.setText(String.valueOf(getKnobValue(3)));
            view.randomnessValueLbl.setText(String.valueOf(getKnobValue(4)));
            view.startValueLbl.setText(String.valueOf(getKnobValue(5)));
            view.endValueLbl.setText(String.valueOf(getKnobValue(6)));
            view.sprayValueLbl.setText(String.valueOf(getKnobValue(7)));
        });
    }

    public float getKnobValue(int knobTransmitter) {
        if (knobTransmitter > 0 && knobTransmitter <= knobValues.length) {
            return knobValues[knobTransmitter];
        } else {
            return 0;
        }
    }

    public void setKnobValue(int knobTransmitter, int value){
        knobValues[knobTransmitter] = value;
    }

    // Pad midi
    public void receivePadMidi(byte[] a) {
        // Receives byte[] from midi keyboard and allocated arrays to midi array.
        if (a[1] >= 0 && a[1] < 8) {
            System.out.println(a[1]);
            padValues[0] = a[1];
            System.out.println("Active pad is " + padValues[0]);
        }
        if (a[1] == 0) {
            setLoopForwards();
        }
        if (a[1] == 1) {
            setLoopBackwards();
        }
        if (a[1] == 2) {
            setLoopAlternating();
        }
        if (a[1] == 3) {
            setReset();
        }
    }

    // Keys midi
    public void receiveKeysMidi(byte[] a) {
        keyValues[0] = a[1];
        System.out.println("Key value is set to " + a[1]);
        setPitch(a[1]);
    }

    // Pitch
    public void setPitch(float f) {
        gsp.setPitch(new Static((float) ((f) * (pitchOffset))));
        System.out.println(spray);
    }

    // Grain Size
    public void setGrainSize(float f){
        gsp.setGrainSize(new Static((float)((f) * (sizeOffset))));
    }

    // Grain Interval
    public void setGrainInterval(float f){
        gsp.setGrainInterval(new Static((float)((f) * intervalOffset)));
    }

    // Randomness
    public void setRandomness(float f){
        gsp.setRandomness( new Static(f));
    }

    // Start point
    public void setStart(float f) {

        //gsp.setLoopStart(new Static( (f)*100));
        if (getKnobValue(5) >= getKnobValue(6)){
            setKnobValue(6, (int)f);
            gsp.setLoopStart(new Static((float) ((f) * (loopOffset))));
        }
        else{
            gsp.setLoopStart(new Static((float)((f) * loopOffset)));
        }
    }

    // End
    public void setEnd(float f) {
        //gsp.setLoopEnd(new Static((f)*100));

        if(getKnobValue(6) <= getKnobValue(5)){
            setKnobValue(5, (int)f);
            gsp.setLoopStart(new Static((float) ((f) * (loopOffset))));
        }
        else{
            gsp.setLoopEnd(new Static((float)((f) * loopOffset)));
        }
    }

    // Spray
    public void setSpray(float f){
        if (getKnobValue(7) > 0) {
            System.out.println("Spray through if, is set to: " + spray);
        }
    }

    // Loop forwards
    public void setLoopForwards(){
        gsp.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);
        Platform.runLater(() -> {
            view.selectLoopComb.setValue("Forwards");
        });
    }

    // Loop backwards
    public void setLoopBackwards(){
        gsp.setLoopType(SamplePlayer.LoopType.LOOP_BACKWARDS);
        Platform.runLater(() -> {
            view.selectLoopComb.setValue("Backwards");
        });
    }

    // Loop alternating
    public void setLoopAlternating(){
        gsp.setLoopType(SamplePlayer.LoopType.LOOP_ALTERNATING);
        Platform.runLater(() -> {
            view.selectLoopComb.setValue("Alternating");
        });
    }

    // Set reset
    public void setReset(){
        gsp.reset();
        Platform.runLater(() -> {
            view.selectLoopComb.setValue("Reset");
        });
    }

    // Update View
    public void updateGUI(Label label, Spinner text, int knob){
        if(text != null) {
            if ((Float.parseFloat(text.getValue().toString())) >= 0) {
                try {
                    label.setText(String.valueOf(text.getValue()));
                    setKnobValue(knob, ((Integer) text.getValue()));
                } catch(NumberFormatException | NullPointerException n) {
                    System.out.println("Please enter an integer number");
                }
            }
        }
        else{
            System.out.println("Please enter something valid");
        }
    }

    // Sample select
    public FileChooser selectSample(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.wav")
        );
        return fileChooser;
    }

    public String setSample(File file){
        String path = null;
        try {
            path = file.getCanonicalPath();
            samplePath = path;
            sampleReady = true;
        } catch (IOException | NullPointerException e) {
            System.out.println("Sample not selected");
        }
        System.out.println(path);
        return path;
    }

    public String getSample(){
        return samplePath;
    }

    public GranularSamplePlayer mountGspSample(){

        Sample sourceSample = null;
        try {
            sourceSample = new Sample(getSample());
            System.out.println("Sample was set to: " + getSample());
            sampleReady = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            sampleReady = false;
        }
        this.gsp.setSample(sourceSample);
        gsp.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);
        ac.out.clearInputConnections();
        ac.out.addInput(gsp);
        ac.start();
        return gsp;
    }

    // Play / pause
    public void pauseSample(){
        gsp.pause(true);
    }

    public void playSample(){
        gsp.pause(false);
    }
}