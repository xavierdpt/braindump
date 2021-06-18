package jfu;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;

public class MyModule extends Module {

  @Override
  public String getModuleName() {
    return "MyModule";
  }

  @Override
  public Version version() {
    return new Version(1, 1, 0, "", null, null);
  }

  @Override
  public void setupModule(SetupContext context) {

  }
}
