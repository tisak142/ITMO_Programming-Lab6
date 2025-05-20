package commands;

import OrdinaryClasses.MusicBand;
import management.MusicBandConsoleCreator;
import parsers.MusicBandParser;
import management.Receiver;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class AddCommand extends ContextAwareCommand {
    private final Receiver receiver;
    private final MusicBandConsoleCreator creator;

    public AddCommand(Receiver receiver, MusicBandConsoleCreator creator) {
        this.receiver = receiver;
        this.creator = creator;
    }

    @Override
    public void execute(String... args) {
        try {
            MusicBand band = (context != null && context.isScriptMode())
                    ? parseFromScript()
                    : creator.createMusicBand();

            if (band != null) {
                receiver.add(band);
                System.out.println("Успешно добавлено: " + band.getName());
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private MusicBand parseFromScript() throws Exception {
        StringBuilder xml = new StringBuilder();
        String line;
        while ((line = context.readLine()) != null && !line.contains("</musicBand>")) {
            xml.append(line);
        }
        if (line != null) xml.append(line);

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml.toString())));
        return MusicBandParser.parse(doc.getDocumentElement());
    }

    @Override
    public String getName() {
        return "add";
    }
}