package sample;

import javax.sound.midi.*;
import java.util.List;

/*
This class checks for connected MIDI devices.
If any, the transmitters are sent to the Synth class.
The MIDI class is programmed to transfer MIDI messages from
an AKAI mpk mini MIDI keyboard. To set up another controller,
you need to change the MIDI transmitters in line 17 to your specific needs.
*/

public class MidiKeyboard {
    MidiDevice device;

    // MIDI transmitters
    final int knobMidi = -80;
    final int padMidi = -64;
    final int keysMidi = -112;

    public MidiKeyboard(Synthesizer synthesizer) {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                System.out.println(infos[i]);
                List<Transmitter> transmitters = device.getTransmitters();
                for (int j = 0; j < transmitters.size(); j++) {
                    transmitters.get(j).setReceiver(
                            new MidiInputReceiver(device.getDeviceInfo().toString(), synthesizer)
                    );
                }
                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString(), synthesizer));
                device.open();
                System.out.println(device.getDeviceInfo() + " Was Opened");

            } catch (MidiUnavailableException e) {
            }
        }
    }

    public class MidiInputReceiver implements Receiver {
        public String name;
        private Synthesizer synthesizer;

        public MidiInputReceiver(String name, Synthesizer synthesizer) {
            this.name = name;
            this.synthesizer = synthesizer;
        }

        public void send(MidiMessage msg, long timeStamp) {
            byte[] aMsg = msg.getMessage();
            for (int i = 0; i < msg.getLength(); i++) {
                //
                if (aMsg[0] == knobMidi) {
                    synthesizer.receiveKnobMidi(aMsg);
                }
                if (aMsg[0] == padMidi) {
                    synthesizer.receivePadMidi(aMsg);
                }
                if (aMsg[0] == keysMidi) {
                    synthesizer.receiveKeysMidi(aMsg);
                }
                // This println prints the the raw midi message.
                // System.out.println(aMsg[i]);
            }
        }

        public void close() {
        }
    }
}