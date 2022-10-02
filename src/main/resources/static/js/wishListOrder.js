
    let wishQtyCnt = document.getElementById('qtyPrice').value;
    let resultWishPrice = document.getElementById('result_wish_price');
    let qtyCurrent;




    let result;
//
//    function wishPriceResult(price, qty ,wishQtyCnt){
//            qtyCurrent = qty;
//    <!--    console.log(price);-->
//    <!--    console.log(parseInt(wishQtyCnt));-->
//<!--        console.log((price * parseInt(wishQtyCnt)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+"원");-->
//        console.log("남은재고 : "+qty);
//        if(parseInt(wishQtyCnt) <= qty){
//            result = (price * parseInt(wishQtyCnt)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
//            resultWishPrice.innerText = result;
//        }else{
//            result = "재고부족";
//            resultWishPrice.innerText = result;
//            console.log(result);
//        }
//
//
//    }


    function priceCntCheck(cnt){

        if(parseInt(cnt) < 1){
            console.log("1이하로 내려감");
            wishQtyCnt = 1;
        }
    }

 let totalQtyResult = 0;
 let totalPriceResult = 0;
 let qtyCntCheck = false;
    function wishPlusQty(price, qty, btn){
<!--        console.log(btn.parentElement.nextElementSibling.firstChild);-->
            loadOrderModal();
        if(parseInt(btn.previousElementSibling.children[1].value) > qty){
            alert("재고부족\n남은수량 : "+qty);
            btn.previousElementSibling.children[1].value =  parseInt(qty);
            if(qty == 0){
                btn.previousElementSibling.children[1].value = 1;
            }
        }else{
             btn.previousElementSibling.children[1].value =  parseInt(btn.previousElementSibling.children[1].value) + 1;
        }

        result = (price * parseInt(btn.previousElementSibling.children[1].value)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
            btn.parentElement.nextElementSibling.firstChild.innerText = result;


//        console.log("총 수량 "+wishProductTotalQty(totalQtyResult));
        totalCntWishText.innerText = wishProductTotalQty(totalQtyResult)+"개";
        totalPriceWishText.innerText = (wishProductTotalPrice(totalPriceResult)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
    }

        function wishMinusQty(price ,qty, btn2){
            loadOrderModal();
        if(parseInt(btn2.nextElementSibling.children[1].value) == 1){
            btn2.nextElementSibling.children[1].value = parseInt(1);
        }else{
            btn2.nextElementSibling.children[1].value =  parseInt(btn2.nextElementSibling.children[1].value) - 1;
        }
        result = (price * parseInt(btn2.nextElementSibling.children[1].value)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';
            btn2.parentElement.nextElementSibling.firstChild.innerText = result;



//        console.log("총 수량 "+wishProductTotalQty(totalQtyResult));
//        console.log("총 가격 "+wishProductTotalPrice(totalPriceResult));
        totalCntWishText.innerText = wishProductTotalQty(totalQtyResult)+"개";
        totalPriceWishText.innerText = (wishProductTotalPrice(totalPriceResult)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';


    }


let totalCntWishText = document.getElementById("total_cnt_wish");
let totalPriceWishText = document.getElementById("total_price_wish");
let totalPdCntWish = document.getElementById("total_pdcnt_wish");






function wishProductTotalQty (totalQtyResult){

       for(let i = 0; i < document.querySelectorAll("#qtyPrice").length; i++){
           totalQtyResult += parseInt(document.querySelectorAll("#qtyPrice")[i].value);


       }

       return totalQtyResult;

   };


//function minusWishProductTotalQty(totalQtyResult){
//     for(let i = 0; i < document.querySelectorAll("#qtyPrice").length; i++){
//         totalQtyResult -= parseInt(document.querySelectorAll("#qtyPrice")[i].value);
//
//     }
//
//
//     return totalQtyResult;
// };


//document.querySelectorAll("#result_wish_price")[0].innerText.split("원")[0].split(",").join("");
function wishProductTotalPrice(totalPriceResult){
     for(let i = 0; i < document.querySelectorAll("#result_wish_price").length; i++){
          totalPriceResult += parseInt(document.querySelectorAll("#result_wish_price")[i].innerText.split("원")[0].split(",").join(""));

     }


     return totalPriceResult;
 };


 function priceAndQtyLoad(){
    if(document.URL.indexOf("wish") > 1){
//        console.log("총 수량 "+wishProductTotalQty(totalQtyResult));
//        console.log("총 가격 "+wishProductTotalPrice(totalPriceResult));

        totalPdCntWish.innerText = document.querySelectorAll("#qtyPrice").length+"개";
        totalCntWishText.innerText = wishProductTotalQty(totalQtyResult)+"개";
        totalPriceWishText.innerText = (wishProductTotalPrice(totalPriceResult)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+'원';

    }

 }

// function minusWishProductTotalPrice(totalPriceResult){
//      for(let i = 0; i < document.querySelectorAll("#result_wish_price").length; i++){
//          totalPriceResult -= parseInt(document.querySelectorAll("#result_wish_price")[i].value);
//
//      }
//
//
//      return totalPriceResult;
//  };


    let wishPdList = [];

    const wishPdData = [];

    function setWishPdData(mId){

        wishPdData.length = 0;
        for(let i = 0; i < document.querySelectorAll("#result_wish_price").length; i++){
              wishPdData.push({pdCode : document.querySelectorAll("#result_wish_price")[i].parentElement.parentElement.children[0].children[1].children[0].href.substr(-6),
                                pdName : document.querySelectorAll("#result_wish_price")[i].parentElement.parentElement.children[0].children[1].children[0].children[0].innerText,
                                pdQty : parseInt(document.querySelectorAll("#qtyPrice")[i].value),
                                pdPrice : parseInt(document.querySelectorAll("#result_wish_price")[i].innerText.split("원")[0].split(",").join("")),
                                pdTotalPrice : wishProductTotalPrice(totalPriceResult),
                               });
        }
        loadWishListResult(wishPdData);
//        console.log("mid는 잘 받아오고 있지? "+mId);
//        $.ajax({
//            type : "get",
//            url : "/orders/"+mId,
//            data : JSON.stringify(wishPdData),
//            contentType : 'application/json; charset-utf-8',
//            success : function(){
//                alert('통신성공');
//                        $.ajax({
//                            type : "post",
//                            url : "/orders/"+mId,
//                            data : JSON.stringify(wishPdData),
//                            contentType : 'application/json; charset-utf-8',
//                            success : function(){
//
////                                alert('통신성공');
//                                loadOrderModal();
////                                location.href="/orders/"+mId;
//                                console.log("주문창까지 온 뒤에 정보값들 : "+wishPdData);
//
//                            },
//                            error : function(){
//                                alert('통신에러');
//                            }
//                        });
//                location.href="/orders/"+mId;
//            },
//            error : function(){
//                alert('통신에러');
//            }
//        });
    }

//function orderWishInfoList(){
//        $("#wish_order_pd_name").text = wishPdData[0].pdName + "외 "+wishPdData.length+"건";
//}



let pdQtyCntCheck = true;
    function lastCheckQty(wishQtyList){
//        for(let i = 0; i < document.querySelectorAll("#result_wish_price").length; i++){
//            if(parseInt(btn.previousElementSibling.children[i].value) > qty){
//                alert(wishPdData[i].pdName+" 상품의 재고가 부족합니다.\n남은수량 : "+qty);
//                btn.previousElementSibling.children[i].value =  parseInt(qty);
//            }
//        }
//        console.log("wishQtyList  : "+wishQtyList);
//        console.log("wishQtyList length : "+wishQtyList.length);
//        console.log(wishQtyList[2]);
//        console.log(wishPdData[2].pdQty);


//        alert("사용자가 담은 수량 체크 하기2 : "+wishPdData[0].pdQty);
         for(let i = 0; i < wishQtyList.length; i++){
            console.log(wishPdData[i].pdQty +"-"+ wishQtyList[i]+" = "+wishPdData[i].pdQty - wishQtyList[i]);
            if((wishQtyList[i] - wishPdData[i].pdQty) < 0){
                alert(wishPdData[i].pdName+"의 수량이 부족합니다.")
                pdQtyCntCheck = false;
            }else{

            }


         }
         if(pdQtyCntCheck == true){

//              document.getElementById("form2").submit();
            let okPdQty = payment2Click();
            if(okPdQty == true){
                payment3();
            }
         }
    }



//function loadWishListResult(wishPdData){






//    let output = "";
//    output += "<div class='modal fade' id='multiOrderModal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel'aria-hidden='true'>";
//    output += "<div class='modal-dialog' role='document'>";
//    output += "<div class='modal-content'>";
//    output += "<div class='modal-header'>";
//    output += "<h5 class='modal-title' id='exampleModalLabel'>주문 정보입력</h5>";
//    output += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'>";
//    output += "<span aria-hidden='true'>&times;</span>";
//    output += "</button>";
//    output += "</div>";
//    output += "<div class='modal-body'>";
//    output += "<h5 th:text='주문자 :  "+[[${member?.mName}]]+"  님'></h5><th:block th:each='"+[[list, i:${list}]]+"'><th:block th:if='"+[[${i.index} < 1]]+"'><input type='hidden' name='pdCode' th:value='"+${list?.pdCode}+"'></th:block></th:block>";
//    output += "</div>";
//    output += "</div>";
//    output += "</div>";
//    output += "</div>";
//    output += "</div>";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";
    // output += "";

//    document.getElementById("order_modal_area").innerHTML = output;
//}