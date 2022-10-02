const id = document.querySelector("#user-id");
const pw = document.querySelector("#password1");

let loginButton = document.querySelector("#login-button");

loginButton.addEventListener("click",login);

function enterLogin(e){
    if(e.keyCode == 13){
        login();
    }

}

function login(){
    const info = {"userId":id.value, "password":pw.value};
    console.log("데이터"+id);
    console.log("아이디"+info.userId);
    console.log("비밀번호"+info.password);
    if(id.value.length >= 1 && pw.value.length  >= 1){
        $.ajax({
                type : 'POST',
                url : '../member/login',
                data: JSON.stringify(info),
                dataType : 'json',
                contentType : 'application/json; charset-utf-8',
                success : function(result){
                    if(result == 2){
                        alert('로그인 성공');
                        location.href = '/';
//                        history.back();
                    }else if(result == 1){
                        alert('비밀번호가 다릅니다.');
                    }else{
                        alert('존재하지 않는 아이디 입니다.');
                    }
                },
                error:function(){
                    alert('에러');
                }

            });
    }else{
        alert('아이디나 비밀번호를 입력해주세요.');
    }


}
