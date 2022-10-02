package com.iwgh.gain.dao;

import com.iwgh.gain.dto.MemberDTO;
import com.iwgh.gain.dto.WishDTO;
import com.iwgh.gain.dto.WishOrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberDAO {
    int memberJoin(MemberDTO member);

    int idCheck(String mId);
    int idCheck2(String mId);

    int emailCheck(String mEmail);

    int emailCheck2(String mEmail);

    int login(String mId, String mPw);

    MemberDTO memberInfo(String mId);

    int memberModify(MemberDTO member);

    String findId(Map<Object, Object> info);

    String findPw(Map<Object, Object> info);

    int updatePw(Map<Object, Object> info);

    int memberDelete(String mid);


    int wish(Map<Object, Object> wishInfo);

    int wishCheck(Map<Object, Object> wishInfo);

    int wishCancel(Map<Object, Object> wishInfo);

    List<WishDTO> wishStateResult(String id);

    List<WishDTO> wishState2(String id);

    List<WishDTO> memberWishList(String mId);

    int wishOrdersInsert(List<Map<String, WishOrderDTO>> wishOrderList2);

    void afterorderWishListDelete(String s);
}
