package com.iwgh.gain.service;

import com.iwgh.gain.dto.OrderDTO;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface OrderService {
    ModelAndView insertOrder(OrderDTO order);

    ModelAndView insertOrder2(OrderDTO order);
//    ModelAndView OrderList(int page, int limit);

    ModelAndView orderView(String orNum);

    ModelAndView list(String pdCode,String mId);

    ModelAndView OrderList(String mId,String pdCode);

    ModelAndView orderConfirm(String orNum);


//ModelAndView orderConfirm();

    ModelAndView memberView();

    ModelAndView insertCancel(OrderDTO order);

    ModelAndView cancelList(String mId);

    ModelAndView adminCancelList();

    ModelAndView orderDelete(String orNum);

    ModelAndView orderView1(String orNum);

    ModelAndView orderQnAList(String mId);

    ModelAndView insertOrderQnA(OrderDTO order) throws IOException;

    ModelAndView orderQnAView(String mId);

    ModelAndView QnAView(String qaNum);

    ModelAndView orderInsertReply(OrderDTO order);

    ModelAndView adminQnAList();

    ModelAndView orderQnADelete(String qaNum);

    ModelAndView orderQnAUpdateForm(String qaNum,String orNum);

    ModelAndView adminOrderList(String pdCode);

    ModelAndView insertOrderState(OrderDTO order);

    ModelAndView orderStateModiForm(String orNum);

    ModelAndView orderStateModify(OrderDTO order);

//    ModelAndView orderQnAModify(OrderDTO order) throws IOException;
}
