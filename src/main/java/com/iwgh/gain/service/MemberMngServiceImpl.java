package com.iwgh.gain.service;

import com.iwgh.gain.dao.MemberMngDAO;
import com.iwgh.gain.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MemberMngServiceImpl implements MemberMngService{

    ModelAndView mav = new ModelAndView();

    @Autowired
    MemberMngDAO mMngDAO;

    @Autowired
    HttpSession httpSession;


    @Override
    public ModelAndView memberList() {

        List<MemberDTO> member = mMngDAO.memberList();

        mav.addObject("member",member);
        mav.setViewName("MemberList");

        return mav;
    }

    @Override
    public ModelAndView deleteMemberList() {

        List<MemberDTO> member = mMngDAO.deleteMemberList();

        mav.addObject("delMember",member);
        mav.setViewName("MemberDeleteList");

        return mav;
    }

//    @Override
//    public List<MemberDTO> deleteMemberList2() {
//
//        List<MemberDTO> member = mMngDAO.deleteMemberList();
//
//        return member;
//    }

    @Override
    public int restoreMember(String mId) {

        int result = mMngDAO.restoreMember(mId);
        int result2 = mMngDAO.restoreMember2(mId);
        result += result2;


        return result;
    }




}
