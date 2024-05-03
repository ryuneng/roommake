package com.roommake.product.controller;

import com.roommake.admin.management.service.FaqService;
import com.roommake.admin.management.service.QnaService;
import com.roommake.admin.management.vo.Faq;
import com.roommake.admin.management.vo.FaqCategory;
import com.roommake.admin.management.vo.Qna;
import com.roommake.admin.management.vo.QnaCategory;
import com.roommake.cart.dto.CartCreateForm;
import com.roommake.product.dto.ProductQnaDto;
import com.roommake.product.dto.ProductReviewDto;
import com.roommake.product.service.ProductService;
import com.roommake.product.vo.*;
import com.roommake.resolver.Login;
import com.roommake.user.security.LoginUser;
import com.roommake.user.service.UserService;
import com.roommake.user.vo.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "상품들의 조회 API를 제공한다.")
public class ProductController {

    private final ProductService productService;
    private final QnaService qnaService;
    private final UserService userService;

    // 상품홈으로 이동하는 메소드
    @GetMapping("/home")
    public String store(Model model) {

        return "store/home";
    }

    // 상품디테일로 이동하는 메소드
    @Operation(summary = "해당상품 상세 정보조회", description = "해당 상품의 정보를 조회한다")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        List<ProductDetail> productDetail = productService.getProductDetailById(id);
        model.addAttribute("productDetail", productDetail);

        List<ProductReviewDto> productReviews = productService.getProductReviewsId(id);
        model.addAttribute("productReviews", productReviews);

        int productReviewAmount = productService.getProductReviewAmountById(id);
        model.addAttribute("productReviewAmount", productReviewAmount);

        double productRatingTotal = productService.getProductRatingTotalById(id);
        model.addAttribute("productRatingTotal", productRatingTotal);

        List<QnaCategory> qnaCategories = qnaService.getQnaCategories();
        model.addAttribute("qnaCategories", qnaCategories);

        List<ProductQnaDto> qnas = productService.getProductQnasById(id);
        model.addAttribute("qnas", qnas);

        return "store/product-detail";
    }

    /**
     * 상품리스트를 불러오는 메소드
     *
     * @param model
     * @return 전체 상품리스트
     */
    @GetMapping("/category/{id}")
    public String list(@PathVariable int id, Model model) {
        List<ProductTag> prodTagList = productService.getAllProductTags();
        model.addAttribute("prodTags", prodTagList);

        List<Product> product = productService.getProductsById(id);
        model.addAttribute("product", product);

        List<ProductCategory> subcategory = productService.getProductSubCategories(id);
        model.addAttribute("subcategory", subcategory);

        return "store/category-list";
    }

    @PostMapping("/addCart")
    @PreAuthorize("isAuthenticated()")
    public String addCart(@RequestParam("id") int id, @RequestParam("productDetailId") List<Integer> details, @RequestParam("amount") List<Integer> amounts, @Login LoginUser loginuser) {

        List<CartCreateForm> cartFormList = new ArrayList<>();
        for (int i = 0; i < details.size(); i++) {
            CartCreateForm form = new CartCreateForm();
            form.setProductId(id);
            form.setProductDetailId(details.get(i));
            form.setAmount(amounts.get(i));

            cartFormList.add(form);
        }

        productService.createCart(cartFormList, loginuser.getId());

        return String.format("redirect:detail/%d", id);
    }

    // 아직 미완성
    @GetMapping("/replyVote/{id}")
    @PreAuthorize("isAuthenticated()")
    public String replyVote(@PathVariable int id, @Login LoginUser loginuser) {

        String userNickname = loginuser.getNickname();
        productService.addProductReviewVote(id, userNickname);

        ProductReview productReview = productService.getProductReviewById(id);

        return "/store/home";
    }

    // 아직 미완성
    @PostMapping("/qna/{id}")
    @PreAuthorize("isAuthenticated()")
    public String createQna(@PathVariable int id, @RequestParam("categoryId") int categoryId, @RequestParam("title") String title, @RequestParam("secret") String secret, @RequestParam("content") String content, @Login LoginUser loginuser) {

        Product product = productService.getProductById(id);
        QnaCategory qnaCategory = qnaService.getQnaCategory(categoryId);

        if (!secret.equals("Y")) {
            return "N";
        }

        Qna qna = new Qna();
        qna.setProduct(product);
        qna.setTitle(title);
        qna.setContent(content);
        qna.setCategory(qnaCategory);
        qna.setPrivateYn(secret);

        productService.createQna(qna, loginuser.getId());

        return String.format("redirect:/store/detail/%d", id);
    }

//    @PostMapping("/replyCreate/{id}")
//    @PreAuthorize("isAuthenticated()")
//    public String creatReply(@PathVariable int id, @RequestParam("reviewStar") int reviewStar, @RequestParam("content") String content, @Login LoginUser loginuser) {
//
//        ProductReviewDto productReviewDto = new ProductReviewDto();
//        productReviewDto.setReviewStar(reviewStar);
//        productReviewDto.setContent(content);
//
//        ProductReview productReview = productService.creatReply(id, productReviewDto, loginuser.getId());
//
//        return null;
//    }

    @GetMapping("/category")
    @ResponseBody
    public List<ProductCategory> subcategory(@RequestParam("id") int productId) {

        return productService.getProductSubCategories(productId);
    }

    // 스크랩 popup으로 이동하는 메소드
    @GetMapping("/popup")
    public String popup() {
        return "layout/scrap-popup";
    }
}
