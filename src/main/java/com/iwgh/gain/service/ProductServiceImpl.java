package com.iwgh.gain.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.iwgh.gain.dao.ProductDAO;
import com.iwgh.gain.dto.PagingDTO;
import com.iwgh.gain.dto.ProductDTO;

import javax.servlet.http.HttpServletRequest;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO pdao;

	private ModelAndView mav = new ModelAndView();



	// ProdoctWriteForm : 가구 등록 처리
	@Override
	public ModelAndView pWrite(ProductDTO product, HttpServletRequest request)
			throws IllegalStateException, IOException {
		System.out.println("[2]등록 service : " + product);
		int result1 = pdao.pWrite1(product);
		System.out.println("[3]등록1 service : " + result1);
//		System.out.println("11"+product.getPdFile());
//		MultipartFile pdFile = null;
		int i = 0;
		int lastIndex= 0;
		// MultipartFile pdFile : product.getPdFile()
//		System.out.println("리스트크기"+product.getPdFile().size());
//		MultipartFile pdFile;
		for (MultipartFile pdFile : product.getPdFile()) {
//			pdFile = product.getPdFile().get(i);
			if (i >= 6) {
				break;
			}
			pdFile = product.getPdFile().get(i);
			System.out.println("리스트 파일 목록 : " + product.getPdFile().get(i));
			lastIndex = product.getPdFile().size();
			System.out.println("lastLength : "+lastIndex+" i : "+i);
			// 2. 파일 이름 불러오기 ;
			String originalFileName = pdFile.getOriginalFilename();

			System.out.println("리스트 목록2 : " + originalFileName);

			// 3. 난수(UUID) 생성하기
			String uuid = UUID.randomUUID().toString().substring(0, 8);
			// 4. 3+2
			String pdImage = uuid + "_" + originalFileName;
			System.out.println("리스트 목록3 : " + pdImage);
			// 5. 파일 저장 위치 설정 : 파일업로드 상대경로 Path path1 =
			Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/upload");

			String savePath = path + "/" + pdImage;
			System.out.println("리스트 목록4 : " + savePath);
			if (!pdFile.isEmpty()) {
				System.out.println("들어옴?");
				if (i == 0) {
					product.setPdImage1(pdImage);
				} else if (i == 1) {
					product.setPdImage2(pdImage);
				} else if (i == 2) {
					product.setPdImage3(pdImage);
				} else if (i == 3) {
					product.setPdImage4(pdImage);
				} else if (i == 4) {
					product.setPdImage5(pdImage);
				} else if (i == 5){
					product.setPdModel(pdImage);
				}
			}
			if(i == lastIndex-1){
				if (product.getPdImage2() == null) {
					product.setPdImage2("default.png");
				}
				if (product.getPdImage3() == null) {
					product.setPdImage3("default.png");
				}
				if (product.getPdImage4() == null) {
					product.setPdImage4("default.png");
				}
				if (product.getPdImage5() == null) {
					product.setPdImage5("default.png");
				}
				if (product.getPdModel() == null){
					product.setPdModel("default.glb");
				}
				path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/upload/model");
				savePath = path + "/" + product.getPdModel();
				System.out.println("모델링 파일 저장 경로는? : "+savePath);

			}

			pdFile.transferTo(new File(savePath));
			System.out.println("리스트 목록5 : " + product.getPdImage1());
			System.out.println("리스트 목록5 : " + product.getPdImage2());
			System.out.println("리스트 목록5 : " + product.getPdImage3());
			System.out.println("리스트 목록5 : " + product.getPdImage4());
			System.out.println("리스트 목록5 : " + product.getPdImage5());
			System.out.println("리스트 목록5 : " + product.getPdModel());
			i++;
		}

		String pdCode = pdao.pdCode(product);
		System.out.println("상품코드 : " + pdCode);
		product.setPdCode(pdCode);
		System.out.println("널확인" + product);

		int result2 = pdao.pWrite2(product);

		System.out.println("[3]등록2 service : " + result2);
		if (result1 > 0 && result2 > 0) {
			mav.setViewName("redirect:/product");
		} else {
			mav.setViewName("redirect:/product/write-form");
		}

		return mav;

	}

//  가구 목록 페이지 
	@Override
	public ModelAndView ProductList(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int pCount = pdao.pCount();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) pCount / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductList = pdao.ProductList(paging);

		mav.addObject("ProductList", ProductList);
		mav.addObject("paging", paging);
