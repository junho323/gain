package com.iwgh.gain.controller;

import com.iwgh.gain.dto.OrderDTO;
import com.iwgh.gain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Controller
public class OrderController {

    private ModelAndView mav = new ModelAndView();

    @Autowired
    private OrderService osvc;


//    @GetMapping("/order/order-form")
//    public String orderForm() {
//
//
//        return "InsertOrderQnA";
//    }

    // 직접 입력시 주문
    @PostMapping("/order/{pdCode}/insertOrder")
    public ModelAndView insertOrder(@ModelAttribute OrderDTO order, @PathVariable(value = "pdCode") String pdCode) {
        System.out.println("[1]등록 Controller : " + order);
        mav = osvc.insertOrder(order);
        System.out.println("[4]등록 Controller : " + mav);
        return mav;
    }

    // 멤버 정보 가져와서 입력 후 주문
    @PostMapping("/order/{pdCode}/insertOrder2")
    public ModelAndView insertOrder2(@ModelAttribute OrderDTO order, @PathVariable(value = "pdCode") String pdCode) {
        System.out.println("[1]등록 Controller : " + order);
        mav = osvc.insertOrder2(order);
        System.out.println("[4]등록 Controller : " + mav);
        return mav;
    }

    // QnA 등록
    @PostMapping("/order/{pdCode}/insertOrderQnA")
    public ModelAndView insertOrderQnA(@ModelAttribute OrderDTO order, @PathVariable(value = "pdCode") String pdCode) throws IOException {

        mav = osvc.insertOrderQnA(order);

        return mav;
    }

    @PostMapping("/order/insertCancel")
    public ModelAndView insertCancel(@ModelAttribute OrderDTO order) {
        System.out.println("[1]취소 등록 Controller : " + order);
        mav = osvc.insertCancel(order);
        System.out.println("[4]취소 등록 Controller : " + mav);
        return mav;
    }

    @GetMapping("/order/cancelList")
    public ModelAndView cancelList(@RequestParam("mId") String mId) {

        mav = osvc.cancelList(mId);

        return mav;
    }

    @GetMapping("/order/PD0002/adminCancelList")
    public ModelAndView adminCancelList() {

        mav = osvc.adminCancelList();

        return mav;
    }

    // 질문 리스트 보기
    @GetMapping("/order/orderQnAList")
    public ModelAndView orderQnAList(@RequestParam("mId") String mId) {

        mav = osvc.orderQnAList(mId);

        return mav;
    }

    @GetMapping("/order/OrderQnAView/{mId}")
    public ModelAndView orderQnAView(@PathVariable("mId") String mId) {

        mav = osvc.orderQnAView(mId);

        return mav;
    }

    @GetMapping("/order/{pdCode}/orderDelete")
    public ModelAndView orderDelete(@RequestParam("orNum") String orNum) {

        mav = osvc.orderDelete(orNum);

        return mav;
    }


    @GetMapping("order/{pdCode}/OrderList")
    public ModelAndView OrderList(@RequestParam("mId") String mId, @PathVariable("pdCode") String pdCode) {

        mav = osvc.OrderList(mId, pdCode);

        return mav;
    }

//    관리자 주문 리스트
    @GetMapping("order/{pdCode}/adminOrderList")
    public ModelAndView adminOrderList( @PathVariable("pdCode") String pdCode) {

        mav = osvc.adminOrderList( pdCode);

        return mav;
    }


    @GetMapping("/order/{pdCode}/{mId}")
    public ModelAndView list(@PathVariable("pdCode") String pdCode, @PathVariable("mId") String mId) {

        mav = osvc.list(pdCode, mId);

        return mav;
    }

    @GetMapping("/order/orderView")
    public ModelAndView orderView( @RequestParam("orNum") String orNum) {

        mav = osvc.orderView(orNum);

        return mav;
    }

    //    관리자 모드에서 주문 상세보기
    @GetMapping("/order/orderView1")
    public ModelAndView orderView1( @RequestParam("orNum") String orNum) {

        mav = osvc.orderView1(orNum);

        return mav;
    }

    @GetMapping("/order/QnAView")
    public ModelAndView QnAView( @RequestParam("qaNum") String qaNum) {

        mav = osvc.QnAView(qaNum);

        return mav;
    }


    @GetMapping("/order/orderConfirm")
    public ModelAndView orderConfirm(@RequestParam("orNum") String orNum) {

        mav = osvc.orderConfirm(orNum);

        return mav;
    }


    @GetMapping("/order/memberView")
    public ModelAndView memberView() {

        mav = osvc.memberView();

        return mav;
    }

    @PostMapping("/order/orderInsertReply")
    public ModelAndView orderInsertReply(OrderDTO order) {

        mav = osvc.orderInsertReply(order);
        return mav;
    }

    @GetMapping("/order/adminQnAList")
    public ModelAndView adminQnAList() {

        mav = osvc.adminQnAList();

        return mav;
    }

    @GetMapping("/order/orderQnADelete")
    public ModelAndView orderQnADelete(@RequestParam("qaNum") String qaNum) {

        mav = osvc.orderQnADelete(qaNum);

        return mav;
    }

    @GetMapping("/order/orderQnAUpdateForm/{qaNum}/{orNum}")
    public ModelAndView orderQnAUpdateForm(@PathVariable("qaNum") String qaNum, @PathVariable("orNum") String orNum) {

        mav = osvc.orderQnAUpdateForm(qaNum, orNum);

        return mav;
    }



    @PostMapping("/order/insertOrderState")
    public ModelAndView insertOrderState(@ModelAttribute OrderDTO order) {
        System.out.println("[1]상태 등록 Controller : " + order);
        mav = osvc.insertOrderState(order);
        System.out.println("[4]상태 등록 Controller : " + mav);
        return mav;
    }


    @GetMapping("/order/orderStateModiForm")
    public ModelAndView orderStateModiForm(@RequestParam("orNum")String orNum){

        mav = osvc.orderStateModiForm(orNum);

        return mav;
    }



    @PostMapping("/order/orderStateModify")
    public ModelAndView orderStateModify(@ModelAttribute OrderDTO order) {

        mav = osvc.orderStateModify(order);

        return mav;
    }

}