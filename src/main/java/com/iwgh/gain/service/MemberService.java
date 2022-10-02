package com.iwgh.gain.service;

import com.iwgh.gain.dto.MemberDTO;
import com.iwgh.gain.dto.WishDTO;
import com.iwgh.gain.dto.WishOrderDTO;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MemberService {
    ModelAndView memberJoin(MemberDTO member) throws IOException;

    int idCheck(String mId);

    int emailCheck(String mEmail);

    String emailAuth(String mEmail);

    int login(Map<Object, Object> loginInfo);

    ModelAndView myInfo(String mId);

    ModelAndView memberModifyForm(String mId);

    ModelAndView memberModify(MemberDTO member) throws IOException;

    String findId(Map<Object, Object> info);

    String findPw(Map<Object, Object> info);

    int memberDelete(String mid);

    int wish(Map<Object, Object> wishInfo);

    int wishState(Map<Object, Object> wishInfo);

    ModelAndView wishStateResult(String id);

    List<WishDTO> wishState2(String id);

    ModelAndView memberWishList(String mid);

    int wishDelete(Map<Object, Object> wishInfo);

//    ModelAndView multiOrder(String mId);

    ModelAndView wishOrdersInsert(WishOrderDTO wishOrder);
}
