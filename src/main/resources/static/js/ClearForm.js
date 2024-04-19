function clearForm(){
	var inputs = document.querySelector('form').getElementsByTagName('input');
	for (var i = 0; i < inputs.length; i++) {
    inputs[i].value = '';
}
	if(document.getElementById('inlineCheckbox1')!=null){
	document.getElementById('inlineCheckbox1').checked = false;
	document.getElementById('inlineCheckbox2').checked = false;
	}
}