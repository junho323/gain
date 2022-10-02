package com.iwgh.gain.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.iwgh.gain.dto.ProductDTO;
import com.iwgh.gain.service.ProductService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
    private ModelAndView mav = new ModelAndView();

    @Autowired
    private ProductService psvc;

	@GetMapping("/")
	public ModelAndView index1(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                         @RequestParam(value = "limit", required = false, defaultValue = "8") int limit) {
        mav = psvc.index1(page, limit);
        return mav;
	}




    // ProductWriteForm : 가구 등록 페이지 이동
    @GetMapping("/product/write-form")
    public String ProductWriteForm() {
        return "ProductAddForm";
    }


    // 가구 등록 처리
    @PostMapping("/product")
    public ModelAndView pWrite(@ModelAttribute ProductDTO product, HttpServletRequest request)
            throws IllegalStateException, IOException {
        System.out.println("request={}" + request);
        System.out.println("[1]등록 controller : " + product);

        mav = psvc.pWrite(product, request);
        System.out.println("[4]등록 controller : " + mav);
        return mav;
    }

    //  가구 목록 페이지
    @GetMapping("/product")
    public ModelAndView ProductList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductList(page, limit);
        return mav;

    }
    //  인테리어 목록 페이지
    @GetMapping("/product/Interior")
    public ModelAndView ProductInterior(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "12") int limit) {
        mav = psvc.ProductInterior(page, limit);
        return mav;

    }

    // /productView : 상세보기
    @GetMapping("/product/{pdCode}")
    public ModelAndView pView(@PathVariable("pdCode") String pdCode) {
        mav = psvc.pView(pdCode);
        return mav;
    }


    // 매거진랙 목록 페이지
    @GetMapping("/product/magazine")
    public ModelAndView ProductMagazine(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductMagazine(page, limit);
        return mav;

    }
    // 장식장 목록 페이지
    @GetMapping("/product/cabinet")
    public ModelAndView ProductCabinet(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductCabinet(page, limit);
        return mav;

    }
    // 책장 목록 페이지
    @GetMapping("/product/Bookshelf")
    public ModelAndView ProductBookshelf(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductBookshelf(page, limit);
        return mav;

    }
    // 침대 목록 페이지
    @GetMapping("/product/Bed")
    public ModelAndView ProductBed(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductBed(page, limit);
        return mav;

    }
    // 일반소파 목록 페이지
    @GetMapping("/product/Sofa1")
    public ModelAndView ProductSofa(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductSofa(page, limit);
        return mav;

    }
    // 일반소파 목록 페이지
    @GetMapping("/product/Sofa2")
    public ModelAndView ProductSofa2(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductSofa2(page, limit);
        return mav;

    }
    // 일반소파 목록 페이지
    @GetMapping("/product/Sofa3")
    public ModelAndView ProductSofa3(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductSofa3(page, limit);
        return mav;

    }
    // 일반소파 목록 페이지
    @GetMapping("/product/Sofa4")
    public ModelAndView ProductSofa4(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductSofa4(page, limit);
        return mav;

    }
    // 리클라이너 목록 페이지
    @GetMapping("/product/Recliner")
    public ModelAndView ProductRecliner(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                     @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductRecliner(page, limit);
        return mav;

    }// 사이드 테이블 목록 페이지
    @GetMapping("/product/Table1")
    public ModelAndView ProductTable1(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductTable1(page, limit);
        return mav;

    }// 좌식 테이블 목록 페이지
    @GetMapping("/product/Table2")
    public ModelAndView ProductTable2(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductTable2(page, limit);
        return mav;

    }// 소파 테이블 목록 페이지
    @GetMapping("/product/Table3")
    public ModelAndView ProductTable3(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductTable3(page, limit);
        return mav;

    }// 식탁 목록 페이지
    @GetMapping("/product/Table4")
    public ModelAndView ProductTable4(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductTable4(page, limit);
        return mav;

    }// 책상 목록 페이지
    @GetMapping("/product/Table5")
    public ModelAndView ProductTable5(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductTable5(page, limit);
        return mav;

    }// 책상 목록 페이지
    @GetMapping("/product/Chair1")
    public ModelAndView ProductChair1(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductChair1(page, limit);
        return mav;

    }// 책상 목록 페이지
    @GetMapping("/product/Chair2")
    public ModelAndView ProductChair2(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductChair2(page, limit);
        return mav;

    }// 책상 목록 페이지
    @GetMapping("/product/Chair3")
    public ModelAndView ProductChair3(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductChair3(page, limit);
        return mav;

    }// 책상 목록 페이지
    @GetMapping("/product/Chair4")
    public ModelAndView ProductChair4(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductChair4(page, limit);
        return mav;

    }// 책상 목록 페이지
    @GetMapping("/product/Chair5")
    public ModelAndView ProductChair5(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductChair5(page, limit);
        return mav;

    }
    @GetMapping("/product/management")
    public ModelAndView ProductListManagement(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                              @RequestParam(value = "limit", required = false, defaultValue = "9") int limit) {
        mav = psvc.ProductListManagement(page, limit);
        return mav;

    }

    //	/Product/Delete/'+${list.pdCode}}
    @GetMapping("/product/Delete")
    public ModelAndView ProductDelete(@RequestParam("pdCode") String pdCode) {
        System.out.println("[1]삭제 controller : " + pdCode);
        mav = psvc.ProductDelete(pdCode);

        System.out.println("[1]삭제 controller : " + mav);
        return mav;
    }

    @GetMapping("/product/Modify/{pdCode}")
    public ModelAndView ProductModifyForm(@PathVariable("pdCode") String pdCode) {

        mav = psvc.ProductModifyForm(pdCode);

        return mav;
    }
    @PostMapping("/product/Modify/{pdCode}")
    public ModelAndView ProductModify(@ModelAttribute ProductDTO product) {
        System.out.println("[1]수정 controller : " + product);
        mav = psvc.ProductModify(product);
        System.out.println("[4]수정 controller : " + mav);
        return mav;
    }
    ///product/Search 검색
    @RequestMapping(value ="/product/Search" ,method=RequestMethod.POST)
    public ModelAndView ProductSearch(@RequestParam("pdName")String pdName)  {
        System.out.println("1 /product/Search" + pdName);
        mav = psvc.ProductSearch(pdName);
        System.out.println("4 /product/Search"+mav);
        return mav;
    }

}
