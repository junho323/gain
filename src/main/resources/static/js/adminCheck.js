const loginId = document.getElementById("id-check").innerHTML;

console.log("로그인 아이디 : "+loginId);

if(loginId == 'admin'){
    console.log("아이디 일치");
}else{
    console.log("아이디 불일치");
    alert('관리자로 로그인 해주세요!');
    location.href="../member/login-form";
}
