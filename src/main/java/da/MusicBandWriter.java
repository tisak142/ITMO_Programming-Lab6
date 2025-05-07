package da;

import OrdinaryClasses.MusicBand;

import java.util.ArrayList;
import java.util.List;

public interface MusicBandWriter {
    void writeToFile(String filename, ArrayList<MusicBand> musicBands);
}