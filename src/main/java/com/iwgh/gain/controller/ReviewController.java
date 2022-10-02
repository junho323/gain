package com.iwgh.gain.controller;

import com.iwgh.gain.dto.ReviewDTO;
import com.iwgh.gain.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ReviewController {

    private ModelAndView mav = new ModelAndView();

    @Autowired
    private ReviewService rvsvc;


    @PostMapping("/reviews")
    @ResponseBody
    public int reviewWrite(@RequestBody Map<Object, Object> review){
        System.out.println("리뷰 컨트롤러 : "+review);
        int result = rvsvc.reviewWrite(review);


        return result;
    }

    @GetMapping("/reviews/{pdCode}")
    @ResponseBody
    public List<ReviewDTO> reviewList(@PathVariable("pdCode") String pdCode){
        System.out.println(pdCode);
        List<ReviewDTO> reviews = rvsvc.reviewList(pdCode);
//        System.out.println("컨트롤러 댓글 리스트 : "+reviews);


        return reviews;
    }

    @DeleteMapping("/reviews/{rvNumDel}")
    @ResponseBody
    public void reviewDelete(@PathVariable("rvNumDel") String rvNum){

        rvsvc.reviewDelete(rvNum);


    }




}
