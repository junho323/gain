package com.iwgh.gain.service;

import com.iwgh.gain.dto.MemberDTO;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface MemberMngService {

    ModelAndView memberList();

    ModelAndView deleteMemberList();

    int restoreMember(String id);

//    List<MemberDTO> deleteMemberList2();
}
