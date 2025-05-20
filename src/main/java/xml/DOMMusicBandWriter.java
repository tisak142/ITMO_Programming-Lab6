package xml;

import OrdinaryClasses.MusicBand;

import java.util.ArrayList;

public class DOMMusicBandWriter implements MusicBandWriter {
    private final DOMWriter domWriter;

    public DOMMusicBandWriter(DOMWriter domWriter) {
        this.domWriter = domWriter;
    }

    @Override
    public void writeToFile(String filename, ArrayList<MusicBand> musicBands) {
        domWriter.writeToFile(filename, musicBands);
    }
}