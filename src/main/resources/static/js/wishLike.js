
// 1. 매개변수로 받아오기
// 2. ajax로 서버로 보내기
// 3. db state 변경
// 4. db state 값에 따라 조취하기

//const heart = document.querySelectorAll(".icon_heart_alt");
//const plists = document.querySelectorAll(".product__item  .icon_heart_alt");

//let clickElem = ''
//plist[0].parentElement.parentElement.parentElement.parentElement.nextElementSibling.children[0].children[1].innerText


// plist[0].previousElementSibling.innerText


//const pdCode = '';
function wishLike(mId,pdCode,event){
//    clickElem = event;

//    for (const plist of plists) {
//      plist.addEventListener("click", function(event) {
//            console.log("찜하기 누른 버튼 정보 : "+plist);
//      });
//    }


    console.log("찜하기 누른 사용자 정보 : "+mId+", "+pdCode);

    const info = {mId:mId, pdCode:pdCode};
    if(mId != null){
        $.ajax({
            type : "post",
            url : "/member/wish",
            data : JSON.stringify(info),
            contentType : 'application/json; charset-utf-8',
            dataType : 'json',
            success : function(data){
                wishLikeState(mId,pdCode,event);
                console.log(event);
                if(data == 1){

//                    alert('찜하기 성공');

//                       plists[0].parentElement.style.background = '#ca1515';
//                    .product__hover li:hover a
//                    	background: #ca1515;
                }else{

//                    alert('찜하기 취소');
//                    heart.style.background= '#ffffff';
                }
            },
            error : function(){
                alert('통신 실패');
            }


        });
    }else{
        alert('로그인 후 이용 가능합니다!');
        location.href='/member/login-form';
    }


}
//const id = '';
//const pd = '';
//$(document).ready( function () {
//
//
//
//});
//let wishCode =  $('#wish-val').val();

function wishLikeState(mId,pdCode, event){
                const info = {mId:mId, pdCode:pdCode};
                        console.log("찜하기 상태 점검");
                        $.ajax({
                            type : "POST",
                            url : "/member/wish-state",
                            data : JSON.stringify(info),
                            contentType : 'application/json; charset-utf-8',
                            dataType : 'json',
                            success : function(data){
//                                console.log("wishCode "+wishCode);
                                wishLikeState2(mId);
                                if(data == 1){
                                    console.log("찜하기 누른 사용자와 찜된 상품정보와 상태 : "+mId+", "+pdCode+", "+data);
//                                    heart.style.background= '#ca1515';
                                    event.style.background = '#ca1515';


                //                    .product__hover li:hover a
                //                    	background: #ca1515;
                                }else{

                                   console.log("찜하기 누른 사용자와 찜된 상품정보 상태: "+mId+", "+pdCode+", "+data);
                //                    heart.style.background= '#ffffff';
                                     event.style.background = '#ffffff';

                                }
                            },
                            error : function(){
                                alert('통신 실패');
                            }


                        });



}
let wishHeart = '';
let wcnt;

function wishLikeState2(mId){

                if(window.location.pathname.split("/").length == 3 && window.location.pathname.split("/")[1] == "product" && window.location.pathname.split("/")[2].indexOf("PD") != -1){
                    reviewList(window.location.pathname.split("/")[2]);
                }

//                console.log("바디실행 위라스2 아이디 : "+mId);
                const info = {mId:mId};

                if(mId != null){
                        if(location.href.slice("/").search("wish") === 29){
//                            setWishPdData(mId);
//                            loadWishListResult(wishPdData);
                        }
//                        console.log("찜한 사용자 정보 : "+mId);
                        $.ajax({
                            type : "POST",
                            url : "/wish-state2",
                            data : JSON.stringify(info),
                            contentType : 'application/json; charset-utf-8',
                            dataType : 'JSON',
                            success : function(data){
                                wcnt = data.length;
                                $('.tip').html(wcnt);
//                                console.log("wishCode : "+data.length);
                                   $("a[id^='pdcode-get-text']").each(function(i){
//                                        console.log("each : "+$(this).prop('href').slice(-6));
                                        for(let i = 0; i < data.length; i++){
                                        //console.log("href : "+$("#pdcode-get-text")[i].prop('href').slice(-6));
//                                           console.log("data[i].pdCode : "+data[i].pdCode);

                                           if($(this).prop('href').slice(-6) == data[i].pdCode){
//                                                console.log('일치합니다!');
                                                 wishHeart = $(this).get(0);
//                                                 wishHeart.parentElement.parentElement.previousElementSibling.children[2].children[1].children[0].style.background ='#ca1515';

                                                   if(window.location.pathname.split("/").length == 2 ||  window.location.pathname.split("/")[2].indexOf("PD") == -1){
                                                        wishHeart.parentElement.parentElement.previousElementSibling.children[1].children[1].children[0].style.background ='#ca1515';


                                                   }else if(window.location.pathname.split("/").length ==3 ){
                                                        wishHeart.nextElementSibling.children[0].children[0].style.background ='#ca1515';


                                                   }

//                                                $("#heart-wish").css('background-color','#ca1515');
                                           }

                                        }

                                   });





                            },
                            error : function(){
                                alert('통신 실패');
                            }


                        });
                }



}
//
//function wishStateResult(id){
//    console.log("onload success : "+id);
//    $.ajax({
//        type : "POST",
//        url : "/member/wish-state-result",
//        data : {"id":id},
//        dataType : 'text',
//        success : function(){
//            alert("통신 성공");
//
//        },
//        error : function(){
//            alert('통신 실패');
//        }
//
//
//    });
//
//
//}

//const id_wish = document.getElementById("session-id").innerText;
//window.onload = function(){
//       wishStateResult(id);
//}
//$(document).ready( function () {
//
//
//
//});
//$(function(){
//    console.log("id : "+id);
//   wishStateResult(id);

//    wishLikeState(mId,pdCode,event);
//});

