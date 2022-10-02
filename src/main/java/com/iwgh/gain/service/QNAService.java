package com.iwgh.gain.service;

import com.iwgh.gain.dto.ProductDTO;
import com.iwgh.gain.dto.QNADTO;
import org.springframework.web.servlet.ModelAndView;

public interface QNAService {

    ModelAndView QuestionsPost(QNADTO Questions);

    ModelAndView QuestionsList();

    ModelAndView QuestionsModifyForm(int qaNum);


    ModelAndView QuestionsModify(QNADTO Questions);

    ModelAndView QuestionsDelete(int qaNum);
}
