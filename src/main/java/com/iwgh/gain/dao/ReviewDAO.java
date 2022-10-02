package com.iwgh.gain.dao;

import com.iwgh.gain.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewDAO {
    int reviewWrite(Map<Object, Object> review);

    List<ReviewDTO> reviewList(String pdCode);

    int reviewDelete(String rvNum);
}
