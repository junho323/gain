package com.iwgh.gain.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ReviewDTO {

    private String mId;
    private String mName;
    private String mProfileName;
    private String rvNum;
    private String pdCode;
    private String review;
    private Date rvDate;
    private double starScore;
}
