const deletedId = document.querySelector("#deleted-id");
//const restoreBtn = document.querySelector("#restore-btn");

//    restoreBtn.addEventListener("click", restore);


function restore(id){
//    const deletedId = $('#deleted-id').text();
//    console.log(deletedId);
    console.log(id);
    $.ajax({
        type : 'post',
        url : '/members/dlist',
        data : {'mId':id},
        dataType : 'text',
//        contentType : 'application/json; charset-utf-8',
        success : function(data){
            if(data > 1){
                alert('복구완료');
                location.href='/members/dlist';
            }else{
                alert('복구에러');
            }
        },
        error:function(){
            alert('통신에러');
        }

    });


}

