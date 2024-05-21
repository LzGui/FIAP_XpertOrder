package br.com.fiap.xpertorder.xpertordermspedidos.config;

import br.com.fiap.xpertorder.xpertordermspedidos.model.Pedido;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

   @Autowired
   public JobBuilderFactory jobBuilderFactory;

   @Autowired
   public StepBuilderFactory stepBuilderFactory;

   @Autowired
   public MongoTemplate mongoTemplate;

   @Bean
   public JsonItemReader<Pedido> reader() {
      return new JsonItemReader<>(
              new ClassPathResource("pedidos.json"),
              new JacksonJsonObjectReader<>(Pedido.class));
   }

   @Bean
   public ItemProcessor<Pedido, Pedido> processor() {
      return new ItemProcessor<Pedido, Pedido>() {
         @Override
         public Pedido process(Pedido pedido) {
            return pedido; // No processing required
         }
      };
   }

   @Bean
   public MongoItemWriter<Pedido> writer() {
      MongoItemWriter<Pedido> writer = new MongoItemWriter<>();
      writer.setTemplate(mongoTemplate);
      writer.setCollection("pedidos");
      return writer;
   }

   @Bean
   public Step step1() {
      return stepBuilderFactory.get("step1")
              .<Pedido, Pedido>chunk(10)
              .reader(reader())
              .processor(processor())
              .writer(writer())
              .build();
   }

   @Bean
   public Job importUserJob(JobRepository jobRepository) {
      return jobBuilderFactory.get("importUserJob")
              .incrementer(new RunIdIncrementer())
              .flow(step1())
              .end()
              .build();
   }

}
