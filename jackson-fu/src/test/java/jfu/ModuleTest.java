package jfu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import jfu.modules.DummyModule;
import jfu.test.TestUtils;

class MTestModule {

  @Test
  void testDummyModule() {
    // load modules from src/main/resources/META-INF/services/com.fasterxml.jackson.databind.Module
    // we specifically look for DummyModule here

    List<Module> modules = ObjectMapper.findModules();
    Module module = TestUtils.extractFirst(modules, DummyModule.class::isInstance);

    assertNotNull(module);
    assertEquals("DummyModule", module.getModuleName());
    assertEquals("//1.1.0", module.version().toFullString());
  }

}