package management;

import OrdinaryClasses.MusicBand;
import parsers.MusicBandParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class FileLoader {

    private final String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<MusicBand> load() {
        ArrayList<MusicBand> musicBands = new ArrayList<>();
        int bandIndex = 0; // Добавим счетчик для индекса группы

        try {
            // 1. Чтение XML-файла
            File file = new File(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            // 2. Итерация по элементам <musicBand>
            NodeList nodeList = document.getElementsByTagName("musicBand");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    bandIndex = i; // Обновляем индекс группы
                    // 3. Преобразование XML-элемента в объект MusicBand с помощью parsers.MusicBandParser
                    MusicBand band = MusicBandParser.parse(element);
                    if (band != null) {
                        musicBands.add(band);
                    } else {
                        System.err.println("Error parsing music band at index " + (i + 1) + ". Skipping to the next band.");
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
        return musicBands;
    }
}