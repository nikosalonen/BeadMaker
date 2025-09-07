package beadMaker.config;

import beadMaker.helpers.XMLWorker;
import processing.data.XML;

public class ConfigService {
    private final XMLWorker xmlWorker;

    public ConfigService(XMLWorker xmlWorker) {
        this.xmlWorker = xmlWorker;
    }

    public XML[] getConfigXml() {
        return xmlWorker.configXML;
    }

    public XML[] getProjectXml() {
        return xmlWorker.projectXML;
    }

    // Placeholder for schema validation hook
    public boolean isValid() {
        return xmlWorker.configXML != null && xmlWorker.projectXML != null;
    }
}


