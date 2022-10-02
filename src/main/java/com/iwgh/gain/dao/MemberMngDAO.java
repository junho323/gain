package com.iwgh.gain.dao;

import com.iwgh.gain.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMngDAO {
    List<MemberDTO> memberList();

    List<MemberDTO> deleteMemberList();

    int restoreMember(String mId);

    int restoreMember2(String mId);
}
