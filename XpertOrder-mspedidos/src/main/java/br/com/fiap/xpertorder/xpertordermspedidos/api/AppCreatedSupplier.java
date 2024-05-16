package br.com.fiap.xpertorder.xpertordermspedidos.api;

import br.com.fiap.xpertorder.xpertordermspedidos.api.model.AppModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Slf4j
public class AppCreatedSupplier implements Supplier<AppModel>{

   @Override
   public AppModel get() {
      log.info("App criado");
      return null;
   }
}
