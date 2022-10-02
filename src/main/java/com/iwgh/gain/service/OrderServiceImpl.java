package com.iwgh.gain.service;


import com.iwgh.gain.dao.MemberDAO;
import com.iwgh.gain.dao.OrderDAO;
import com.iwgh.gain.dto.MemberDTO;
import com.iwgh.gain.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private ModelAndView mav = new ModelAndView();

    @Autowired
    private HttpSession session;


    @Autowired
    private OrderDAO odao;



    @Override
    public ModelAndView insertOrder(OrderDTO order) {


        System.out.println("[2]등록 Service : " + order);
        // 2. 주소 처리
        String addr1 = order.getAddr1();
        String addr2 = order.getAddr2();
        String addr3 = order.getAddr3();

        String orAddr = "("+addr1+")"+addr2+" "+addr3;


        if(!addr1.equals("")) {
            order.setOrAddr(orAddr);
        }

        int result = odao.insertOrder(order);

        if(result > 0){
            mav.setViewName("OrderConfirm");
        }else{
            mav.setViewName("index");
        }
        System.out.println("[3]등록 Service : " + mav);
        return mav;
    }


    @Override
    public ModelAndView insertOrder2(OrderDTO order) {

        int result = odao.insertOrder2(order);

        if(result > 0){
            mav.setViewName("OrderConfirm");
        }else{
            mav.setViewName("index");
        }

        return mav;
    }



    @Override
    public ModelAndView orderView(String orNum) {
        OrderDTO order = odao.orderView(orNum);

        mav.addObject("view",order);
        mav.setViewName("OrderView");

        return mav;
    }



    @Override
    public ModelAndView list(String pdCode,String mId) {
        System.out.println("[2]: " + pdCode);
        OrderDTO pdList = odao.list(pdCode,mId);

        System.out.println("[4] " + pdList);
        mav.setViewName("OrderScreenForm");
        mav.addObject("list",pdList);


        return mav;
    }

    @Override
    public ModelAndView OrderList(String mId,String pdCode) {
        List<OrderDTO> list = odao.OrderList(mId,pdCode);

        mav.setViewName("OrderListForm");
        mav.addObject("olist",list);


        return mav;
    }

    @Override
    public ModelAndView orderConfirm(String orNum) {
        OrderDTO list = odao.orderConfirm(orNum);


        mav.addObject("confirm",list);
        mav.setViewName("OrderConfirm");

        return mav;
    }


    @Override
    public ModelAndView memberView() {

        OrderDTO member = odao.memberView();

        mav.addObject("view",member);
        mav.setViewName("OrderScreenForm");

        return mav;
    }

    @Override
    public ModelAndView insertCancel(OrderDTO order) {
        System.out.println("[2]취소 Service : " + order);
        int result = odao.insertCancel(order);

        if(result > 0){
            mav.setViewName("redirect:/order/PD0002/OrderList?mId="+order.getMId());
        }else{
            mav.setViewName("index");
        }
        System.out.println("[4]취소 Service : " + order);
        return mav;
    }

    @Override
    public ModelAndView cancelList(String mId) {
        List<OrderDTO> list = odao.cancelList(mId);

        mav.addObject("list",list);
        mav.setViewName("OrderCancelListForm");

        return mav;
    }

    @Override
    public ModelAndView adminCancelList() {
        List<OrderDTO>list = odao.adminCancelList();

        mav.addObject("list",list);
        mav.setViewName("AdminOrderCancelForm");

        return mav;
    }

    @Override
    public ModelAndView orderDelete(String orNum) {
        int result = odao.orderDelete(orNum);

        if(result>0){
            mav.setViewName("redirect:/order/{pdCode}/adminCancelList");
        }else{
            mav.setViewName("index");
        }

        return mav;
    }

    @Override
    public ModelAndView orderView1(String orNum) {

        OrderDTO order = odao.orderView1(orNum);

        mav.addObject("view",order);
        mav.setViewName("OrderView1");

        return mav;
    }

    @Override
    public ModelAndView orderQnAList(String mId) {
        List<OrderDTO> list = odao.orderQnAList(mId);

        mav.addObject("list",list);
        mav.setViewName("OrderQnAListForm");


        return mav;
    }

    @Override
    public ModelAndView insertOrderQnA(OrderDTO order) throws IOException {


        // 1. 파일 불러오기
        MultipartFile qaFile1 = order.getQaFile1();
        MultipartFile qaFile2 = order.getQaFile2();
        MultipartFile qaFile3 = order.getQaFile3();

        // 2. 파일 이름 불러오기
        String originalFileName1 = qaFile1.getOriginalFilename();
        String originalFileName2 = qaFile2.getOriginalFilename();
        String originalFileName3 = qaFile3.getOriginalFilename();

        // 3. 난수(UUID) 생성하기
        String uuid = UUID.randomUUID().toString().substring(0, 8);

        String qaImage1 = uuid + "_" + originalFileName1;
        String qaImage2 = uuid + "_" + originalFileName2;
        String qaImage3 = uuid + "_" + originalFileName3;


        // 5. 파일 저장 위치 설정 : 파일업로드 상대경로
        Path path1 = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/QnA");
        String savePath1 = path1 + "/" + qaImage1;
        Path path2 = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/QnA");
        String savePath2 = path2 + "/" + qaImage2;
        Path path3 = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/QnA");
        String savePath3 = path3 + "/" + qaImage3;

        // 6. 파일 선택 여부
        if (!qaFile1.isEmpty()) {
            order.setQaImage1(qaImage1);
            qaFile1.transferTo(new File(savePath1));
        }
        if (!qaFile2.isEmpty()) {
            order.setQaImage2(qaImage2);
            qaFile2.transferTo(new File(savePath2));
        }
        if (!qaFile3.isEmpty()) {
            order.setQaImage3(qaImage3);
            qaFile3.transferTo(new File(savePath3));
        }
        int result = odao.insertOrderQnA(order);

        if(result > 0){
            mav.setViewName("redirect:/order/orderQnAList?mId=" + order.getMId());

        }else{
            mav.setViewName("index");
        }

        return mav;

    }

    @Override
    public ModelAndView orderQnAView(String mId) {
        List<OrderDTO> list = odao.orderQnAView(mId);

        mav.setViewName("InsertOrderQnA");
        mav.addObject("list",list);

        return mav;
    }

    @Override
    public ModelAndView QnAView(String qaNum) {

        OrderDTO order = odao.QnAView(qaNum);

        mav.addObject("view",order);
        mav.setViewName("QnAView");

        return mav;
    }

    @Override
    public ModelAndView orderInsertReply(OrderDTO order) {
        int result = odao.orderInsertReply(order);

        if(result > 0 ){
            mav.setViewName("redirect:/order/adminQnAList");
        }else{
            mav.setViewName("index");
        }

        return mav;
    }

    @Override
    public ModelAndView adminQnAList() {
        List<OrderDTO> list = odao.adminQnAList();

        mav.addObject("list",list);
        mav.setViewName("AdminQnAList");

        return mav;
    }


