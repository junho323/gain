let dlist = '';
  	$(document).ready(function(){
  		$.ajax({
  			type : "GET",
  			url : "/members/dlist",
  			dataType : "json",
  			success : function(list){
                dlist = list;
  				memberList(list);
  			},
  			error : function(){
  				alert('회원 목록 불러오기 실패!');
  			}
  		});
  	});

  	function memberList(list){
  	    console.log(list[0]);
  	    console.log(list[1]);
        var output = "";
          		output += "<table  class='table datatable' id='products'>";
          		output += "<thead>";
          		output += "<tr>";
          		output += "<th>아이디</th>";
          		output += "<th>이름</th>";
          		output += "<th>성별</th>";
          		output += "<th>이메일</th>";
          		output += "<th>계정복구</th>";
          		output += "</tr>";
          		output += "</thead>";

          		output += "<tbody>";

          		for(var i in list){
          		    console.log("ajax로 불러온 아이디 : "+list[i].mId);
          			output += "<tr>";
          			output += "<td id='deleted-id'>" + list[i].mid + "</td>";
          			output += "<td>" + list[i].mname + "</td>";
          			output += "<td>" + list[i].mgender + "</td>";
          			output += "<td>" + list[i].memail + "</td>";
          			output += "<td><button type='button' id='restore-btn' onclick='restore()'>복구</button></td>";
          		}

          		output += "</tbody>";
          		output += "</table>";


          		var listAreaArea = document.getElementById('listArea');
          		listAreaArea.innerHTML = output;


  	}

  	function restore(){
        const deletedId = $('#deleted-id').text();
        console.log(deletedId);
    //    console.log(deletedId.innerText);
        $.ajax({
            type : 'post',
            url : '/members/dlist',
            data : {'mId':deletedId},
            dataType : 'text',
    //        contentType : 'application/json; charset-utf-8',
            success : function(data){
                if(data > 1){
                    alert('복구완료');
                    if(dlist != ''){
                        memberList(dlist);
                    }

                }else{
                    alert('복구에러');
                }
            },
            error:function(){
                alert('통신에러');
            }

        });


    }

