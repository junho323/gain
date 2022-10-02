package com.iwgh.gain.dao;

import com.iwgh.gain.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDAO {

    int insertOrder(OrderDTO order);

    int insertOrder2(OrderDTO order);

//    List<OrderDTO> OrderList(PageDTO paging);

    OrderDTO orderView(String orNum);

    int oCount();


    OrderDTO list(String pdCode,String mId);

    List<OrderDTO> OrderList(String mId,String pdCode);

    OrderDTO orderConfirm(String orNum);


//OrderDTO orderConfirm();

    OrderDTO memberView();

    int insertCancel(OrderDTO order);

    List<OrderDTO> cancelList(String mId);

    List<OrderDTO> adminCancelList();

    int orderDelete(String orNum);

    OrderDTO orderView1(String orNum);

    List<OrderDTO> orderQnAList(String mId);

    int insertOrderQnA(OrderDTO order);

    List<OrderDTO> orderQnAView(String mId);

    OrderDTO QnAView(String qaNum);

    int orderInsertReply(OrderDTO order);

    List<OrderDTO> adminQnAList();

    int orderQnADelete(String qaNum);


    OrderDTO qnaUpdateInfo(String qaNum, String orNum);

    int orderQnAModify(OrderDTO order);

    List<OrderDTO> adminOrderList(String pdCode);

    int insertOrderState(OrderDTO order);

    int orderStateModify(OrderDTO order);
}
