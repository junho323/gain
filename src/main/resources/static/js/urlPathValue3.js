//let name = window.location.pathname.substr(9);

//document.getElementById("id-check").innerHTML = item;


let urlId = window.location.pathname.substr(16);
const loginId = document.getElementById("id-check").innerHTML;

console.log("로그인 아이디 : "+loginId);
console.log("URL 아이디 : "+urlId);

if(urlId == loginId){
    console.log("아이디 일치");
}else{
    console.log("아이디 불일치");
    alert('해당 아이디로 로그인 해주세요!');
    location.href="../member/login-form";
}
