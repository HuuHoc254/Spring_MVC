function clearForm(){
	if (document.getElementById('accountName')!=null){
		document.getElementById('accountName').value = '';
		document.getElementById('fullName').value = '';
	}
	document.getElementById('productCode').value = '';
	document.getElementById('productName').value = '';
	document.getElementById('customerName').value = '';
	document.getElementById('phoneNumberCustomer').value = '';
	document.getElementById('beginOrderDate').value = '';
	document.getElementById('endOrderDate').value = '';
	document.getElementById('inlineCheckbox1').checked = false;
	document.getElementById('inlineCheckbox2').checked = false;
}