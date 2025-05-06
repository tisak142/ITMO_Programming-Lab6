import OrdinaryClasses.MusicBand;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
                    // 3. Преобразование XML-элемента в объект MusicBand с помощью MusicBandParser
                    MusicBand band = MusicBandParser.parse(element);
                    if (band != null) {
                        musicBands.add(band);
                    } else {
                        System.err.println("Error parsing music band at index " + i + ". Skipping to the next band.");
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }

        if(musicBands.isEmpty()){
            System.out.println("There is not a single valid group in the file.");
            return null;
        }
        return musicBands;
    }
}