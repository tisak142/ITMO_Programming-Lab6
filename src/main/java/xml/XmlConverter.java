package xml;

import OrdinaryClasses.MusicBand;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XmlConverter {
    Element toXmlElement(Document document, MusicBand musicBand);
}