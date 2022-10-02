const emailText = document.querySelector("#user-email");
const checkEmail = document.querySelector("#check-email");

const findIdBtn = document.querySelector("#find-id-btn");



let validCheckEmail = false;
let validCheckEmail2 = false;
const mEmail = document.querySelector("#mEmail");

function emailCheck(){

//    const emailReg = /^[a-zA-Z0-9@_.]/;
    const emailReg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{1,}))$/;
    const email = $('#user-email').val();
    console.log(email);
    if (!emailReg.test(emailText.value)){
  		event.preventDefault();
//  		message.innerText = "올바른 이메일 양식이 아닙니다.";
  		document.querySelector('#user-email').nextElementSibling.innerText = "올바른 이메일 양식이 아닙니다.";
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
                         document.querySelector('#user-email').nextElementSibling.innerText = "등록되지 않은 이메일 입니다.";
                         validCheckEmail = false;
             			 emailText.addEventListener("keydown",() => {

                                        validCheckEmail = false;
                                    });
//                         popUpMessage();
                     }else{
                         event.preventDefault();
                         console.log(data);
//                         message.innerText = "이미 사용중 입니다.";
                         document.querySelector('#user-email').nextElementSibling.innerText = "등록된 이메일 입니다.";
                         validCheckEmail = true;
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
	if(validCheckEmail != true){
            event.preventDefault();
//            message.innerText = "이메일 중복확인을 해주세요.";
//            popUpMessage();
            alert("이메일 중복확인을 해주세요.");
       }else{


  			let mEmail = $('#user-email').val();
  			let output = "";
  			let inputUUID = document.getElementById('inputUUID');

  			console.log("이메일 주소 : "+mEmail);
  			$.ajax({
				type : 'POST',
				url : "/member/email-auth",
				data : {mEmail : mEmail},
				dataType : "text",
				success : function(uuid){
						$('#user-email').attr('readonly',true);
//						document.querySelector("#user-email").nextElementSibling.style.visibility = "hidden";
						document.querySelector("#user-email").nextElementSibling.style.display = "none";
//						message.innerText = "이메일로 인증번호를 발송했습니다.";
//  						popUpMessage();
                        alert("이메일로 인증번호를 발송했습니다.");
//                        document.querySelector('#user-email').nextElementSibling.innerText = "이메일로 인증번호를 발송했습니다.";

  						output += "<br/>";
//  						output += "<p style='color: #444444; margin-bottom: 10px'>인증번호 입력</p>";
  						output += "<label for='recipient-name' class='col-form-label'>인증번호 입력:</label>";
  						output += "<input type='text' class='form-control' id='iuuid'/>";
  						output += "<input type='hidden' id='cuuid' value='"+uuid+"'/>";
  						output += "<br/><input type='button' value='인증' onclick='checkUUID()'/>";

  						inputUUID.innerHTML = output;
				},
				error : function(){
//						message.innerText = "인증번호 발송을 실패했습니다.";
//  						popUpMessage();
                        alert("인증번호 발송을 실패했습니다.");
				}
			});

		}


}

function checkUUID(){
	let iuuid = document.getElementById('iuuid').value;
	let cuuid = document.getElementById('cuuid').value;

	if(iuuid == cuuid){
		   event.preventDefault();

//           message.innerText = "이메일 인증에 성공했습니다.";
           alert("이메일 인증에 성공했습니다.");
           validCheckEmail2 = true;
            $('#user-email').attr('readonly',true);
            // check-email iuuid cuuid
//            document.querySelector("#inputUUID").style.visibility = "hidden";
//            document.querySelector("#iuuid").parentElement.style.visibility = "hidden";

           document.querySelector("#inputUUID").style.display = "none";
           document.querySelector("#user-email").nextElementSibling.style.display = "none";
//			document.querySelector("#user-email").nextElementSibling.style.visibility = "hidden";
//           popUpMessage();
	}else{
		  event.preventDefault();
		  alert("이메일 인증에 실패했습니다.");
//          message.innerText = "이메일 인증에 실패했습니다.";
//          popUpMessage();
	}

}

mEmail.addEventListener("click", emailCheck2);

//checkEmail.addEventListener("click",emailCheck);

emailText.addEventListener("blur",emailCheck);

function lastvalid(event){
       if(validCheckEmail != true){
            event.preventDefault();
//            message.innerText = "이메일 중복확인을 해주세요.";
//            popUpMessage();
            alert("이메일 확인을 해주세요.")
       }else if(validCheckEmail2 != true){
			event.preventDefault();
//            message.innerText = "이메일 인증을 해주세요.";
//            popUpMessage();
            alert("이메일 인증을 해주세요.");
       }else{
            findId();
	    }
}

findIdBtn.addEventListener("click", lastvalid);
function findId(){

    let userName = $("#user-name").val();
    let userEmail = $('#user-email').val();
    let info = {"userName":userName, "userEmail" : userEmail};
    console.log(userEmail);

    $.ajax({
       type : 'POST',
       url : "/member/find-id",
       data: JSON.stringify(info),
       dataType : "text",
       contentType : 'application/json; charset-utf-8',
       success : function(result){
            if(result == ""){
                alert('이름과 이메일이 일치하지 않습니다.');
            }else{
                alert(userName+'님의 아이디 : '+result);
//                $('#btn btn-secondary').trigger("click");
//                location.href='/member/login-form';s
            }



       },error : function(){
            alert('아이디 찾기 통신 에러');

       }

    });

}