function reviewDelete(rvNum){
    console.log("삭제할 리뷰 번호"+rvNum);
    $.ajax({
        type : "DELETE",
        url : "/reviews/"+rvNum,
    success : function(){
        alert('리뷰가 삭제되었습니다.');
        location.href= document.location.href.slice(21);
    },
    error : function(){
            alert('통신실패');
        }
    });

}



