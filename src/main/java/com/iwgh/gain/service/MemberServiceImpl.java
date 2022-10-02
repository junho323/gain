package com.iwgh.gain.service;

import com.iwgh.gain.dao.MemberDAO;
import com.iwgh.gain.dto.MemberDTO;
import com.iwgh.gain.dto.WishDTO;
import com.iwgh.gain.dto.WishOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class MemberServiceImpl implements MemberService{

    private ModelAndView mav = new ModelAndView();

    @Autowired
    private MemberDAO mdao;

    @Autowired
    private PasswordEncoder pwEnc;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HttpSession session;


    @Override
    public ModelAndView memberJoin(MemberDTO member) throws IOException {

        System.out.println("[2]Service : "+member);

        // 1. 파일 불러오기
        MultipartFile file = member.getMProfile();

        // 2. 파일 이름 불러오기
        String originalFileName = file.getOriginalFilename();

        // 3. 난수(UUID) 생성하기
        String uuid = UUID.randomUUID().toString().substring(0,8);

        // 4. 파일이름 중복방지
        String fileName = uuid + "_" + originalFileName;

        // 5. 파일 저장 위치 설정 - 상대경로
        Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profile");
        System.out.println("경로 : "+path);
        String savePath = path+"/"+fileName;

        // 6. 파일 선택 여부
        if(!file.isEmpty()){
            member.setMProfileName(fileName);
            file.transferTo(new File(savePath));
        }else{
            member.setMProfileName("default.png");
        }

        // 7. 비밀번호 암호화
        member.setMPw(pwEnc.encode(member.getMPw()));

        // 8. 주소 처리
        member.setMAddr("("+member.getAddr1()+")"+" "+member.getAddr2()+" "+member.getAddr3());

        int result = mdao.memberJoin(member);

        if(result > 0){
            mav.setViewName("LoginForm");

        }else{
            mav.setViewName("index");
        }
        System.out.println("[4]Service : "+result);

        return mav;
    }

    @Override
    public int idCheck(String mId) {

        int result = mdao.idCheck(mId);
        int result2 = mdao.idCheck2(mId);
        result += result2;

        return result;
    }

    @Override
    public int emailCheck(String mEmail) {

        int result = mdao.emailCheck(mEmail);
        int result2 = mdao.emailCheck2(mEmail);
        result += result2;

        return result;
    }

    @Override
    public String emailAuth(String mEmail) {

        String uuid = UUID.randomUUID().toString().substring(0,5);

        MimeMessage mail = mailSender.createMimeMessage();
//        SimpleMailMessage
        String str = "<h2>안녕하세요. 가구 인테리어 전문 GAIN 입니다.</h2></br>"
                    + "<h3>이메일 인증번호는 "+uuid+" 입니다.</h3>"
                    + "<h3>인증번호를 입력해주세요!</h3>";
        try {
            mail.setSubject("[가인(GAIN)] 이메일 인증","utf-8");
            mail.setText(str,"UTF-8","html");
            mail.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mEmail));
            System.out.println("이메일 인증 번호 : "+uuid);
            mailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("이메일 인증 오류 : "+e);
        }


        return uuid;
    }

    @Override
    public int login(Map<Object, Object> loginInfo) {

        String mId = loginInfo.get("userId").toString();
        String mPw = loginInfo.get("password").toString();
        int result  = 0;

        result = mdao.idCheck(mId);

        // 암호화된 비밀번호 검색 겸 회원정보 불러오기
        if(result == 1){
            MemberDTO member = mdao.memberInfo(mId);
            if(pwEnc.matches(mPw, member.getMPw())){
                result = 2;
                session.setAttribute("member", member);
                System.out.println("로그인 성공!");
            }else{
                result = 1;
            }
        }

//        System.out.println("암호화된 비밀번호 : "+member.getMPw());
//        System.out.println("일 치 여 부 : "+pwEnc.matches(mPw, member.getMPw()));

        return result;
    }

    @Override
    public ModelAndView myInfo(String mId) {

        MemberDTO member = mdao.memberInfo(mId);

        mav.addObject("info",member);
        mav.setViewName("MemberInfo");

        return mav;
    }

    @Override
    public ModelAndView memberModifyForm(String mId) {

        MemberDTO member = mdao.memberInfo(mId);
        member.setAddr1(member.getMAddr().split(" ")[0]);
        member.setAddr2(member.getMAddr().split(" ")[1]);
        member.setAddr3(member.getMAddr().split(" ")[2]);

        mav.addObject("modi",member);
        mav.setViewName("MemberModify");

        return mav;
    }

    @Override
    public ModelAndView memberModify(MemberDTO member) throws IOException {



        // 1. 파일 불러오기
        MultipartFile file = member.getMProfile();

        // 2. 파일 이름 불러오기
        String originalFileName = file.getOriginalFilename();

        // 3. 난수(UUID) 생성하기
        String uuid = UUID.randomUUID().toString().substring(0,8);

        // 4. 파일이름 중복방지
        String fileName = uuid + "_" + originalFileName;

        // 5. 파일 저장 위치 설정 - 상대경로
        Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profile");
        System.out.println("경로 : "+path);
        String savePath = path+"/"+fileName;

        // 6. 파일 선택 여부
        if(!file.isEmpty()){
            member.setMProfileName(fileName);
            file.transferTo(new File(savePath));
        }else{
            member.setMProfileName("default.png");
        }

        // 7. 비밀번호 암호화
        member.setMPw(pwEnc.encode(member.getMPw()));

        // 8. 주소 처리
        member.setMAddr("("+member.getAddr1()+")"+" "+member.getAddr2()+" "+member.getAddr3());

        int result = mdao.memberModify(member);

        if(result > 0){
            System.out.println("수정성공");
            mav.setViewName("redirect:/member/"+member.getMId());
        }else {
            System.out.println("수정실패");
            mav.setViewName("index");
        }

        return mav;
    }

    @Override
    public String findId(Map<Object, Object> info) {

        String name = info.get("userName").toString();
        String email = info.get("userEmail").toString();
        System.out.println("이름과 이메일 : "+name+" "+email);

        String result = mdao.findId(info);
        System.out.println(result);
        if(result != null){
            System.out.println("찾기 성공 : "+result);


        }else if(result == null){
            System.out.println("찾기 실패");
            result = "";

        }
        return result;
    }

    @Override
    public String findPw(Map<Object, Object> info) {


        String id = info.get("userId").toString();
        String email = info.get("userEmail").toString();
        System.out.println("아이디와 이메일 : "+id+" "+email);
        String tempPw = "";


        String result = mdao.findPw(info);
        System.out.println(result);
        if(result != null){
            System.out.println("찾기 성공 : "+result);
            result = UUID.randomUUID().toString().substring(0,5);
            tempPw = pwEnc.encode(result);
            info.put("pw",tempPw);
            int pwUpdateState = mdao.updatePw(info);
            if(pwUpdateState > 0){
                System.out.println("임시 비밀번호 발급 완료! : "+result);
            }

        }else if(result == null){
            System.out.println("찾기 실패");
            result = "";

        }
        return result;

    }

    @Override
    public int memberDelete(String mid) {

        int result = mdao.memberDelete(mid);
        if(result > 0){
            session.invalidate();
        }

        return result;
    }

    @Override
    public int wish(Map<Object, Object> wishInfo) {

        int check = mdao.wishCheck(wishInfo);
        int result = 0;
        if(check > 0){
            result = mdao.wishCancel(wishInfo);
        }else{
            result = mdao.wish(wishInfo);
            if(result > 0){
                result = 0;

            }
        }

        return result;
    }

    @Override
    public int wishState(Map<Object, Object> wishInfo) {

        int result = mdao.wishCheck(wishInfo);

        return result;
    }

    @Override
    public ModelAndView wishStateResult(String id) {

        if(!id.isEmpty()){
            List<WishDTO> wishList = mdao.wishStateResult(id);

            System.out.println("찜목록 2 : "+wishList);
            mav.addObject("wish",wishList);
            mav.setViewName("ProductList");
        }
        mav.setViewName("ProductList");

        return mav;
    }

    @Override
    public List<WishDTO> wishState2(String id) {
        List<WishDTO> wishList;

        wishList = mdao.wishStateResult(id);
        System.out.println("찜목록 상품 코드들 : " + wishList);

        return wishList;
    }

    @Override
    public ModelAndView memberWishList(String mId) {

        List<WishDTO> wishList = mdao.memberWishList(mId);

        List<Integer> wishListQtyCnt = new ArrayList<>();

        for(int i = 0; i< wishList.size(); i++){
            wishListQtyCnt.add(i,wishList.get(i).getPdQty());
        }

        mav.addObject("wishList",wishList);
        mav.addObject("wishQtyList",wishListQtyCnt);
//        System.out.println("wishList : "+wishList);
//        System.out.println("wishList cnt : "+wishList.size());
        System.out.println("wishListQtyCnt  : "+wishListQtyCnt);
        System.out.println("wishListQtyCnt cnt : "+wishListQtyCnt.size());

        mav.setViewName("MemberWish");


        return mav;
    }

    @Override
    public int wishDelete(Map<Object, Object> wishInfo) {


        int result = mdao.wishCancel(wishInfo);


        return result;
    }