//		System.out.println(session.getSessionContext());
		mav.setViewName("ProductList");

		return mav;
	}

	@Override
	public ModelAndView pView(String pdCode) {

		ProductDTO product = pdao.pView(pdCode);

		mav.setViewName("ProductViewForm");
		mav.addObject("view", product);

		return mav;
	}




	@Override
	public ModelAndView ProductListManagement(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int pCount = pdao.p20Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) pCount / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductList2 = pdao.ProductList2(paging);

		mav.addObject("ProductList2", ProductList2);
		mav.addObject("paging", paging);
//		System.out.println(session.getSessionContext());
		mav.setViewName("ProductListManagement");

		return mav;
	}

	@Override
	public ModelAndView ProductDelete(String pdCode) {
		System.out.println("[2]service : " + pdCode);

		int result1 = pdao.ProductDelete1(pdCode);

		System.out.println("[3] service1 : " + result1);

		int result2 = pdao.ProductDelete2(pdCode);
		System.out.println("[3] service2 : " + result2);
		int result3 = pdao.ProductDelete3(pdCode);
		System.out.println("[3] service3 : " + result3);
		int result4 = pdao.ProductDelete4(pdCode);
		System.out.println("[3] service4 : " + result4);
		if (result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0) {
			mav.setViewName("redirect:/product/management");
		} else {
			mav.setViewName("redirect:/product/management");
		}

		return mav;
	}

	@Override
	public ModelAndView ProductModifyForm(String pdCode) {
		ProductDTO product = pdao.pView(pdCode);

		mav.setViewName("ProductModifyForm");
		mav.addObject("modi", product);

		return mav;
	}

	@Override
	public ModelAndView ProductModify(ProductDTO product) {
		System.out.println("[2]수정service : " + product);
		int result = pdao.ProductModify(product);
		System.out.println("[3]수정service : " + mav);
		if (result > 0) {
			mav.setViewName("redirect:/product/management");
		} else {
			mav.setViewName("redirect:/product/Modify/{pdCode}");
		}

		return mav;
	}
