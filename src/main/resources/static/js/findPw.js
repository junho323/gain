const emailText2 = document.querySelector("#user-email2");
const checkEmail2 = document.querySelector("#check-email2");

const findPwBtn = document.querySelector("#find-pw-btn");



let validCheckEmail3 = false;
let validCheckEmail4 = false;
const mEmail2 = document.querySelector("#mEmail2");

function emailCheck(){

//    const emailReg = /^[a-zA-Z0-9@_.]/;
    const emailReg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{1,}))$/;
    const email = $('#user-email2').val();
    console.log(email);
    if (!emailReg.test(emailText2.value)){
  		event.preventDefault();
//  		message.innerText = "올바른 이메일 양식이 아닙니다.";
  		document.querySelector('#user-email2').nextElementSibling.innerText = "올바른 이메일 양식이 아닙니다.";
//  		popUpMessage();
   		//alert("아이디는 영문자로 시작하는 4~12자리의 소문자 또는 숫자이어야 합니다."
    	}else{
             $.ajax({
                 url: '/member/email-check',
                 type:'post',
                 data: {email:email},
                 dataType : "json",
                 success:function(data){
                     if(data != 1){
                  		 console.log(data);
//                         message.innerText = "사용 가능 합니다.";
                         document.querySelector('#user-email2').nextElementSibling.innerText = "등록되지 않은 이메일 입니다.";
                         validCheckEmail3 = false;
             			 emailText2.addEventListener("keydown",() => {

                                        validCheckEmail3 = false;
                                    });
//                         popUpMessage();
                     }else{
                         event.preventDefault();
                         console.log(data);
//                         message.innerText = "이미 사용중 입니다.";
                         document.querySelector('#user-email2').nextElementSibling.innerText = "등록된 이메일 입니다.";
                         validCheckEmail3 = true;
//                         popUpMessage();
                     }
                 },
                 error:function(){
                     console.log("에러");
                 }
             })
     }
}

function emailCheck2(){
	if(validCheckEmail3 != true){
            event.preventDefault();
//            message.innerText = "이메일 중복확인을 해주세요.";
//            popUpMessage();
            alert("이메일 중복확인을 해주세요.");
       }else{


  			let mEmail2 = $('#user-email2').val();
  			let output2 = "";
  			let inputUUID2 = document.getElementById('inputUUID2');

  			console.log("이메일 주소 : "+mEmail2);
  			$.ajax({
				type : 'POST',
				url : "/member/email-auth",
				data : {"mEmail" : mEmail2},
				dataType : "text",
				success : function(uuid){
						$('#user-email2').attr('readonly',true);
//						document.querySelector("#user-email").nextElementSibling.style.visibility = "hidden";
						document.querySelector("#user-email2").nextElementSibling.style.display = "none";
//						message.innerText = "이메일로 인증번호를 발송했습니다.";
//  						popUpMessage();
                        alert("이메일로 인증번호를 발송했습니다.");
//                        document.querySelector('#user-email').nextElementSibling.innerText = "이메일로 인증번호를 발송했습니다.";

  						output2 += "<br/>";
//  						output2 += "<p style='color: #444444; margin-bottom: 10px'>인증번호 입력</p>";
  						output2 += "<label for='recipient-name' class='col-form-label'>인증번호 입력:</label>";
  						output2 += "<input type='text' class='form-control' id='iuuid2'/>";
  						output2 += "<input type='hidden' id='cuuid2' value='"+uuid+"'/>";
  						output2 += "<br/><input type='button' value='인증' onclick='checkUUID2()'/>";

  						inputUUID2.innerHTML = output2;
				},
				error : function(){
//						message.innerText = "인증번호 발송을 실패했습니다.";
//  						popUpMessage();
                        alert("인증번호 발송을 실패했습니다.");
				}
			});

		}


}

function checkUUID2(){
	let iuuid = document.getElementById('iuuid2').value;
	let cuuid = document.getElementById('cuuid2').value;

	if(iuuid == cuuid){
		   event.preventDefault();

//           message.innerText = "이메일 인증에 성공했습니다.";
           alert("이메일 인증에 성공했습니다.");
           validCheckEmail4 = true;
            $('#user-email2').attr('readonly',true);
            // check-email iuuid cuuid
//            document.querySelector("#inputUUID").style.visibility = "hidden";
//            document.querySelector("#iuuid").parentElement.style.visibility = "hidden";

           document.querySelector("#inputUUID2").style.display = "none";
           document.querySelector("#user-email2").nextElementSibling.style.display = "none";
//			document.querySelector("#user-email").nextElementSibling.style.visibility = "hidden";
//           popUpMessage();
	}else{
		  event.preventDefault();
		  alert("이메일 인증에 실패했습니다.");
//          message.innerText = "이메일 인증에 실패했습니다.";
//          popUpMessage();
	}

}

mEmail2.addEventListener("click", emailCheck2);

//checkEmail2.addEventListener("click",emailCheck);

emailText2.addEventListener("blur",emailCheck);

function lastvalid(event){
       if(validCheckEmail3 != true){
            event.preventDefault();
//            message.innerText = "이메일 중복확인을 해주세요.";
//            popUpMessage();
            alert("이메일 확인을 해주세요.")
       }else if(validCheckEmail4 != true){
			event.preventDefault();
//            message.innerText = "이메일 인증을 해주세요.";
//            popUpMessage();
            alert("이메일 인증을 해주세요.");
       }else{
            findPw();
	    }
}

findPwBtn.addEventListener("click", lastvalid);
function findPw(){

    let userId = $("#user-id2").val();
    let userEmail = $('#user-email2').val();
    let info = {"userId":userId, "userEmail" : userEmail};
    console.log(userEmail);

    $.ajax({
       type : 'POST',
       url : "/member/find-pw",
       data: JSON.stringify(info),
       dataType : "text",
       contentType : 'application/json; charset-utf-8',
       success : function(result){
            if(result == ""){
                alert('아이디와 이메일이 일치하지 않습니다.');
            }else{
                alert(userId+'님의 임시 비밀번호 : '+result);
//                $('#btn btn-secondary').trigger("click");
//                location.href='/member/login-form';s
            }



       },error : function(){
            alert('아이디 찾기 통신 에러');

       }

    });

}