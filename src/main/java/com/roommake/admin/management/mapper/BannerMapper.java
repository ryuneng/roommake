package com.roommake.admin.management.mapper;

import com.roommake.admin.management.vo.Banner;
import com.roommake.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    void createBanner(Banner banner);

    Banner getBannerById(int id);

    List<Banner> getAllBanners();

    void modifyBanner(Banner banner);

    int getTotalRows(Criteria criteria);

    List<Banner> getBanners(Criteria criteria);

    void modifyBannerStatus();
}
