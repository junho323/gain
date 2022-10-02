

function reviewList(pdCode){
//    console.log("리뷰 불러오는 상품코드는? : "+pdCode);
    $.ajax({
        type : "get",
        url : "/reviews/"+pdCode,
        dataType : "json",
        contentType : 'application/json; charset-utf-8',
        success : function(data){
            console.log(data);
            if(window.location.pathname.split("/").length == 3){
                reviewListView(data);
            }


        },
        error : function(){

        }
    });
    if(reviewAvg <= 0){
        scoreAvgText.innerText = "0점";
    }


}
let reviewCnt = 0;
let reviewAvg = 0;

const scoreAvgText = document.getElementById("star-avg");
const scoreAvgstar = document.getElementById("draw-star-avg");


function reviewListView(list){
    reviewCnt = list.length;


    reviewAvg = 0;
    for(let i in list){
//        console.log("리스트 i 검사 : "+i)
        reviewAvg += list[i].starScore;
//        console.log("리스트 reviewAvg 검사 : "+reviewAvg)
    }
    console.log("평균별점 : "+(reviewAvg/list.length).toFixed(2));
        if(reviewAvg != 0){
            scoreAvgText.innerText = (reviewAvg/list.length).toFixed(2)+"점";
        }else{
            scoreAvgText.innerText = "0점";
        }



     scoreAvgstar.style.width= ((reviewAvg/list.length))*10*2+"%";




    let output = "";
      output += "<div class='blog__details__comment'>";
      output += "<h5 style='text-transform:capitalize; margin-bottom: 15px;'>Reviews("+list.length+")";
      output += "</h5>";
      output +=  (list.length != 0) ? "<div class='blog__comment__item__text'> <ul style='margin-bottom:30px'><h6 style='color:#111111'>평균별점 <li style='color:#111111'><i class='fa fa-star'></i>"
             + ((reviewAvg/list.length)).toFixed(2)+"</li></h6></ul></div>" : "등록된 리뷰가 없습니다.";
     for(let i in list){
        output += "<div class='blog__comment__item'>";
        output += "<div class='blog__comment__item__pic'>";
        output += "<img src='/profile/"+list[i].mprofileName+"'width='70px' alt=''>";
        output += "</div>";
        output += "<div class='blog__comment__item__text'>";
        output += "<h6>"+list[i].mname+"</h6>";
        output += "<p>"+list[i].review+"</p>";
        output += "<ul style='margin-bottom:30px'>";
        output += "<li><i class='fa fa-clock-o'></i>"+list[i].rvDate+"</li>";
        output += "<li><i class='fa fa-star'></i>"+(list[i].starScore)
                    +"<span class='star' style='font-size:15px'>" + "★★★★★"
                    + "<span style='width:" + (list[i].starScore)*10*2 + "%'>" + "★★★★★"+ "</span>"
          		  "</li>";
//        output += "<li class='fa fa-close'  th:if='[[${session.member?.mId eq "+list[i].mid+"}]]'><span th:rvNum='${list[i].rvNum}' th:onclick='reviewDelete(this.getAttribute('rvNum'))'>삭제</span></li>";
//        console.log("로그인아이디 : "+[[${session.member?.mId}]]+" , 리뷰 아이디 : "+[[${session.member?.mId}]]+" , 일치여부 "+[[${session.member?.mId == " +list[i].mid+ "}]]);
//    		  console.log("로그인아이디 : "+[[${session.member?.mId}]]+" , 리뷰 아이디 : "+[[${session.member?.mId}]]+" , 일치여부 "+[[${session.member?.mId == list[i].mid }]]);
        output += "<li class='fa fa-close' th:if='[[${session.member?.mId eq " +list[i].mid+ "}]]'><span onclick='reviewDelete(list[i].rvNum)'>삭제</span></li>";

        output += "</ul>";
        output += "</div>";
        output += "</div>";
        output += "</div>";

     }
     let reviewArea = document.getElementById('review-area');
     reviewArea.innerHTML = output;

}

function reviewDelete(rvNum){
//    console.log("삭제할 리뷰 번호"+rvNum);
    $.ajax({
        type : "DELETE",
        url : "/reviews/"+rvNum,
    success : function(){

    },
    error : function(){
            alert('통신실패');
        }
    });

}



