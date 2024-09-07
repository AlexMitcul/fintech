package org.core;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;


record Coords(double lat, double lon) {}

public record City(String slug, Coords coords) {
    private static final Logger logger = LoggerFactory.getLogger(City.class);

    public void toXML() {
        XmlMapper xmlMapper = new XmlMapper();

        File xmlFile = new File("app/src/main/resources/" + this.slug() + ".xml");
        try {
            xmlMapper.writeValue(xmlFile, this);
            logger.info("Successfully write to disk a file " + xmlFile.toString());
        } catch (IOException ioException) {
            logger.error("Cannot write file " + xmlFile.getName() + " to disk.");
        }
    }

}
