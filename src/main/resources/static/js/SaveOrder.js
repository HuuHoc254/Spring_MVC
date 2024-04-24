
// Mảng lưu trữ số lượng đơn hàng được thêm mới theo từng page
let arrayPage = JSON.parse(localStorage.getItem("arrayPage")) || [];
let currentPage = parseInt(new URLSearchParams(window.location.search).get('page')) || 1;
let orderArr = [];
let orderArrIndex = arrayPage.findIndex(order => order.page === currentPage);
if(orderArrIndex!== -1){
	orderArr = arrayPage[orderArrIndex].orderArr;
}
// Mảng lưu trữ các đơn hàng đã sửa đổi
let editedOrders = JSON.parse(localStorage.getItem("editedOrders")) || [];
// Gán sự kiện "blur" cho các ô có thuộc tính contenteditable="true"
$("tbody").on("blur", "[contenteditable=true]", function() {
    // Lấy ra các ô dữ liệu trong hàng của đơn hàng hiện tại
    let $currentRow = $(this).closest("tr");
    let index = $currentRow.find(".index").text();
    let orderId = $currentRow.find(".orderId").text();
    let productCode = $currentRow.find(".productCode").text();
    let productName = $currentRow.find(".productName").text();
    let quantity = $currentRow.find(".quantity").text();
    let phoneNumber = $currentRow.find(".phoneNumber").text();
    let customerName = $currentRow.find(".customerName").text();
    let version = $currentRow.find(".version").text();

    // Lấy ra các giá trị cũ (ẩn) tương ứng
    let oldProductCode = $currentRow.find(".oldProductCode").text();
    let oldQuantity = $currentRow.find(".oldQuantity").text();
    let oldProductName = $currentRow.find(".oldProductName").text();
    let oldPhoneNumber = $currentRow.find(".oldPhoneNumber").text();
    let oldCustomerName = $currentRow.find(".oldCustomerName").text();
	let editedOrder = {
			index: index,
            orderId: orderId,
            productCode: productCode,
            productName: productName,
            quantity: quantity,
            phoneNumber: phoneNumber,
            customerName: customerName,
            version: version
        };
    let oldOrder = {
			index: index,
            orderId: orderId,
            productCode: oldProductCode,	
            productName: oldProductName,
            quantity: oldQuantity,
            phoneNumber: oldPhoneNumber,
            customerName: oldCustomerName,
        };
	let existingPage = arrayPage.findIndex(order => order.page === currentPage);
    // Kiểm tra xem các giá trị mới có khác với các giá trị cũ hay không
     if (notEquals(editedOrder,oldOrder)) {

        // Kiểm tra xem đơn hàng đã tồn tại trong mảng editedOrders chưa
        let existingIndex = editedOrders.findIndex(order => order.index === index);
        if (existingIndex === -1) {
            // Nếu không tồn tại, thêm vào mảng
            editedOrders.push(editedOrder);
            // Nếu là đơn hàng thêm mới thì lưu vào mảng các đơn hàng được thêm mới theo từng page
            if (orderId==''){
				orderArr.push(index);
			let orderInPage = {
					page: currentPage,
					orderArr: orderArr
					}
			if (existingPage === -1) {
				arrayPage.push(orderInPage);
			} else {
				arrayPage[existingPage] = orderInPage;
			}
		    
			}
        } else {
            // Nếu tồn tại, cập nhật lại đơn hàng
            editedOrders[existingIndex] = editedOrder;
        }
    } else {
        // Nếu giống nhau, kiểm tra xem đơn hàng đã tồn tại trong mảng editedOrders chưa
        let existingIndex = editedOrders.findIndex(order => order.index === index);
        if (existingPage !== -1) {
			let arrIndex = arrayPage[existingPage].orderArr;
			let existingOrderInPage = arrIndex.findIndex(orderInPage => orderInPage === index);
			if (existingOrderInPage !== -1) {
				// Nếu tồn tại, xóa khỏi mảng
            	arrIndex.splice(existingOrderInPage, 1);
            	arrayPage[existingPage]= {
					page: currentPage,
					orderArr: arrIndex
				}
				//nếu tồn tại thì giảm index của các index phía sau đi 1
				arrayPage = arrayPage.map(pageIndex => {
				    if (pageIndex.page >= currentPage) {
				        pageIndex.orderArr = pageIndex.orderArr.map(orderIndex => {
				            if (parseInt(orderIndex) > parseInt(index)) {
				                orderIndex = (parseInt(orderIndex) - 1).toString();
				            }
				            return orderIndex;
				        });
			    	}
				    return pageIndex;
				});

			}
        }
        
        if (existingIndex !== -1) {
            // Nếu tồn tại, xóa khỏi mảng
            editedOrders.splice(existingIndex, 1);
             // Giảm index của các index sau đi 1
		    editedOrders.forEach(order => {
		        if (parseInt(order.index) > parseInt(index)) {
		            order.index--;
		            order.index = order.index.toString();
		        }
		    });
        }
    }
	localStorage.setItem("arrayPage", JSON.stringify(arrayPage));
    localStorage.setItem("editedOrders", JSON.stringify(editedOrders));
});
function notEquals(editedOrder, oldOrder){
	if ( 
			(editedOrder.productCode 	!== oldOrder.productCode)
 		 || (editedOrder.productName 	!== oldOrder.productName)
         || (editedOrder.quantity 		!== oldOrder.quantity)
         || (editedOrder.customerName 	!== oldOrder.customerName)
         || (editedOrder.phoneNumber 	!== oldOrder.phoneNumber)
    ){
		return true;
	}
	return false;
}
function resert(){
	localStorage.removeItem("editedOrders");
	localStorage.removeItem("arrayPage");
	window.location.href = "http://localhost:8080/order"; 
}

