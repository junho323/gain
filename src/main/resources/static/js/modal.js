const modal = document.createElement("div");
const messageBox = document.createElement("div");
const messageBtn = document.createElement("button");
const message = document.createElement("span");
const mEmail = document.querySelector("#mEmail");

function popUpMessage(){


	modal.className = "modal2";
	messageBox.id = "message-box";
	messageBtn.id = "popup-button";
	window.scrollTo(0,0);

	//messageBtn.setAttribute("value","확인");
	messageBtn.innerText = "확인";


	content.prepend(modal);
	modal.appendChild(messageBox);
	messageBox.appendChild(message);
	messageBox.appendChild(messageBtn);
	messageBtn.addEventListener("click",()=>modal.remove());
}