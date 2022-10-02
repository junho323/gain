var pdPrice;

var orQty;
//
const pdPrice1 = document.querySelector("#pdPrice1");
const orQty1 = document.querySelector("#orQty1");

const pdPrice2 = document.querySelector("#pdPrice2");
const orQty2 = document.querySelector("#orQty2");
const pdQty = document.querySelector("#pdQty1");

function init () {
	pdPrice = document.form.pdPrice1.value;
	orQty = document.form.orQty1.value;
	document.form.orPrice.value = pdPrice;

	change();


}


function add () {
	hm = document.form.orQty;
	orPrice = document.form.orPrice;

     if(hm.value == document.form.pdQty.value){
         alert('상품의 재고가 부족합니다.');
         }else{
          hm.value ++ ;
             orPrice.value = parseInt(hm.value) * pdPrice;
         }
}

function del () {
	hm = document.form.orQty;
	orPrice = document.form.orPrice;
		if (hm.value > 1) {
			hm.value -- ;
			orPrice.value = parseInt(hm.value) * pdPrice;
		}
}

function change () {
	hm = document.form.orQty;
	orPrice = document.form.orPrice;

		if (hm.value < 0) {
			hm.value = 0;
		}
	orPrice.value = parseInt(hm.value) * pdPrice;

}

function init2 () {


	    pdPrice = document.form2.pdPrice2.value;
    	orQty = document.form2.orQty2.value;
    	document.form2.orPrice.value = pdPrice;
    	change2();
}


function add2 () {
	hm = document.form2.orQty;
	orPrice = document.form2.orPrice;
	if(hm.value == document.form.pdQty.value){
             alert('상품의 재고가 부족합니다.');
             }else{
              hm.value ++ ;
                 orPrice.value = parseInt(hm.value) * pdPrice;
             }
    }

function del2 () {
	hm = document.form2.orQty;
	orPrice = document.form2.orPrice;
		if (hm.value > 1) {
			hm.value -- ;
			orPrice.value = parseInt(hm.value) * pdPrice;
		}
}

function change2 () {
	hm = document.form2.orQty;
	orPrice = document.form2.orPrice;

		if (hm.value < 0) {
			hm.value = 0;
		}
	orPrice.value = parseInt(hm.value) * pdPrice;
}

