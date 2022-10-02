package com.iwgh.gain.controller;




import com.iwgh.gain.dto.QNADTO;
import com.iwgh.gain.service.QNAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class QNAController {
    private ModelAndView mav = new ModelAndView();

    @Autowired
    private QNAService qsvc;


    @GetMapping("/Q&A")
    public String OrderPostForm() {
        return "QuestionsPostForm";
    }

    @PostMapping("/Q&A/QuestionsPost")
    public ModelAndView QuestionsPost(@ModelAttribute QNADTO Questions) {
        System.out.println("[1]등록 controller : " + Questions);
        mav = qsvc.QuestionsPost(Questions);
        System.out.println("[1]등록 controller : " + mav);
        return mav;
    }
    // bList : 목록
    @GetMapping("/Q&A/QuestionsList")
    public ModelAndView QuestionsList() {
        mav = qsvc.QuestionsList();
        return mav;
    }
    @GetMapping("/Q&A/Modify/{qaNum}")
    public ModelAndView QuestionsModifyForm(@PathVariable("qaNum") int qaNum) {

        mav = qsvc.QuestionsModifyForm(qaNum);

        return mav;
    }
    @PostMapping("/Q&A/Modify/{qaNum}")
    public ModelAndView QuestionsModify(@ModelAttribute QNADTO Questions) {
        System.out.println("[1]수정 controller : " + Questions);

        mav = qsvc.QuestionsModify(Questions);

        System.out.println("[4]수정 controller : " + mav);
        return mav;
    }
    @GetMapping("/Q&A/Delete")
    public ModelAndView QuestionsDelete(@RequestParam("qaNum")  int qaNum) {
        System.out.println("[1]삭제 controller : " + qaNum);
        mav = qsvc.QuestionsDelete(qaNum);

        System.out.println("[1]삭제 controller : " + mav);
        return mav;
    }
}
