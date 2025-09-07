package beadMaker.helpers;

import beadMaker.config.PathsConfig;
import processing.data.XML;

public class XMLWorker extends core.helper.XmlHelper {

  public XML[] configXML = new XML[1];
  public XML[] projectXML = new XML[1];

  String configFilePath;
  private final PathsConfig pathsConfig;

  // ------------------------------------------------------------
  // CONSTRUCTOR
  // ------------------------------------------------------------
  public XMLWorker(boolean useAppData, String appDataFolderName) {
    super(useAppData, appDataFolderName);
    this.pathsConfig = new PathsConfig(useAppData, appDataFolderName);
    this.configFilePath = pathsConfig.getDefaultConfigPath();
    configXML = GetXMLFromFile(configFilePath);
    projectXML =
        GetXMLFromFile(
            GetAbsoluteFilePathStringFromXml(
                "defaultProjectFilePath", configXML, useAppData, appDataFolderName));
  }
}
