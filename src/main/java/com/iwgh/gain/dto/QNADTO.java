package com.iwgh.gain.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QNADTO extends ProductDTO {
    /*QANUM   NVARCHAR2(6) CONSTRAINT PK_QNA_BNUM PRIMARY KEY,
    QAWRITER NVARCHAR2(20),
    QATITLE NVARCHAR2(50),
    QACONTENT NVARCHAR2(500)*/

    private int qaNum;
    private String qaWriter;
    private String qaTitle;
    private String qaContent;
    private String qaCategory;


}
