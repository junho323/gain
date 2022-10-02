const checkAgree1 = document.querySelector("#checkAgree1");
var checkAgree2 = document.getElementById("checkAgree2");

var form2 = document.getElementById("form2");
var orName = document.getElementById("orName");
var orEmail = document.getElementById("orEmail");
var orPhone = document.getElementById("orPhone");
var sample6_postcode = document.getElementById("sample6_postcode");
var sample6_detailAddress = document.getElementById("sample6_detailAddress");
var orAddr = document.getElementById("sample6_postcode1");



//$(document).ready(function(){
//	$("#iamportPayment2").click(function(){
//	if(checkAgree1.checked == false){
//	alert('개인정보 수집 및 이용 약관에 동의해주세요');
//	}else if(!form.orName.value || !form.orPhone.value || !form.orEmail.value || !form.sample6_postcode.value || !form.sample6_detailAddress.value){
//               alert('필수 입력사항을 입력해주세요 (이름, 이메일, 전화번호, 주소)');
//      	}else{
//	payment();
//	}
//
//    });
//})

//
//
//function payment(){
//const data = {
//number : $("input[name='orNum']"),
//name : $("input[name='orName']"),
//amount : Number($("#orPrice").val()),
//totalPrice : $("#orPrice").val()
//}
//payment(data);
//}
//
//function payment(data) {
//    IMP.init('imp36834793');
//    IMP.request_pay({// param
//        pg: "kakaopay.TC0ONETIME",
//        pay_method: "card",
//        merchant_uid: 'cart_' + new Date().getTime(),
//        name: "주문 상품",
//        amount: 100,
//        buyer_email : "testiamport@naver.com",
//        buyer_name : "주문자",
//
//    }, function (rsp) {
//        if (rsp.success) {
//            location.href="orderConfirm?orNum=${confirm.orNum}";
//
//            form2.submit()
//        } else {
//            alert("결제를 실패하였습니다. / 메세지(" + rsp.error_msg + ")");
//        }
//    });
//}


//$("#iamportPayment2").click(function(){
//
//
//    });
function payment2Click(){
        console.log("iamportPayment2 버튼 눌림");
    	if(document.getElementById("checkAgree2").checked == false){
    	    alert('개인정보 수집 및 이용 약관에 동의해주세요');
    	    return false;
    	}else if(!document.getElementById("orName").value || !document.getElementById("orPhone").value || !document.getElementById("orEmail").value || !document.getElementById("sample6_postcode1").value){
                   alert('필수 입력사항을 입력해주세요 (이름, 이메일, 전화번호, 주소)');
            return false;
        }else if(document.getElementById("checkAgree2").checked == true && pdQtyCntCheck== true){
//               console.log("실행!!!");
            return true;
    	}
}

//$(document).ready(function(){
//
//})

function payment3(){
    const data = {
        number : $("input[name='orNum']"),
        name : $("input[name='pdName']"),
        amount : Number($("#pdPrice").val()),
        totalPrice : $("#pdPrice").val()
    }

   payment4(data);
}
function payment4(data) {
    console.log(data.name[1].value+"외 "+data.name.length-1+"건");
    IMP.init('imp36834793');
    IMP.request_pay({// param

        pg: "kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid: 'cart_' + new Date().getTime(),
        name: data.name[1].value+" 외 "+(data.name.length-1)+"건",
        amount: 100,
        buyer_email : "testiamport@naver.com",
        buyer_name : "주문자",

    }, function (rsp) {
        if (rsp.success) {

            document.getElementById("form2").submit();
        } else {
            alert("결제를 실패하였습니다. / 메세지(" + rsp.error_msg + ")");
        }
    });
}