package com.iwgh.gain.dao;

import com.iwgh.gain.dto.ProductDTO;
import com.iwgh.gain.dto.QNADTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QNADAO {


    int QuestionsPost(QNADTO Questions);

    List<QNADTO> QuestionsList();

    QNADTO qView(int qaNum);

    int QuestionsModify(QNADTO Questions);

    int QuestionsDelete(int qaNum);
}