//    1:1문의 삭제 메소드
    @Override
    public ModelAndView orderQnADelete(String qaNum) {

        int result = odao.orderQnADelete(qaNum);

        if(result > 0){
            mav.setViewName("redirect:/order/adminCancelList");
        }else{
            mav.setViewName("index");
        }

        return mav;
    }

    @Override
    public ModelAndView orderQnAUpdateForm(String qaNum,String orNum) {
        OrderDTO order = odao.qnaUpdateInfo(qaNum,orNum);

        mav.addObject("modi",order);
        mav.setViewName("OrderQnAUpdateForm");

        return mav;
    }




    @Override
    public ModelAndView adminOrderList(String pdCode) {
        List<OrderDTO> list = odao.adminOrderList(pdCode);

        mav.setViewName("OrderListForm");
        mav.addObject("olist",list);


        return mav;
    }

    @Override
    public ModelAndView insertOrderState(OrderDTO order) {
        int result = odao.insertOrderState(order);

        if(result > 0){
            mav.setViewName("redirect:/order/PD0002/adminOrderList");
        }else{
            mav.setViewName("index1");
        }



        return mav;
    }

    @Override
    public ModelAndView orderStateModiForm(String orNum) {
        OrderDTO order = odao.orderView(orNum);

        mav.addObject("view",order);
        mav.setViewName("OrderStateModiForm");

        return mav;
    }

    @Override
    public ModelAndView orderStateModify(OrderDTO order) {


        int result = odao.orderStateModify(order);

        if(result > 0 ){
            mav.setViewName("redirect:/order/PD0002/adminOrderList");
            }else{
           mav.setViewName("index");
      }

       return mav;
   }
}
