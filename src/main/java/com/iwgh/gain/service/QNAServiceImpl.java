package com.iwgh.gain.service;



import com.iwgh.gain.dao.QNADAO;
import com.iwgh.gain.dto.QNADTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Service
public class QNAServiceImpl implements QNAService {

    @Autowired
    private QNADAO qdao;

    private ModelAndView mav = new ModelAndView();


    @Override
    public ModelAndView QuestionsPost(QNADTO Questions) {
        int result = qdao.QuestionsPost(Questions);


        if (result > 0 ) {
            mav.setViewName("redirect:/Q&A/QuestionsList");
        } else {
            mav.setViewName("redirect:/Q&A");
        }

        return mav;
    }

    @Override
    public ModelAndView QuestionsList() {
        List<QNADTO> QuestionsList = qdao.QuestionsList();

        mav.setViewName("QuestionsListForm");
        mav.addObject("QuestionsList", QuestionsList);

        return mav;
    }

    @Override
    public ModelAndView QuestionsModifyForm(int qaNum) {

        QNADTO QuestionsModifyForm = qdao.qView(qaNum);

        mav.setViewName("QuestionsModifyForm");
        mav.addObject("modi", QuestionsModifyForm);

        return mav;
    }

    @Override
    public ModelAndView QuestionsModify(QNADTO Questions) {
        System.out.println("[2]수정service : " + Questions);
        int result = qdao.QuestionsModify(Questions);
        System.out.println("[3]수정service : " + mav);
        if (result > 0) {
            mav.setViewName("redirect:/Q&A/QuestionsList");
        } else {
            mav.setViewName("redirect:/Q&A/Modify/{qaNum}");
        }

        return mav;
    }

    @Override
    public ModelAndView QuestionsDelete(int qaNum) {
        System.out.println("[2]service : " + qaNum);

        int result1 = qdao.QuestionsDelete(qaNum);

        System.out.println("[3] service : " + result1);

        if (qaNum > 0 ) {
            mav.setViewName("redirect:/Q&A/QuestionsList");
        } else {
            mav.setViewName("redirect:/Q&A/QuestionsList");
        }

        return mav;
    }
}
