

function reviewWrite(pdCode, mId, review){
    let reviewInfo = {pdCode:pdCode, mId:mId, review:review.value, starScore:starScore};
    console.log(pdCode+", "+mId+", "+review.value+", "+starScore);
    if(mId != "" && mId != null){
        if(review.value != ""){
            $.ajax({
                type : "post",
                url : "/reviews",
                data : JSON.stringify(reviewInfo),
                dataType : "json",
                contentType : 'application/json; charset-utf-8',
                success : function(data){
                    reviewList(pdCode);
                },
                error : function(){

                }
            });
        }else{
            alert("리뷰 내용을 작성해주세요!");
        }


    }else{
        alert("로그인 후 이용가능합니다.");
    }



}



  let starScore = 0;
  const drawStar = (target) => {
	  	document.querySelector(`#std span`).style.width = `${target.value * 20}%`;

	    starScore = target.value;
	 	 console.log("star"+starScore);

	  }




