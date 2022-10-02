const loginBtn = document.querySelector(".navbar-login");
const content = document.querySelector(".regist-frame");
const idText = document.querySelector("#user-id");
const checkId = document.querySelector("#check-id");
const checkPassword = document.querySelector("#password1");
const checkPassword2 = document.querySelector("#password2");
const emailText = document.querySelector("#user-email");
const checkEmail = document.querySelector("#check-email");
const registButton = document.querySelector("#regist-button");
const controllerValue = document.querySelector(".controller-value");
const phoneText = document.querySelector("#user-phone");

const modal = document.createElement("div");
const messageBox = document.createElement("div");
const messageBtn = document.createElement("button");
const message = document.createElement("span");
const mEmail = document.querySelector("#mEmail");

const userId = "${id}";
let validCheckId = false;
let validCheckEmail = false;
let validCheckEmail2 = false;


function popUpMessage(){

	
	modal.className = "modal2";
	messageBox.id = "message-box";
	messageBtn.id = "popup-button";
//	window.scrollTo(0,0);
	
	//messageBtn.setAttribute("value","확인");
	messageBtn.innerText = "확인";

	
	content.prepend(modal);
	modal.appendChild(messageBox);
	messageBox.appendChild(message);
	messageBox.appendChild(messageBtn);
	messageBtn.addEventListener("click",()=>modal.remove());
}



function idCheck(event){
        document.querySelector('#user-id').nextElementSibling.style.color = "red";
		const idReg = /^[a-z0-9-_]{5,11}$/;

	 	if (!idReg.test(idText.value)){

			event.preventDefault();
			message.innerText = "5~20자의 영문 소문자, 숫자와 특수기호 _  - 만 사용 가능합니다.";
			document.querySelector('#user-id').nextElementSibling.innerText =  "5~10자의 영문 소문자, 숫자와 특수기호 _  - 만 사용 가능합니다.";

 	 	}else{
 	 	    const id = $('#user-id').val();
 	 	    console.log(id);
                    	$.ajax({
                    		url: '../member/id-check',
                    		type:'POST',
                    		data: {id:id},
                    		dataType : "json",
                    		success:function(data){
                    		    console.log(data);
                    		    console.log(typeof(data));
                    			if(data != 1){
                    		   		console.log(data.cnt);
                    				message.innerText = "사용 가능 합니다.";
                                    document.querySelector('#user-id').nextElementSibling.style.color = "blue";
                    				document.querySelector('#user-id').nextElementSibling.innerText = "사용 가능합니다.";
                    				validCheckId = true;
                                    idText.addEventListener("keydown",() => {
                                        validCheckId = false;
                                        
                                    });
                    			}else{
                    			 	event.preventDefault();
                    			    console.log(data.cnt);
                    				message.innerText = "이미 사용중 입니다.";
									document.querySelector('#user-id').nextElementSibling.innerText = "이미 사용중입니다.";
                                    validCheckId = false;
                                 
                    			}

                    		},
                    		error:function(){
                    			console.log("에러");
                    		}

                    	})

 	 	}





}

