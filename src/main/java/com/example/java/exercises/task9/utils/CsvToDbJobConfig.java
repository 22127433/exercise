package com.example.java.exercises.task9.utils;
//
//

////
////import com.example.java.exercises.task9.dto.product.ProductDTO;
////import com.example.java.exercises.task9.dto.product.ProductModifyDTO;
////import com.example.java.exercises.task9.service.interfaces.ProductService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.autoconfigure.batch.BatchProperties;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.core.io.InputStreamResource;
////import org.springframework.transaction.PlatformTransactionManager;
////import org.springframework.transaction.annotation.Propagation;
////import org.springframework.transaction.annotation.Transactional;
////import org.springframework.web.multipart.MultipartFile;
////
////import java.util.HashMap;
////import java.util.List;
////import java.util.Map;
////
////@Configuration
////public class CsvToDbJobConfig {
////
////    @Autowired
////    private ProductService productService;
////
////    @Bean
////    public BatchProperties.Job csvToDbJob(JobRepository jobRepository, Step csvToDbStep) {
////        return new JobBuilder("csvToDbJob", jobRepository)
////                .start(csvToDbStep)
////                .build();
////    }
////
////    @Bean
////    public Step csvToDbStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
////        return new StepBuilder("csvToDbStep", jobRepository)
////                .<ProductModifyDTO, ProductModifyDTO>chunk(100, transactionManager) // Process 100 records per chunk
////                .reader(itemReader(null)) // Placeholder, will be set dynamically
////                .processor(itemProcessor())
////                .writer(itemWriter())
////                .faultTolerant()
////                .skip(Exception.class)
////                .skipLimit(100) // Allow up to 100 chunks to be skipped
////                .build();
////    }
////
////    @Bean
////    public ItemReader<ProductModifyDTO> itemReader(MultipartFile file) {
////        return new FlatFileItemReaderBuilder<ProductModifyDTO>()
////                .name("productItemReader")
////                .resource(file != null ? new InputStreamResource(file.getInputStream()) : new InputStreamResource(System.in))
////                .delimited()
////                .delimiter(",")
////                .names("id", "name", "price", "quantity")
////                .linesToSkip(1) // Skip header
////                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
////                    setTargetType(ProductModifyDTO.class);
////                }})
////                .build();
////    }
////
////    @Bean
////    public ItemProcessor<ProductModifyDTO, ProductModifyDTO> itemProcessor() {
////        return item -> {
////            // Validate quantity
////            if (item.getQuantity() < 0) {
////                throw new IllegalArgumentException("Quantity cannot be less than 0 for product: " + item.getName());
////            }
////            return item;
////        };
////    }
////
////    @Bean
////    public ItemWriter<ProductModifyDTO> itemWriter() {
////        return new ItemWriter<ProductModifyDTO>() {
////            @Override
////            @Transactional(propagation = Propagation.NESTED)
////            public void write(List<? extends ProductModifyDTO> items) throws Exception {
////                // Map items to their IDs for batch processing
////                Map<Integer, ProductModifyDTO> itemMap = new HashMap<>();
////                for (ProductModifyDTO item : items) {
////                    itemMap.put(item.getId(), item); // Assuming ProductModifyDTO has getId()
////                }
////
////                // Fetch existing products in bulk
////                List<Integer> ids = itemMap.keySet().stream().toList();
////                List<ProductDTO> existingProducts = productService.getProductsByIds(ids);
////
////                // Create maps for efficient lookup
////                Map<Integer, ProductDTO> existingProductMap = new HashMap<>();
////                for (ProductDTO product : existingProducts) {
////                    existingProductMap.put(product.getId(), product);
////                }
////
////                List<ProductModifyDTO> productsToCreate = new java.util.ArrayList<>();
////                List<ProductModifyDTO> productsToUpdate = new java.util.ArrayList<>();
////                List<Integer> updateIds = new java.util.ArrayList<>();
////
////                // Process each item in the chunk
////                for (ProductModifyDTO item : items) {
////                    int id = item.getId();
////                    ProductDTO existingProduct = existingProductMap.get(id);
////
////                    if (existingProduct == null) {
////                        productsToCreate.add(item);
////                    } else {
////                        int newQuantity = existingProduct.getQuantity() + item.getQuantity();
////                        if (newQuantity < 0) {
////                            throw new IllegalArgumentException("Quantity cannot be less than 0 for product ID: " + id);
////                        }
////                        item.setQuantity(newQuantity);
////                        productsToUpdate.add(item);
////                        updateIds.add(id);
////                    }
////                }
////
////                // Perform batch operations with nested transactions
////                if (!productsToCreate.isEmpty()) {
////                    productService.createProducts(productsToCreate);
////                }
////                if (!productsToUpdate.isEmpty()) {
////                    productService.updateProducts(updateIds, productsToUpdate);
////                }
////            }
////        };
////    }
////}