// Mảng lưu trữ các đơn hàng đã sửa đổi
let editedOrders = [];
if(localStorage.getItem("editedOrders")){
	editedOrders = JSON.parse(localStorage.getItem("editedOrders"));
}

// Gán sự kiện "blur" cho các ô có thuộc tính contenteditable="true"
$("tbody").on("blur", "[contenteditable=true]", function() {
    // Lấy ra các ô dữ liệu trong hàng của đơn hàng hiện tại
    let $currentRow = $(this).closest("tr");
    let index = $currentRow.find(".index").text();
    let orderId = $currentRow.find(".orderId").text();
    let productCode = $currentRow.find(".productCode").text();
    let quantity = $currentRow.find(".quantity").text();
    let phoneNumber = $currentRow.find(".phoneNumber").text();

    // Lấy ra các giá trị cũ (ẩn) tương ứng
    let oldProductCode = $currentRow.find(".oldProductCode").text();
    let oldQuantity = $currentRow.find(".oldQuantity").text();
    let oldPhoneNumber = $currentRow.find(".oldPhoneNumber").text();
	let editedOrder = {
			index: index,
            orderId: orderId,
            productCode: productCode,
            quantity: quantity,
            phoneNumber: phoneNumber
        };
    let oldOrder = {
			index: index,
            orderId: orderId,
            productCode: oldProductCode,	
            quantity: oldQuantity,
            phoneNumber: oldPhoneNumber
        };

    // Kiểm tra xem các giá trị mới có khác với các giá trị cũ hay không
     if (equals(editedOrder,oldOrder)) {
        // Kiểm tra xem đơn hàng đã tồn tại trong mảng editedOrders chưa
        let existingIndex = editedOrders.findIndex(order => order.index === index);
        if (existingIndex === -1) {
            // Nếu không tồn tại, thêm vào mảng
            editedOrders.push(editedOrder);
        } else {
            // Nếu tồn tại, cập nhật lại đơn hàng
            editedOrders[existingIndex] = editedOrder;
        }
    } else {
        // Nếu giống nhau, kiểm tra xem đơn hàng đã tồn tại trong mảng editedOrders chưa
        let existingIndex = editedOrders.findIndex(order => order.index === index);
        if (existingIndex !== -1) {
            // Nếu tồn tại, xóa khỏi mảng
            editedOrders.splice(existingIndex, 1);
        }
    }
    localStorage.setItem("editedOrders", JSON.stringify(editedOrders));
});
function equals(editedOrder, oldOrder){
	if ( 
			(editedOrder.productCode 	!== oldOrder.productCode)
         || (editedOrder.quantity 		!== oldOrder.quantity)
         || (editedOrder.phoneNumber 	!== oldOrder.phoneNumber)
    ){
		return true;
	}
	return false;
}
function resert(){
	localStorage.removeItem("editedOrders");
	window.location.href = "http://localhost:8080/order"; 
}
