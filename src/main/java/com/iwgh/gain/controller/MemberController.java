package com.iwgh.gain.controller;

import com.iwgh.gain.dto.MemberDTO;
import com.iwgh.gain.dto.WishDTO;
import com.iwgh.gain.dto.WishOrderDTO;
import com.iwgh.gain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {

    private ModelAndView mav = new ModelAndView();

    @Autowired
    private MemberService msvc;

    @Autowired
    private HttpSession session;

    @GetMapping("/member/join-form")
    public String memberJoinForm(){

        return "JoinForm";
    }

    // 회원가입
    @PostMapping("/member/join")
    public ModelAndView memberJoin(@ModelAttribute MemberDTO member) throws IOException {

        System.out.println("[1]Controller : "+member);


        mav = msvc.memberJoin(member);
        System.out.println("[5]Controller : "+mav);
        return mav;
    }

    // 아이디 중복체크
    @PostMapping("/member/id-check")
    @ResponseBody
    public int idCheck(@RequestParam(value="id", required=false) String mId){
        System.out.println("[1]Controller Id : "+mId);

        int check = msvc.idCheck(mId);
        System.out.println("[1]Controller check : "+check);
        return check;
    }


    // 이메일 중복체크
    @PostMapping("/member/email-check")
    @ResponseBody
    public int emailCheck(@RequestParam(value = "email", required=false) String mEmail){

        int check = msvc.emailCheck(mEmail);

        return check;
    }


    // 이메일 인증
    @PostMapping("/member/email-auth")
    @ResponseBody
    public String emailAuth(@RequestParam("mEmail") String mEmail){

        String uuid = msvc.emailAuth(mEmail);

        return uuid;
    }

    // 로그인 폼 이동
    @GetMapping("/member/login-form")
    public String loginForm(){

        return "LoginForm";
    }


    // 로그인
    @PostMapping("/member/login")
    @ResponseBody
    public int login(@RequestBody Map<Object, Object> loginInfo){

        System.out.println("로그인 아이디 비밀번호 정보 : "+loginInfo.get("userId")+" "+loginInfo.get("password"));
        int result = msvc.login(loginInfo);
        System.out.println("ajax 리턴값 : "+result);
        return result;
    }
    
    // 내 정보 보기
    @GetMapping("/member/{mId}")
    public ModelAndView myInfo(@PathVariable("mId") String mId){

        System.out.println("아이디 정보 : "+mId);
        mav = msvc.myInfo(mId);

        return mav;
    }

    // 내 정보 수정 폼
    @GetMapping("/member/modify/{mId}")
    public ModelAndView memberModifyForm(@PathVariable("mId") String mId){

        mav = msvc.memberModifyForm(mId);

        return  mav;
    }

    // 내 정보 수정
    @PutMapping("/member/modify/{mId}")
    public ModelAndView memberModify(@PathVariable("mId") String mId, @ModelAttribute MemberDTO member) throws IOException {

        System.out.println("[1] 정보수정 : "+member);
        mav = msvc.memberModify(member);

        return mav;
    }
    
    // 로그아웃
    @GetMapping("/member/logout")
    public String logout(){

        session.invalidate();
        System.out.println("로그아웃 성공");
        return "LoginForm";
    }

    // 아이디 찾기
    @PostMapping("/member/find-id")
    @ResponseBody
    public String findId(@RequestBody Map<Object, Object> info){


        String result = msvc.findId(info);

        return result;
    }

    // 비밀번호 찾기 - 임시 비밀번호 발급
    @PostMapping("/member/find-pw")
    @ResponseBody
    public String findPw(@RequestBody Map<Object, Object> info){


        String result = msvc.findPw(info);

        return result;
    }
    
    // 회원탈퇴 페이지 이동
    @GetMapping("/member/delete/{mId}")
    public String memberDeleteForm(@PathVariable("mId") String mid){


        return "MemberDelete";
    }

    // 회원탈퇴
    @DeleteMapping("/member/delete")
    @ResponseBody
    public int memberDelete(@RequestParam(value = "id", required=false) String mid){

        System.out.println("아이디 : "+mid);

        int result = msvc.memberDelete(mid);

        return result;
    }

    @PostMapping("/member/wish")
    @ResponseBody
    public int wish(@RequestBody Map<Object,Object> wishInfo){
        System.out.println("wishInfo : "+wishInfo);
        System.out.println("wishInfo.get : "+wishInfo.get("mId"));

        int result = msvc.wish(wishInfo);

        return result;
    }

    @PostMapping("/member/wish-state")
    @ResponseBody
    public int wishState(@RequestBody Map<Object,Object> wishInfo){
        System.out.println("wishInfo : "+wishInfo);
        System.out.println("wishInfo.get : "+wishInfo.get("mId"));

        int result = msvc.wishState(wishInfo);

        return result;
    }


    @PostMapping("/member/wish-state-result")
    @ResponseBody
    public ModelAndView wishStateResult(@RequestParam("id") String id){
        System.out.println("찜목록 1 : "+id);
        mav = msvc.wishStateResult(id);

        return mav;
    }


    @PostMapping("/wish-state2")
    @ResponseBody
    public List<WishDTO> wishState2(@RequestBody Map<Object, Object> id){
        System.out.println("wishInfo : "+id.get("mId").toString());
//        System.out.println("wishInfo.get : "+wishPdcode.get("mId"));

        List<WishDTO> result = msvc.wishState2(id.get("mId").toString());

        return result;
    }

    @GetMapping("/member/wish/{mId}")
    public ModelAndView memberWishList(@PathVariable("mId") String mid){
        System.out.println("wish id : "+mid);
        mav = msvc.memberWishList(mid);

        return mav;
    }


    @DeleteMapping("/member/wish")
    @ResponseBody
    public int wishDelete(@RequestBody Map<Object,Object> wishInfo){
        System.out.println("wishInfo : "+wishInfo);
        System.out.println("wishInfo.get : "+wishInfo.get("mId"));

        int result = msvc.wishDelete(wishInfo);
        System.out.println("찜삭제후 리턴값은? : "+result);
        return result;
    }


//    @GetMapping("/orders/{mId}")
//    public ModelAndView multiOrder(@PathVariable("mId") String mId){
////        , @RequestBody List<Map<Object, Object>> pdInfo
////        System.out.println("상품정보 : "+pdInfo);
////        System.out.println("상품정보 : "+pdInfo.get(0).get("pdCode"));
////        System.out.println("찜하기 주문에서 받아온 아이디 값은? "+mId);
//        mav = msvc.multiOrder(mId);
//
//        return mav;
//    }

    @PostMapping("/orders")
    public ModelAndView wishOrdersInsert(@ModelAttribute WishOrderDTO wishOrder){
        System.out.println("상품정보 : "+wishOrder);


        mav = msvc.wishOrdersInsert(wishOrder);



        return mav;
    }




}
