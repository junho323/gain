

function wishDelete(mId, pdCode){
     const info = {mId:mId, pdCode:pdCode};
     $.ajax({
        type : "DELETE",
        url : "/member/wish",
        data : JSON.stringify(info),
        contentType : 'application/json; charset-utf-8',
        dataType : 'json',
        success : function(data){
            if(data > 0){
                location.href = '/member/wish/'+mId;
            }

        },
        error : function(){
//            alert('통신 실패');
        }


    });

}