//    @Override
//    public ModelAndView multiOrder(String mId) {
//
////        MemberDTO member = mdao.memberInfo(mId);
////        mav.addObject("member",member);
////        mav.addObject("list",pdInfo);
//        mav.setViewName("MultiOrder");
//
//        return mav;
//    }

    @Override
    public ModelAndView wishOrdersInsert(WishOrderDTO wishOrder) {

        System.out.println("wishOrder.getPdCode().size() : "+wishOrder.getPdCode().size());

        for (int i = 1; i < wishOrder.getPdCode().size(); i++) {
            wishOrder.getMId().add(i, wishOrder.getMId().get(0));
            wishOrder.getOrName().add(i,wishOrder.getOrName().get(0));
            wishOrder.getOrPhone().add(i,wishOrder.getOrPhone().get(0));
            wishOrder.getOrAddr().add(i,wishOrder.getOrAddr().get(0));
            wishOrder.getOrEmail().add(i,wishOrder.getOrEmail().get(0));
        }
            System.out.println("wishOrder : "+wishOrder);
//        List<WishOrderDTO> wishOrderList = new ArrayList<WishOrderDTO>();
//        for (int i = 0; i < wishOrder.getPdCode().size(); i++) {
//            wishOrderList.add(i, wishOrder);
//        }
//        System.out.println("wishOrderList : "+wishOrderList);

        Map<String,WishOrderDTO> map = new HashMap<>();
        map.put("wishOrder",wishOrder);
        List<Map<String,WishOrderDTO>> wishOrderList2 = new ArrayList<Map<String,WishOrderDTO>>();
        for (int i = 0; i < wishOrder.getPdCode().size(); i++) {
            wishOrderList2.add(i, map);
        }
//        wishOrderList2.add(asd);
        System.out.println("wishOrderList2 "+wishOrderList2);
        System.out.println("wishOrderList2 size "+wishOrderList2.size());
//        System.out.println("wishOrderList2 size "+wishOrderList2.get(0).get("mId").getMId());
//        System.out.println("wishOrderList2 "+wishOrderList2.size());
//        System.out.println("wishOrderList2 "+wishOrderList2.get(0).get("wishOrder").getPdCode());
//        System.out.println("wishOrder : "+wishOrderList);
        int result = mdao.wishOrdersInsert(wishOrderList2);

        if(result > 0){
            String orderId = wishOrder.getMId().get(0);
            mdao.afterorderWishListDelete(orderId);
            mav.setViewName("OrderConfirm2");
        }else{
            mav.setViewName("index");
        }

//        System.out.println(result);
//        System.out.println("보자보자 : "+wishOrder);
        return mav;
    }
}