function loadEditedOrders() {
    let editedOrders = JSON.parse(localStorage.getItem("editedOrders")) || [];
    let $table = $("tbody").closest("table"); // Lấy thẻ <table> chứa tbody
    editedOrders.forEach(function(order) {
        // Tìm số thứ tự của hàng trong bảng bằng với order.index
        let rowIndex = -1;
        $table.find("tr").each(function(index, row) {
            if ($(row).find(".index").text() === order.index.toString()) {
                rowIndex = index;
                return false; // Thoát khỏi vòng lặp khi tìm thấy hàng
            }
        });

        // Nếu tìm thấy hàng, cập nhật dữ liệu của đơn hàng đã chỉnh sửa
        if (rowIndex !== -1) {
            let $row = $table.find("tr").eq(rowIndex);
            $row.find(".productCode").text(order.productCode);
            $row.find(".productName").text(order.productName);
            $row.find(".quantity").text(order.quantity);
            $row.find(".customerName").text(order.customerName);
            $row.find(".phoneNumber").text(order.phoneNumber);
        }
    });
}
//Hàm lấy ra số thứ tự bắt đầu của trang
function sumOrderOnPage(page) {
    let orderArrays = JSON.parse(localStorage.getItem("arrayPage")) || [];
    let startIndex = 0;

    // Duyệt qua mảng orderArrays để tính toán số thứ tự bắt đầu của trang
    for (let i = 1; i < page; i++) {
        let existingPage = orderArrays.find(order => order.page === i); // Tìm trang trong mảng orderArrays

        if (existingPage) {
            startIndex += 3 + existingPage.orderArr.length; // Nếu có đơn hàng mới trên trang, cộng số lượng đơn hàng đó vào startIndex
        } else {
            startIndex += 3; // Nếu không có đơn hàng mới, cộng 3 vào startIndex (giả định là có 3 đơn hàng trên mỗi trang)
        }
    }

    return startIndex;
}

//Chỉnh lại số thứ tự của các record theo các đơn hàng thêm mới, ví dụ page 1 có 3 record và thêm mới 2 record thì khi qua trang 2, số thứ tự bắt đầu là 6
function numbered(){
    let $table = $("tbody").closest("table"); // Lấy thẻ <table> chứa tbody

    // Tính toán số thứ tự bắt đầu của trang
    let startIndex = sumOrderOnPage(currentPage);

    // Lặp qua các hàng trong bảng và cập nhật số thứ tự cho mỗi hàng
    $table.find("tr").each(function(index, row) {
        let $indexCell = $(row).find(".index"); // Ô chứa số thứ tự

        // Nếu ô chứa số thứ tự tồn tại
        if ($indexCell.length > 0) {
            // Cập nhật số thứ tự cho hàng
            $indexCell.text(startIndex + index);
        }
    });
}

function loadInsertOrders(){
	let $table = $("tbody").closest("table"); // Lấy thẻ <table> chứa tbody
	let orderArrays = JSON.parse(localStorage.getItem("arrayPage")) || [];
	let editedOrders = JSON.parse(localStorage.getItem("editedOrders")) || [];
	let orderInPage = orderArrays.find(order => order.page === currentPage);//lấy ra page hiện tại
	let length = 0;

	if(orderInPage !== undefined){
		length = orderInPage.orderArr.length;
	}
	for (let i=0;i<length;i++){
		let rowIndex = orderInPage.orderArr[i];//lấy ra số thứ tự của order
        let order = editedOrders.find(order => order.index === rowIndex);//tìm đơn hàng có số thự tự ở trên 
		let $row = $table.find("tr").filter(function() {
    		return $(this).find(".index").text() === rowIndex.toString();//Lấy ra dòng chứa đơn hàng
		});
        $row.find(".productCode").text(order.productCode);
        $row.find(".productName").text(order.productName);
        $row.find(".quantity").text(order.quantity);
        $row.find(".customerName").text(order.customerName);
        $row.find(".phoneNumber").text(order.phoneNumber);
        createNewRow();
	}
}

function saveOrder() {
    // Lấy mảng editedOrders từ local storage
    let editedOrders = localStorage.getItem("editedOrders") || [];

    // Kiểm tra nếu mảng editedOrders không rỗng
    if (editedOrders.length > 0) {
        // Sử dụng jQuery để gửi dữ liệu
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/order/save",
            data: editedOrders, // Chuyển đổi đối tượng thành chuỗi JSON
            contentType: "application/json", // Đặt kiểu dữ liệu của yêu cầu là JSON
            success: function(response) {
                // Xử lý phản hồi từ máy chủ (nếu cần)
                console.log("Đã gửi dữ liệu thành công:", response);
                
                // Sau khi gửi thành công, bạn có thể xóa mảng editedOrders từ local storage
                //localStorage.removeItem("editedOrders");
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi nếu có
                console.error("Lỗi khi gửi dữ liệu:", error);
            }
        });
    } else {
        console.log("Không có dữ liệu để gửi.");
    }
}
