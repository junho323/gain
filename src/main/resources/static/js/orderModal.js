const btnClose = document.querySelector("#btnClose");

//const btnConfirm = document.querySelector("#btnConfirm");
const modalForm = document.getElementById('modalForm');


$(document).ready(function(){
$('#btnConfirm').click(function(){
if (confirm('주문을 취소하시겠습니까?')==true){
alert('주문취소 요청이 완료되었습니다.');
location.reload();
modalForm.submit()


} else{
alert('취소하였습니다.')
}


});
})

