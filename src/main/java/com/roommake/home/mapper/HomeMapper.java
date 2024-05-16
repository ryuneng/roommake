package com.roommake.home.mapper;

import com.roommake.community.vo.Community;
import com.roommake.product.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

    List<ProductDto> getNewProducts();

    List<Community> getCommPostsByCategoryId(int id);

    int cartCountByUserId(int id);
}
