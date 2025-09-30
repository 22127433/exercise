package com.example.java.exercises.task9.service.implement;

import com.example.java.exercises.task9.dto.product.ProductModifyDTO;
import com.example.java.exercises.task9.dto.product.ProductDTO;
import com.example.java.exercises.task9.dto.product.ProductParamRequest;
import com.example.java.exercises.task9.entity.Inventory;
import com.example.java.exercises.task9.entity.Product;
import com.example.java.exercises.task9.mapper.ProductMapper;
import com.example.java.exercises.task9.repository.InventoryRepository;
import com.example.java.exercises.task9.repository.ProductRepository;
import com.example.java.exercises.task9.service.interfaces.ProductService;
import com.example.java.exercises.task9.utils.ReflectionValidator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(
            ProductRepository productRepository,
            InventoryRepository inventoryRepository,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return null;
        Inventory inventory = inventoryRepository.findByProductId(id).orElse(null);
        if (inventory == null) return null;
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice().doubleValue())
                .quantity(inventory.getQuantity())
                .updatedAt(product.getUpdatedAt().toString())
                .createdAt(product.getCreatedAt().toString())
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductDTO createProduct(ProductModifyDTO productModifyDTO){
        try {
            ReflectionValidator.validate(productModifyDTO);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        Product product = Product.builder()
                .name(productModifyDTO.getName())
                .price(BigDecimal.valueOf(productModifyDTO.getPrice()))
                .build();
        Inventory inventory = Inventory.builder()
                .quantity(productModifyDTO.getQuantity())
                .product(product)
                .build();
        productRepository.save(product);
        inventoryRepository.save(inventory);
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice().doubleValue())
                .quantity(inventory.getQuantity())
                .updatedAt(product.getUpdatedAt().toString())
                .createdAt(product.getCreatedAt().toString())
                .build();
    }

    @Override
    @Transactional (
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.SERIALIZABLE
    )
    public void updateProduct(int id, ProductModifyDTO productModifyDTO){
        try {
            ReflectionValidator.validate(productModifyDTO);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Inventory inventory = inventoryRepository.findByProductId(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        if (inventory.getQuantity() != productModifyDTO.getQuantity()) {
            inventory.setQuantity(productModifyDTO.getQuantity());
            inventoryRepository.save(inventory);
        }
        product.setName(productModifyDTO.getName());
        product.setPrice(new BigDecimal(productModifyDTO.getPrice()));
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProductById(int id){
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        inventoryRepository.findByProductId(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        productRepository.deleteById(id);
        inventoryRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsLikeName(String name){
        return productRepository.findByNameContaining(name)
                .stream()
                .map(product -> productMapper.toDTO(
                        product,
                        inventoryRepository
                                .findByProductId(product.getId())
                                .orElseThrow(() -> new RuntimeException("Inventory not found"))
                                .getQuantity()))
                .toList();
    }

    @Override
    public List<ProductDTO> sortProductsAscByField(String field) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,field))
                .stream()
                .map(product -> productMapper.toDTO(
                        product,
                        inventoryRepository
                                .findByProductId(product.getId())
                                .orElseThrow(() -> new RuntimeException("Inventory not found"))
                                .getQuantity()))
                .toList();
    }

    @Override
    public List<ProductDTO> filterProducts(ProductParamRequest productParamRequest){
        BigDecimal fromPrice = BigDecimal.valueOf(productParamRequest.getFromPrice());
        BigDecimal toPrice = BigDecimal.valueOf(productParamRequest.getToPrice());
        List<Product> products;
        if (productParamRequest.isAsc()){
            products = productRepository.findProductsByPriceBetweenOrderByPriceAsc(fromPrice, toPrice);
        }
        else products = productRepository.findProductsByPriceBetweenOrderByPriceDesc(fromPrice, toPrice);

        return products
                .stream()
                .map(product -> productMapper.toDTO(
                        product,
                        inventoryRepository
                                .findByProductId(product.getId())
                                .orElseThrow(() -> new RuntimeException("Inventory not found"))
                                .getQuantity()))
                .toList();
    }

    @Override
    @Transactional (isolation = Isolation.SERIALIZABLE)
    public void adjustInventoryQuantity(int productId, int quantity, boolean isIncrease) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        if (!isIncrease) {
            if (inventory.getQuantity() < quantity) {
                throw new RuntimeException("Inventory Quantity Not Enough");
            }
            quantity *= -1;
        }
        inventory.setQuantity(inventory.getQuantity() + quantity);
    }
}
