//let name = window.location.pathname.substr(9);

//document.getElementById("id-check").innerHTML = item;


let urlId = window.location.pathname.split("/")[3];
const loginId = document.getElementById("id-check").innerHTML;

//console.log("로그인 아이디 : "+loginId);
//console.log("URL 아이디 : "+urlId);

if(urlId == loginId){
    console.log("아이디 일치");
}else{
    console.log("아이디 불일치");
    alert('로그인 후 이용 가능합니다!');
    location.href="/member/login-form";
}
