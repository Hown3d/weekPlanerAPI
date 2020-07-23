package de.ludi.weekPlanerAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Autowired
  public void configureJackson(ObjectMapper objectMapper) {
    objectMapper.setTimeZone(TimeZone.getDefault());
    objectMapper.setLocale(Locale.GERMANY);
  }
}
