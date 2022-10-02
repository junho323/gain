package com.iwgh.gain.service;

import com.iwgh.gain.dao.ReviewDAO;
import com.iwgh.gain.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService{


    @Autowired
    private ReviewDAO rvdao;

    @Override
    public int reviewWrite(Map<Object, Object> review) {

        int result = rvdao.reviewWrite(review);
        if(result >  0){
            System.out.println("댓글작성 성공");
        }

        return result;
    }

    @Override
    public List<ReviewDTO> reviewList(String pdCode) {

        List<ReviewDTO> reviews = rvdao.reviewList(pdCode);
        System.out.println("댓글 리스트 : "+reviews);

        return reviews;
    }

    @Override
    public void reviewDelete(String rvNum) {

        rvdao.reviewDelete(rvNum);


    }
}
