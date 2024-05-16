package com.roommake.channel.mapper;

import com.roommake.channel.dto.ChannelPostReplyDto;
import com.roommake.channel.vo.ChannelPostReply;
import com.roommake.channel.vo.ChannelPostReplyComplaint;
import com.roommake.channel.vo.ChannelPostReplyLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostReplyMapper {

    int getTotalReplyCountByPostId(int postId);

    int getTotalReplyRow(int postId);

    List<ChannelPostReply> getAllRepliesByPostId(@Param("postId") int postId,
                                                 @Param("begin") int begin,
                                                 @Param("end") int end);

    List<ChannelPostReplyDto> getAllRepliesByPostIdAndUserId(@Param("userId") int userId,
                                                             @Param("postId") int postId,
                                                             @Param("begin") int begin,
                                                             @Param("end") int end);

    void createPostReply(ChannelPostReply postReply);

    void createPostReReply(ChannelPostReply postReply);

    void modifyReplyGroupId(ChannelPostReply postReply);

    ChannelPostReply getReplyByReplyId(int replyId);

    void createReplyComplaint(ChannelPostReplyComplaint postReplyComplaint);

    void modifyReply(ChannelPostReply postReply);

    int getReReplyCount(int replyId);

    void addPostReplyLike(ChannelPostReplyLike replyLike);

    void deletePostReplyLike(ChannelPostReplyLike replyLike);
}
