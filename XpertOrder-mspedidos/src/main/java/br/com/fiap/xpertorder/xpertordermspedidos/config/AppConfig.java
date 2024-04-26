package br.com.fiap.xpertorder.xpertordermspedidos.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("br.com.fiap")
public class AppConfig {

   @Bean
   RestTemplate restTemplat() {
      RestTemplate restTemplate = new RestTemplate();
      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
      converter.setObjectMapper(new ObjectMapper());
      restTemplate.getMessageConverters().add(converter);

      return restTemplate;

   }

}
