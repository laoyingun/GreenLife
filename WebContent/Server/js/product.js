function showInfo(value) {
	window.location.href="showAndChange.jsp?id="+value;
}

function getXMLHttpRequest() {

	var xhr;
	if (window.ActiveXObject) {
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else {
		xhr = null;
	}
	return xhr;
}
