

function showAdress(className){
	/* 컨텐츠 동작 */
	/* 모두 숨기기 */
		$(".addressInfo_input_div").css('display', 'none');
	/* 컨텐츠 보이기 */
		$(".addressInfo_input_div_" + className).css('display', 'block');
}


/* 주소입력란 버튼 동작(숨김, 등장) */
function showAdress(className){
	/* 컨텐츠 동작 */
		/* 모두 숨기기 */
		$(".addressInfo_input_div").css('display', 'none');
		/* 컨텐츠 보이기 */
		$(".addressInfo_input_div_" + className).css('display', 'block');

	/* 버튼 색상 변경 */
		/* 모든 색상 동일 */
			$(".address_btn").css('backgroundColor','#CA1515' );
		/* 지정 색상 변경 */
			$(".address_btn_"+className).css('backgroundColor', '#ED0000');
}