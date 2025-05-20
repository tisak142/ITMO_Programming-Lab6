package xml;

import OrdinaryClasses.MusicBand;

import java.util.ArrayList;

public interface MusicBandWriter {
    void writeToFile(String filename, ArrayList<MusicBand> musicBands);
}