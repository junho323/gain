package com.iwgh.gain.dto;


import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@Alias("member")
public class MemberDTO {

    private String mId;
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

    // 주소 api를 위한 필드 선언
    private String addr1;
    private String addr2;
    private String addr3;

}
