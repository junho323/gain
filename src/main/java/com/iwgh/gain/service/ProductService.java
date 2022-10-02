package com.iwgh.gain.service;

import java.io.IOException;

import org.springframework.web.servlet.ModelAndView;

import com.iwgh.gain.dto.ProductDTO;

import javax.servlet.http.HttpServletRequest;

public interface ProductService {

	ModelAndView pWrite(ProductDTO product, HttpServletRequest request) throws IllegalStateException, IOException;

	ModelAndView ProductList(int page, int limit);

	ModelAndView pView(String pdCode);



	ModelAndView ProductListManagement(int page, int limit);

	ModelAndView ProductDelete(String pdCode);

	ModelAndView ProductModifyForm(String pdCode);


	ModelAndView ProductModify(ProductDTO product);

	ModelAndView ProductMagazine(int page, int limit);

	ModelAndView ProductCabinet(int page, int limit);

	ModelAndView ProductBookshelf(int page, int limit);

	ModelAndView ProductBed(int page, int limit);

	ModelAndView ProductSofa(int page, int limit);
	ModelAndView ProductSofa2(int page, int limit);
	ModelAndView ProductSofa3(int page, int limit);
	ModelAndView ProductSofa4(int page, int limit);

	ModelAndView ProductRecliner(int page, int limit);

	ModelAndView ProductTable1(int page, int limit);

	ModelAndView ProductTable2(int page, int limit);

	ModelAndView ProductTable3(int page, int limit);

	ModelAndView ProductTable4(int page, int limit);

	ModelAndView ProductTable5(int page, int limit);

	ModelAndView ProductChair1(int page, int limit);

	ModelAndView ProductChair2(int page, int limit);

	ModelAndView ProductChair3(int page, int limit);

	ModelAndView ProductChair4(int page, int limit);

	ModelAndView ProductChair5(int page, int limit);

	ModelAndView ProductInterior(int page, int limit);




	ModelAndView ProductSearch(String pdName);

	ModelAndView index1(int page, int limit);
}
