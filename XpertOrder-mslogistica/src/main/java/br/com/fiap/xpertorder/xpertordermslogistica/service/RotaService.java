package br.com.fiap.xpertorder.xpertordermslogistica.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RotaService {

   @Value("${google.maps.api.key}")
   private String googleApiKey;

   private GeoApiContext getGeoContext() {
      return new GeoApiContext.Builder()
              .apiKey(googleApiKey)
              .build();
   }

   public DirectionsResult calcularRota(String origem, String destino) throws InterruptedException, ApiException, IOException {
      return DirectionsApi.getDirections(getGeoContext(), origem, destino).await();
   }

   public long estimarTempoEntrega(String origem, String destino) throws InterruptedException, ApiException, IOException {
      DirectionsResult result = calcularRota(origem, destino);
      DirectionsRoute route = result.routes[0];
      return route.legs[0].duration.inSeconds;
   }
}
