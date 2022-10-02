package com.iwgh.gain.controller;

import com.iwgh.gain.dto.MemberDTO;
import com.iwgh.gain.service.MemberMngService;
import com.iwgh.gain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberMngController {

    private ModelAndView mav = new ModelAndView();

    List<MemberDTO> memberList = new ArrayList<MemberDTO>();

    @Autowired
    private MemberMngService mmsvc;

    @Autowired
    private HttpSession session;

    @GetMapping("/members")
    public ModelAndView memberList(){

        mav = mmsvc.memberList();

        return mav;
    }

    @GetMapping("/members/dlist")
    public ModelAndView deleteMemberList(){

        mav = mmsvc.deleteMemberList();

        return mav;
    }

//    @GetMapping("/members/dlist-page")
//    public String deleteMemberList(){
//
//        return "MemberDeleteList";
//    }

//    @GetMapping("/members/dlist")
//    @ResponseBody
//    public List<MemberDTO> deleteMemberList2(){
//        System.out.println("진입");
////        List<MemberDTO> member = mmsvc.deleteMemberList2();
//        memberList = mmsvc.deleteMemberList2();
//        System.out.println("결과 : "+memberList.get(0));
//        return memberList;
//    }

    @PostMapping("/members/dlist")
    @ResponseBody
    public int restoreMember(@RequestParam(value="mId", required=false) String mId){
        System.out.println("복구아이디 : "+mId);
        int result = mmsvc.restoreMember(mId);

        return result;
    }





}
