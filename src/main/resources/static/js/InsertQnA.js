
var qaName = document.getElementById('qaName');
var qaContent = document.getElementById('qaContent');

var qaType = document.getElementById('qaType');
var form = document.getElementById('form');
$(document).ready(function(){

$('#btnSubmit').click(function(){
if(form.qaType.value == 1){
alert('문의 유형을 선택해주세요');
}else if(!form.qaName.value){
alert('제목을 입력해주세요')

} else if(!form.qaContent.value){
alert('문의 내용을 입력해주세요')
}else{
confirm('등록 후에는 수정이 불가하며, 삭제 후 다시 등록해야 합니다.\n\n등록하시겠습니까?')
if(confirm = true){
alert('등록이 완료 되었습니다.');
form.submit()
}else{
alert('취소하였습니다.')
}


};

})

});
