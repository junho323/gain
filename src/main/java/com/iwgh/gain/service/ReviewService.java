package com.iwgh.gain.service;


import com.iwgh.gain.dto.ReviewDTO;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    int reviewWrite(Map<Object, Object> review);

    List<ReviewDTO> reviewList(String pdCode);

    void reviewDelete(String rvNum);
}
