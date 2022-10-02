
const loginId = document.getElementById("id-check").innerHTML;
console.log("로그인 아이디 : "+loginId);

$('#directBuy').click(function(){
if(!loginId){
    console.log("아이디 일치");
    alert('로그인 이후 진행해주세요!');
        location.href="../member/login-form";
}
});

$('#directBuy2').click(function(){
alert('품절된 상품 입니다!');
});


$('#insertCart').click(function(){
if(!loginId){
    console.log("아이디 일치");
    alert('로그인 이후 진행해주세요!');
        location.href="../member/login-form";
}
});

