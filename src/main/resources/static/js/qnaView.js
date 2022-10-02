var form = document.getElementById("form");
$('#btn1').click(function(){
confirm('답변을 등록하시겠습니까?')
if(confirm = true){
alert('답변을 등록했습니다.');

//     location.href="/order/adminQnAList";
     form.submit()
}



});