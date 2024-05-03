package com.roommake.admin.product.service;

import com.roommake.admin.product.dto.ProductListDto;
import com.roommake.admin.product.form.ProductCreateForm;
import com.roommake.admin.product.form.ProductDetailForm;
import com.roommake.product.mapper.ProductMapper;
import com.roommake.product.vo.Product;
import com.roommake.product.vo.ProductCategory;
import com.roommake.product.vo.ProductDetail;
import com.roommake.product.vo.ProductImage;
import com.roommake.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    public void detailSearch(int id, Model model) {
        Product product = productMapper.getProductById(id);
        List<ProductImage> productImages = productMapper.getProductImages(id);
        List<ProductDetail> productDetailList = productMapper.getProductDetailById(id);
        model.addAttribute("product", product);
        model.addAttribute("defaultImage", productImages.size() > 0 ? productImages.get(0).getName() : "");
        model.addAttribute("productImages", productImages);
        model.addAttribute("productDetailList", productDetailList);
    }

    private String saveDirectory = "C:\\roommake\\src\\main\\resources\\static\\images\\product";

    public void insertProduct(ProductCreateForm form) {
        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setDiscount(form.getDiscount());
        product.setContent(form.getContent());

        ProductCategory category = new ProductCategory();
        category.setId(form.getCategoryId());
        product.setCategory(category);

        productMapper.insertProduct(product);
        String imageName = "";
        ProductImage productImage = new ProductImage();
        if (!form.getImageFiles().isEmpty()) {
            for (MultipartFile multipartFile : form.getImageFiles()) {
                String filename = FileUtils.upload(multipartFile, saveDirectory);
                productImage.setProductId(product);
                productImage.setName(filename);
                productMapper.insertProductImage(productImage);
            }
        } else {
            productImage.setProductId(product);
            productImage.setName("default.jpg");
            productMapper.insertProductImage(productImage);
        }
    }

    public void modifyProduct(Product product) {
        productMapper.modifyProduct(product);
    }

    public void insertProductDetailAndSearch(ProductDetailForm productDetailForm, Model model) {
        ProductDetail productDetail = new ProductDetail();
        int productId = productDetailForm.getProductId();
        int categoryId = productMapper.getCategoryId(productId);
        productDetail.setUniqueId(categoryId * 100 + (int) Math.round(Math.random() * 100));
        productDetail.setSize(productDetailForm.getSize());
        productDetail.setColor(productDetailForm.getColor());
        productDetail.setStock(productDetailForm.getStock());
        productDetail.setProduct(productDetail.getProduct());
        productDetail.setId(productId);
        productMapper.insertProductDetail(productDetail);
        detailSearch(productId, model);
    }

    public int getTotalProducts(String keyword, String type) {
        return productMapper.getTotalProducts(keyword, type);
    }

    // 페이징 처리
    public List<ProductListDto> getProductByPage(int page, int pageSize, String keyword, String type) {
        int offset = (page - 1) * pageSize;
        return productMapper.getProductsByPage(offset, pageSize, keyword, type);
    }
}

