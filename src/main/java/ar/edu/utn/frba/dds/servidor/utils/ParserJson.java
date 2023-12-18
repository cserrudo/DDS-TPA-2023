package ar.edu.utn.frba.dds.servidor.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ParserJson {
  public static Map<String, Object> parsearJSON(String nombre) throws IOException {
    String jsonFilePath = String.format("src/main/resources/templates/%s.json", nombre);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> data = mapper.readValue(new File(jsonFilePath), new TypeReference<Map<String, Object>>() {
    });

    return data;
  }
}
