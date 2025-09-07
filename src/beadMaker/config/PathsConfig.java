package beadMaker.config;

import java.io.File;

public class PathsConfig {
  private final boolean useAppData;
  private final String appDataFolderName;

  public PathsConfig(boolean useAppData, String appDataFolderName) {
    this.useAppData = useAppData;
    this.appDataFolderName = appDataFolderName;
  }

  public boolean isUseAppData() {
    return useAppData;
  }

  public String getRoot() {
    if (useAppData) {
      return System.getenv("APPDATA") + File.separator + appDataFolderName;
    }
    return System.getProperty("user.dir");
  }

  public String getConfigDir() {
    return getRoot() + File.separator + "config";
  }

  public String getDefaultConfigPath() {
    return getConfigDir() + File.separator + "_default_config.xml";
  }

  public String getPalettesDir() {
    return getRoot() + File.separator + "pallettes";
  }

  public String getImagesDir() {
    return getRoot() + File.separator + "images";
  }

  public String getColorMapsDir() {
    return getRoot() + File.separator + "ColorMaps";
  }

  public String getLUTsDir() {
    return getRoot() + File.separator + "LUTs";
  }
}
