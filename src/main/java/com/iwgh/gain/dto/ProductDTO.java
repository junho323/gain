package com.iwgh.gain.dto;


import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductDTO {
	//      PDCODE NVARCHAR2(6) CONSTRAINT PK_PRODUCT_PDCODE PRIMARY KEY,
//	    PDNAME NVARCHAR2(10),
//	    PDPRICE NUMBER,
//	    PDQTY NUMBER,
//	    PDDETAIL NVARCHAR2(300),
//	    PDCOUNT NUMBER,
//	    PDSTARPOINT NUMBER,
//	    PDCATEGORY NVARCHAR2(10)
	private String pdCode;
	private String pdName;
	private int pdPrice;
	private int pdQty;
	private String pdDetail;
	private int pdCount;
	private double pdStarpoint;
	private String pdCategory;
	private String pdModel;


//		PDCODE   NVARCHAR2(6),
//	    PDIMAGE1 NVARCHAR2(100),
//	    PDIMAGE2 NVARCHAR2(100),
//	    PDIMAGE3 NVARCHAR2(100),
//	    PDIMAGE4 NVARCHAR2(100),
//	    PDIMAGE5 NVARCHAR2(100),

	/*
	 * private String pdImage1;
	 *
	 * private String pdImage2; private String pdImage3; private String pdImage4;
	 * private String pdImage5; private MultipartFile pdFile1; private MultipartFile
	 * pdFile2; private MultipartFile pdFile3; private MultipartFile pdFile4;
	 * private MultipartFile pdFile5;
	 */

	private String pdImage1;
	private String pdImage2;
	private String pdImage3;
	private String pdImage4;
	private String pdImage5;

	private List<MultipartFile> pdFile;



}
