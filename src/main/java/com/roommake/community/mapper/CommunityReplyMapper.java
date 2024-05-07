package com.roommake.community.mapper;

import com.roommake.community.dto.CommReplyCriteria;
import com.roommake.community.dto.CommReplyDto;
import com.roommake.community.vo.CommunityReply;
import com.roommake.community.vo.CommunityReplyComplaint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityReplyMapper {
    int getTotalReplyCountByCommId(int commId);

    int getTotalReplyRow(int commId);

    List<CommReplyDto> getAllRepliesByUserId(CommReplyCriteria commReplyCriteria);

    List<CommReplyDto> getAllReplies(CommReplyCriteria commReplyCriteria);

    void createCommunityReply(CommunityReply communityReply);

    void modifyReplyGroupId(CommunityReply communityReply);

    void createCommunityReReply(CommunityReply communityReply);

    CommunityReply getCommReplyByReplyId(int replyId);

    void modifyCommunityReply(CommunityReply communityReply);

    void createCommunityReplyComplaint(CommunityReplyComplaint replyComplaint);
}
