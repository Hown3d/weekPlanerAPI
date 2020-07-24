package de.ludi.weekPlanerAPI;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;

@EnableEncryptableProperties
@org.springframework.context.annotation.Configuration
public class Configuration {

  @Autowired
  public void configureJackson(ObjectMapper objectMapper) {
    objectMapper.setTimeZone(TimeZone.getDefault());
    objectMapper.setLocale(Locale.GERMANY);
  }
}
