
<script th:inline="javascript">

  /*<![CDATA[*/
        let pdName = /*[[ ${list?.pdName} ]]*/;
        /*]]*/



const checkAgree1 = document.querySelector("#checkAgree1");
const checkAgree2 = document.querySelector("#checkAgree2");

var form = document.getElementById("form");
var orName = document.getElementById("orName");
var orEmail = document.getElementById("orEmail");
var orPhone = document.getElementById("orPhone");
var sample6_postcode = document.getElementById("sample6_postcode");
var sample6_detailAddress = document.getElementById("sample6_detailAddress");
var orAddr = document.getElementById("orAddr");



$(document).ready(function(){
	$("#iamportPayment1").click(function(){
	if(checkAgree1.checked == false){
	alert('개인정보 수집 및 이용 약관에 동의해주세요');
	}else if(!form.orName.value || !form.orPhone.value || !form.orEmail.value || !form.sample6_postcode.value || !form.sample6_detailAddress.value){
               alert('필수 입력사항을 입력해주세요 (이름, 이메일, 전화번호, 주소)');
      	}else{
	payment();
	}

    });
})



function payment(){
const data = {
number : $("input[name='orNum']"),
name : $("input[name='orName']"),
amount : Number($("#orPrice").val()),
totalPrice : $("#orPrice").val()
}
payment(data);
}

function payment(data) {
    IMP.init('imp61297047');
    IMP.request_pay({// param
        pg: "kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid: 'cart_' + new Date().getTime(),
        name: pdName,
        amount: 100,
        buyer_email : "testiamport@naver.com",
        buyer_name : "주문자",

    }, function (rsp) {
        if (rsp.success) {
            location.href="orderConfirm?orNum=${confirm.orNum}";

            form.submit()
        } else {
            alert("결제를 실패하였습니다. / 메세지(" + rsp.error_msg + ")");
        }
    });
}




$(document).ready(function(){
	$("#iamportPayment2").click(function(){
	if(checkAgree2.checked == false){
	alert('개인정보 수집 및 이용 약관에 동의해주세요');
	}else if(!form2.orName.value || !form2.orPhone.value || !form2.orEmail.value || !form2.orAddr.value){
               alert('필수 입력사항을 입력해주세요 (이름, 이메일, 전화번호, 주소)');
      	}else{
	payment2();
	}

    });
})

function payment2(){
const data = {
number : $("input[name='orNum']"),
name : $("input[name='orName']"),
amount : Number($("#orPrice").val()),
totalPrice : $("#orPrice").val()
}
payment2(data);
}

function payment2(data) {
    IMP.init('imp61297047');
    IMP.request_pay({// param
        pg: "kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid: 'cart_' + new Date().getTime(),
        name: pdName,
        amount: 100,
        buyer_email : "testiamport@naver.com",
        buyer_name : "주문자",

    }, function (rsp) {
        if (rsp.success) {
//            location.href="OrderListForm";
            form2.submit()
        } else {
            alert("결제를 실패하였습니다. / 메세지(" + rsp.error_msg + ")");
        }
    });
}

</script>