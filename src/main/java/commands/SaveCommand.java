package commands;

import management.*;
import xml.DOMMusicBandWriter;
import xml.DOMWriter;
import xml.MusicBandWriter;
import xml.MusicBandXmlConverter;

public class SaveCommand implements Command {
    private final Receiver receiver;
    private final MusicBandWriter writer; // Абстракция записи
    private final String filename;

    public SaveCommand(Receiver receiver, String filename) {
        this.receiver = receiver;
        this.writer = new DOMMusicBandWriter(new DOMWriter(new MusicBandXmlConverter()));
        this.filename = filename;
    }

    @Override
    public void execute(String... args) {
        if (args.length > 0) {
            System.err.println("Only one argument is allowed");
            return;
        }
        writer.writeToFile(filename, receiver.getBands());
    }

    @Override
    public String getName() {
        return "save";
    }
}