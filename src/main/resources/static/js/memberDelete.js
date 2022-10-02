const id = document.querySelector("#user-id");
const checkBox = document.getElementById("acc");
const deleteBtn = document.getElementById("delete-button");


deleteBtn.addEventListener("click",(()=>{


    if(checkBox.checked){
        console.log(id.value);
        $.ajax({
            url: '/member/delete/',
            type:'delete',
            data: {id:id.value},
            dataType : "text",
            success : function(result){
                if(result > 0){
                    alert(id.value+'님의 회원탈퇴 성공');
                    location.href = '/member/login-form';
                }else{
                    alert('탈퇴 ㄱㄷㄱㄷ');

                }
            },
            error:function(){
                alert('에러');
            }
        });


    }else{
        alert("약관동의 후 탈퇴 가능합니다.");
    }

}));


