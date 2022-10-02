package com.iwgh.gain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;


@Data
public class OrderDTO {

    private String orNum;
    private String mId;
    private String pdCode;
    private String orName;
    private String orEmail;
    private String orPhone;
    private String orAddr;
    private String orState;


    private Date orDate;
    private String orQty;
    private String orPrice;

    private String pdName;
    private String pdPrice;
    private String pdQty;


    private String addr1;
    private String addr2;
    private String addr3;



    private String mPw;
    private String mName;
    private Date mBirth;
    private String mGender;
    private String mPhone;
    private String mAddr;
    private String mEmail;
    private Date deleteDay;

    private MultipartFile mProfile;
    private String mProfileName;

    private String pdImage1;


//
    private String osNum;
    private Date osDate;
    private String osReason;
    private String osState;

// 1대1 문의

private String qaNum;
private String qaType;
private String qaName;
private String qaContent;
private Date   qaDate;
private String qaState;
private String qaImage1;
private String qaImage2;
private String qaImage3;
    private MultipartFile qaFile1;
    private MultipartFile qaFile2;
    private MultipartFile qaFile3;


// 관리자 QNA 답변
private Date rpDate;
private String rpAnswer;



}
