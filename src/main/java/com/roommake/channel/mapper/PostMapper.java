package com.roommake.channel.mapper;

import com.roommake.channel.dto.ChannelCriteria;
import com.roommake.channel.dto.PostListDto;
import com.roommake.channel.vo.ChannelPost;
import com.roommake.channel.vo.ChannelPostComplaint;
import com.roommake.channel.vo.ChannelPostLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    List<ChannelPost> getAllPosts(ChannelCriteria criteria);

    List<PostListDto> getAllPostAndLikeStatusByUserId(ChannelCriteria criteria);

    void createPost(ChannelPost post);

    void modifyPost(ChannelPost post);

    ChannelPost getPostByPostId(int postId);

    ChannelPostLike getPostLikeUser(ChannelPostLike postLikeUser);

    void addPostLike(ChannelPostLike postLike);

    void deletePostLike(ChannelPostLike postLike);

    void createPostComplaint(ChannelPostComplaint postComplaint);
}
