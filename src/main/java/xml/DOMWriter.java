package xml;

import OrdinaryClasses.MusicBand;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Класс DOMWriter отвечает за запись данных коллекции MusicBand в XML-файл.
 * <p>
 * Использует DOM (Document Object Model) для создания XML-документа и записи его в файл.
 * </p>
 */
public class DOMWriter {

    private final XmlConverter xmlConverter;

    public DOMWriter(XmlConverter xmlConverter) {
        this.xmlConverter = xmlConverter;
    }

    /**
     * Записывает коллекцию MusicBand в XML-файл.
     *
     * @param filename имя файла для записи
     * @param musicBands коллекция объектов MusicBand
     */
    public void writeToFile(String filename, ArrayList<MusicBand> musicBands) {
        try {
            // Создаем новый XML-документ
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Создаем корневой элемент
            Element rootElement = document.createElement("musicBands");
            document.appendChild(rootElement);

            // Добавляем каждый элемент коллекции в XML
            for (MusicBand band : musicBands) {
                Element bandElement = xmlConverter.toXmlElement(document, band);
                rootElement.appendChild(bandElement);
            }

            // Записываем документ в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Форматирование с отступами
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Размер отступа
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // Кодировка

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

            System.out.println("The collection has been successfully saved to a file: " + filename);
        } catch (Exception e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }
}