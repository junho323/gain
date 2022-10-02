package com.iwgh.gain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Data
public class WishOrderDTO {
    private String orNum;
    private List<String> orName;
    private List<String> orEmail;
    private List<String> orPhone;
    private List<String> orAddr;
    private String orState;
    private Date orDate;
    private String orQty;
    private String orPrice;

    private List<String> pdCode;
    private List<String> pdName;
    private List<Integer> pdPrice;
    private List<Integer> pdQty;


    private List<String> mId;
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

    private String ocNum;
    private Date ocDate;
    private String ocReason;
    private String ocState;


}
