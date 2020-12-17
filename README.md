# The Grandaddy is a granular synthesizer that utilises the http://www.beadsproject.net/ library

 

## Usage 
Download the beads 3.2 jar here: https://search.maven.org/search?q=g:net.beadsproject
Add the beads-3.2.jar library to the external libraries appendix.

 

Run the program. Watch your volume! The grandaddy can create sounds with a very high volume, so be careful when loading samples and while operating.

 

Choose a sample and press play. Make sure that your sample is a .wav in 16 bit 44.1kHz. By default the sample Sinus.wav is included in the root folder.

 

Press the exit button when finished.

 

## Connecting a MIDI device
The Granddaddy is programmed to receiving MIDI data from a AKAI MPK mini midi keyboard. If you want to connect your own midi keyboard/controller, you need to calibrate the MidiInputReciever aMsg[] in the midiKeyboard.java class.