//매거진랙
	@Override
	public ModelAndView ProductMagazine(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p1Count = pdao.p1Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p1Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductMagazine = pdao.ProductMagazine(paging);

		mav.addObject("ProductMagazine", ProductMagazine);
		mav.addObject("paging", paging);
		mav.setViewName("ProductMagazine");

		return mav;
	}

	@Override
	public ModelAndView ProductCabinet(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p2Count = pdao.p2Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p2Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductCabinet = pdao.ProductCabinet(paging);

		mav.addObject("ProductCabinet", ProductCabinet);
		mav.addObject("paging", paging);
		mav.setViewName("ProductCabinet");

		return mav;
	}

	@Override
	public ModelAndView ProductBookshelf(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p3Count = pdao.p3Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p3Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductBookshelf = pdao.ProductBookshelf(paging);

		mav.addObject("ProductBookshelf", ProductBookshelf);
		mav.addObject("paging", paging);
		mav.setViewName("ProductBookshelf");

		return mav;
	}

	@Override
	public ModelAndView ProductBed(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p4Count = pdao.p4Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p4Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductBed = pdao.ProductBed(paging);

		mav.addObject("ProductBed", ProductBed);
		mav.addObject("paging", paging);
		mav.setViewName("ProductBed");

		return mav;
	}

	@Override
	public ModelAndView ProductSofa(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p5Count = pdao.p5Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p5Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductSofa = pdao.ProductSofa(paging);

		mav.addObject("ProductSofa", ProductSofa);
		mav.addObject("paging", paging);
		mav.setViewName("ProductSofa1");

		return mav;
	}
	@Override
	public ModelAndView ProductSofa2(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p6Count = pdao.p6Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p6Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductSofa2 = pdao.ProductSofa2(paging);

		mav.addObject("ProductSofa2", ProductSofa2);
		mav.addObject("paging", paging);
		mav.setViewName("ProductSofa2");

		return mav;
	}
	@Override
	public ModelAndView ProductSofa3(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p7Count = pdao.p7Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p7Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductSofa3 = pdao.ProductSofa3(paging);

		mav.addObject("ProductSofa3", ProductSofa3);
		mav.addObject("paging", paging);
		mav.setViewName("ProductSofa3");

		return mav;
	}
	@Override
	public ModelAndView ProductSofa4(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p8Count = pdao.p8Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p8Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductSofa4 = pdao.ProductSofa4(paging);

		mav.addObject("ProductSofa4", ProductSofa4);
		mav.addObject("paging", paging);
		mav.setViewName("ProductSofa4");

		return mav;
	}

	@Override
	public ModelAndView ProductRecliner(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p9Count = pdao.p9Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p9Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductRecliner = pdao.ProductRecliner(paging);

		mav.addObject("ProductRecliner", ProductRecliner);
		mav.addObject("paging", paging);
		mav.setViewName("ProductRecliner");
		return mav;
	}

	@Override
	public ModelAndView ProductTable1(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p10Count = pdao.p10Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p10Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductTable1 = pdao.ProductTable1(paging);

		mav.addObject("ProductTable1", ProductTable1);
		mav.addObject("paging", paging);
		mav.setViewName("ProductTable1");
		return mav;
	}

	@Override
	public ModelAndView ProductTable2(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p11Count = pdao.p11Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p11Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductTable2 = pdao.ProductTable2(paging);

		mav.addObject("ProductTable2", ProductTable2);
		mav.addObject("paging", paging);
		mav.setViewName("ProductTable2");
		return mav;
	}

	@Override
	public ModelAndView ProductTable3(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p12Count = pdao.p12Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p12Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductTable3 = pdao.ProductTable3(paging);

		mav.addObject("ProductTable3", ProductTable3);
		mav.addObject("paging", paging);
		mav.setViewName("ProductTable3");
		return mav;
	}

	@Override
	public ModelAndView ProductTable4(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p13Count = pdao.p13Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p13Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductTable4 = pdao.ProductTable4(paging);

		mav.addObject("ProductTable4", ProductTable4);
		mav.addObject("paging", paging);
		mav.setViewName("ProductTable4");
		return mav;
	}
	@Override
	public ModelAndView ProductTable5(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p14Count = pdao.p14Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p14Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductTable5 = pdao.ProductTable5(paging);

		mav.addObject("ProductTable5", ProductTable5);
		mav.addObject("paging", paging);
		mav.setViewName("ProductTable5");
		return mav;
	}
	@Override
	public ModelAndView ProductChair1(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p15Count = pdao.p15Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p15Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductChair1 = pdao.ProductChair1(paging);

		mav.addObject("ProductChair1", ProductChair1);
		mav.addObject("paging", paging);
		mav.setViewName("ProductChair1");
		return mav;
	}
	@Override
	public ModelAndView ProductChair2(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p16Count = pdao.p16Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p16Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductChair2 = pdao.ProductChair2(paging);

		mav.addObject("ProductChair2", ProductChair2);
		mav.addObject("paging", paging);
		mav.setViewName("ProductChair2");
		return mav;
	}

	@Override
	public ModelAndView ProductChair3(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p17Count = pdao.p17Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p17Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductChair3 = pdao.ProductChair3(paging);

		mav.addObject("ProductChair3", ProductChair3);
		mav.addObject("paging", paging);
		mav.setViewName("ProductChair3");
		return mav;
	}

	@Override
	public ModelAndView ProductChair4(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p18Count = pdao.p18Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p18Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductChair4 = pdao.ProductChair4(paging);

		mav.addObject("ProductChair4", ProductChair4);
		mav.addObject("paging", paging);
		mav.setViewName("ProductChair4");
		return mav;
	}

	@Override
	public ModelAndView ProductChair5(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p19Count = pdao.p19Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p19Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductChair5 = pdao.ProductChair5(paging);

		mav.addObject("ProductChair5", ProductChair5);
		mav.addObject("paging", paging);
		mav.setViewName("ProductChair5");
		return mav;
	}

	@Override
	public ModelAndView ProductInterior(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int p21Count = pdao.p21Count();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) p21Count / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductInterior = pdao.ProductInterior(paging);

		mav.addObject("ProductInterior", ProductInterior);
		mav.addObject("paging", paging);
		mav.setViewName("ProductInterior");
		return mav;
	}

	@Override
	public ModelAndView ProductSearch(String pdName) {



		System.out.println("2 /product/Search" + pdName);
		List<ProductDTO> pSearch = pdao.ProductSearch(pdName);

		System.out.println("3 /product/Search" + mav);

		mav.setViewName("ProductSearchList");
		mav.addObject("pSearch", pSearch);

		return mav;
	}

	@Override
	public ModelAndView index1(int page, int limit) {
		// 한화면에 보여줄 페이지 번호 갯수
		int block = 10;

		// 전체 게시글 수
		int pCount = pdao.pCount();

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		int maxPage = (int) (Math.ceil((double) pCount / limit)); // 7
		int startPage = (((int) (Math.ceil((double) page / block))) - 1) * block + 1;
		int endPage = startPage + block - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이지 객체 생성
		PagingDTO paging = new PagingDTO();

		paging.setPage(page);

		paging.setStartRow(startRow);
		paging.setEndRow(endRow);

		paging.setMaxPage(maxPage);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		paging.setLimit(limit);

		List<ProductDTO> ProductList = pdao.ProductList(paging);

		mav.addObject("ProductList", ProductList);
		mav.addObject("paging", paging);
//		System.out.println(session.getSessionContext());
		mav.setViewName("index");

		return mav;
	}


}