function passwordCheck(){
	const passwordReg = /^[a-zA-Z0-9!@#$%^&*-_]{5,26}$/;
	const check = true;
	document.querySelector('#password2').nextElementSibling.style.color = "red";
	if(checkPassword.value != checkPassword2.value){
		event.preventDefault();
		document.querySelector("#password2").nextElementSibling.innerText = "비밀번호가 서로 틀려요!";
		// checkPassword2.nextElementSibling.innerText = "비밀번호가 서로 틀려요!";
		return false;

	}else if(checkPassword.value == checkPassword2.value && !passwordReg.test(checkPassword2.value)){
		event.preventDefault();
		document.querySelector("#password2").nextElementSibling.innerText = "5~25자리 대소문자,숫자,!@#$%^&*-_ 사용가능";
		// checkPassword2.nextElementSibling.innerText = "5~22자리 대소문자,숫자,특수문자만 사용가능";

		return false;
	}else{
	    document.querySelector('#password2').nextElementSibling.style.color = "blue";
		document.querySelector("#password2").nextElementSibling.innerText = "사용 가능한 비밀번호 입니다.";
		// checkPassword2.nextElementSibling.innerText = "사용 가능한 비밀번호 입니다.";
		return true;
	}

}
//checkPassword.addEventListener("blur",passwordCheck);
checkPassword2.addEventListener("blur",passwordCheck);
registButton.addEventListener("click",passwordCheck);
registButton.addEventListener("click",phoneCheck);


function phoneCheck(){
        document.querySelector('#user-phone').nextElementSibling.style.color = "red";
        const phoneReg = /^010(-[0-9]{4}){2}$/;
        if(!phoneReg.test(phoneText.value)){
            event.preventDefault();
            document.querySelector('#user-phone').nextElementSibling.innerText = "올바른 연락처 양식이 아닙니다. ex(010-xxxx-xxxx)";
//            document.querySelector('#user-phone').focus();
            return false;

        }else{
            document.querySelector('#user-phone').nextElementSibling.style.color = "blue";
            document.querySelector('#user-phone').nextElementSibling.innerText = "사용 가능합니다.";
            return true;
        }
}
phoneText.addEventListener("blur",phoneCheck);

function emailCheck(){

//    const emailReg = /^[a-zA-Z0-9@_.]/;
    const emailReg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{1,}))$/;

    const email = $('#user-email').val();
    console.log(email);


    if (!emailReg.test(emailText.value)){
  		document.querySelector('#user-email').nextElementSibling.style.color = "red";
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
                         document.querySelector('#user-email').nextElementSibling.style.color = "blue";
                         document.querySelector('#user-email').nextElementSibling.innerText = "사용 가능합니다.";
                         validCheckEmail = true;
             			 emailText.addEventListener("keydown",() => {
                       
                                        validCheckEmail = false;
                                    });
//                         popUpMessage();
                     }else{
                         event.preventDefault();
                         console.log(data);
//                         message.innerText = "이미 사용중 입니다.";
                         document.querySelector('#user-email').nextElementSibling.innerText = "이미 사용중 입니다.";
                         validCheckEmail = false;
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
//            emailText.focus();
//            popUpMessage();
       }else{
		
  			
  			let mEmail = $('#user-email').val();
  			let output = "";
  			let inputUUID = document.getElementById('inputUUID');
  			
  			console.log("이메일 주소 : "+mEmail);
  			$.ajax({
				type : 'POST',
				url : "../member/email-auth",
				data : {mEmail : mEmail},
				dataType : "text",
				success : function(uuid){
						$('#user-email').attr('readonly',true);
//						document.querySelector("#user-email").nextElementSibling.style.visibility = "hidden";
						document.querySelector("#user-email").nextElementSibling.style.display = "none";
//						message.innerText = "이메일로 인증번호를 발송했습니다.";
//  						popUpMessage();
                        alert("이메일로 인증번호를 발송했습니다.");

  						
  						output += "<br/>";
  						output += "<p style='color: #444444; margin-bottom: 10px'>인증번호 입력</p>";
  						output += "<input type='text' id='iuuid'/>";
  						output += "<input type='hidden' id='cuuid' value='"+uuid+"'/>";
  						output += "<input type='button' value='인증' onclick='checkUUID()'/>";
  						
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
           validCheckEmail2 = true;
            $('#user-email').attr('readonly',true);
            // check-email iuuid cuuid
//            document.querySelector("#inputUUID").style.visibility = "hidden";
//            document.querySelector("#iuuid").parentElement.style.visibility = "hidden";

           document.querySelector("#inputUUID").style.display = "none";
           document.querySelector("#user-email").nextElementSibling.style.display = "none";
//			document.querySelector("#user-email").nextElementSibling.style.visibility = "hidden";
            alert("이메일 인증에 성공했습니다.");
//           popUpMessage();
	}else{
		  event.preventDefault();
//          message.innerText = "이메일 인증에 실패했습니다.";
//          popUpMessage();
            alert("이메일 인증에 실패했습니다.");
	}
	
}

idText.addEventListener("blur",idCheck);

mEmail.addEventListener("click", emailCheck2);

//checkEmail.addEventListener("click",emailCheck);

emailText.addEventListener("blur",emailCheck);


function lastvalid(event){
     if(validCheckId != true){
            event.preventDefault();
//            message.innerText = "아이디 중복확인을 해주세요.";
            alert("아이디 중복확인을 해주세요.");
            idText.focus();
//            popUpMessage();
       }else if(passwordCheck() != true){
            event.preventDefault();
            alert("비밀번호를 확인 해주세요.");
            checkPassword.focus();
       }else if(phoneCheck() != true){
            event.preventDefault();
            alert("연락처를 확인 해주세요.");
            phoneText.focus();
       }else if(validCheckEmail != true){
            event.preventDefault();
//            message.innerText = "이메일 중복확인을 해주세요.";
            alert("이메일 중복확인을 해주세요.");
            emailText.focus();
//            popUpMessage();
       }else if(validCheckEmail2 != true){
			event.preventDefault();
			alert("이메일 인증을 해주세요.");
//            message.innerText = "이메일 인증을 해주세요.";
            emailText.focus();
//            popUpMessage();
       }else{


        alert('회원가입이 완료되었습니다.');
       }

}

registButton.addEventListener("click", lastvalid);

let fileImgCheck = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
document.getElementById("profile-img").onchange = ()=> {
			if(!document.getElementById("profile-img").value.match(fileImgCheck)){
				alert('이미지 파일이 아닙니다.\n jpg,jpeg,png,gif,bmp 확장자만 가능합니다.');
				document.getElementById("profile-img").value = '';
			}
		}