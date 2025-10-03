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
        Product product = productRepository.findProductById(id);
        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice().doubleValue())
                .quantity(product.getInventory().getQuantity())
                .updatedAt(product.getUpdatedAt())
                .createdAt(product.getCreatedAt())
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductDTO createProduct(ProductModifyDTO productModifyDTO) {
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
                .updatedAt(product.getUpdatedAt())
                .createdAt(product.getCreatedAt())
                .build();
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.SERIALIZABLE
    )
    public void updateProduct(int id, ProductModifyDTO productModifyDTO) {
        Product product = productRepository.findProductById(id);
        if (product.getInventory().getQuantity() != productModifyDTO.getQuantity()) {
            product.getInventory().setQuantity(productModifyDTO.getQuantity());
            inventoryRepository.save(product.getInventory());
        }
        product.setName(productModifyDTO.getName());
        product.setPrice(new BigDecimal(productModifyDTO.getPrice()));
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProductById(int id) {
        productRepository.findProductById(id);
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsLikeName(String name) {
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
        return productRepository.findAllProductsByField(field)
                .stream()
                .map(product -> productMapper.toDTO(
                        product,
                        product.getInventory().getQuantity()))
                .toList();
    }

    @Override
    public List<ProductDTO> filterProducts(ProductParamRequest productParamRequest) {
        BigDecimal fromPrice = BigDecimal.valueOf(productParamRequest.getFromPrice());
        BigDecimal toPrice = BigDecimal.valueOf(productParamRequest.getToPrice());
        List<Product> products;
        if (productParamRequest.isAsc()) {
            products = productRepository.findProductsByPriceBetweenOrderByPriceAsc(fromPrice, toPrice);
        } else products = productRepository.findProductsByPriceBetweenOrderByPriceDesc(fromPrice, toPrice);

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
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void adjustInventoryQuantity(int productId, int quantity, boolean isIncrease) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        if (!isIncrease) {
            if (inventory.getQuantity() < quantity) {
                throw new RuntimeException("Inventory Quantity Not Enough");
            }
            inventoryRepository.decreaseStock(productId, quantity);
        }
        else {
            inventoryRepository.increaseStock(productId, quantity);
            }
    }
}
