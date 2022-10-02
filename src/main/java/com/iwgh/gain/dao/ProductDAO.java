package com.iwgh.gain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iwgh.gain.dto.PagingDTO;
import com.iwgh.gain.dto.ProductDTO;

@Mapper
public interface ProductDAO {


	int pWrite1(ProductDTO product);

	int pWrite2(ProductDTO product);

	String pdCode(ProductDTO product);

	int pCount();

	List<ProductDTO> ProductList(PagingDTO paging);

	

	ProductDTO pView(String pdCode);


	int p1Count();

	List<ProductDTO> ProductMagazine(PagingDTO paging);




	int ProductDelete1(String pdCode);

	int ProductDelete2(String pdCode);

    int ProductModify(ProductDTO product);

	int p2Count();

	List<ProductDTO> ProductCabinet(PagingDTO paging);

	int p3Count();

	List<ProductDTO> ProductBookshelf(PagingDTO paging);

	int p4Count();

	List<ProductDTO> ProductBed(PagingDTO paging);

	int p5Count();

	List<ProductDTO> ProductSofa(PagingDTO paging);

	int p6Count();

	List<ProductDTO> ProductSofa2(PagingDTO paging);

	int p7Count();

	List<ProductDTO> ProductSofa3(PagingDTO paging);

	int p8Count();

	List<ProductDTO> ProductSofa4(PagingDTO paging);

	int p9Count();

	List<ProductDTO> ProductRecliner(PagingDTO paging);

	int p10Count();

	List<ProductDTO> ProductTable1(PagingDTO paging);

	List<ProductDTO> ProductTable2(PagingDTO paging);

	List<ProductDTO> ProductTable3(PagingDTO paging);

	int p12Count();

	int p11Count();

	int p13Count();

	int p14Count();

	List<ProductDTO> ProductTable5(PagingDTO paging);

	List<ProductDTO> ProductTable4(PagingDTO paging);

	int p19Count();

	int p18Count();

	int p16Count();

	int p17Count();

	int p15Count();

	List<ProductDTO> ProductChair2(PagingDTO paging);

	List<ProductDTO> ProductChair1(PagingDTO paging);

	List<ProductDTO> ProductChair3(PagingDTO paging);

	List<ProductDTO> ProductChair4(PagingDTO paging);

	List<ProductDTO> ProductChair5(PagingDTO paging);

	int ProductDelete3(String pdCode);

	int ProductDelete4(String pdCode);

	int p21Count();

	List<ProductDTO> ProductInterior(PagingDTO paging);

	int p20Count();

	List<ProductDTO> ProductList2(PagingDTO paging);


    List<ProductDTO> ProductSearch(String pdName);

}
