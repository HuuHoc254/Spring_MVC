$(document).ready(function() {
    // Gọi hàm fillDataProduct hoặc fillDataCustomer khi có sự kiện blur trong các ô có thuộc tính contenteditable="true"
    $("tbody").on("blur", "[contenteditable=true]", function() {
		
        var cell = $(this);
        var classList = cell.attr("class"); // Lấy tất cả các class của cell
        var classArray = classList.split(" "); // Tách chuỗi thành một mảng các class
        var columnName = classArray[0]; // Lấy phần tử đầu tiên của mảng, đó chính là class đầu tiên
        var type = "";
        
        // Xác định loại dữ liệu và gọi hàm fillDataProduct hoặc fillDataCustomer tương ứng
        if (columnName === "productCode" || columnName === "productName") {
            type = (columnName === "productCode") ? "name" : "code";
            fillDataProduct(cell, type).then(() => {
            });
        } else if (columnName === "customerName" || columnName === "phoneNumber") {
            type = (columnName === "customerName") ? "phone" : "name";
            fillDataCustomer(cell, type).then(() => {
            });
        }

		checkValue(cell);
    });

    
});

async function fillDataProduct(cellData, type) {
    const data = $(cellData).text().trim();
    if(type == 'code'){
		cellInsert = $(cellData).closest("tr").find(".productCode");
	} else {
		cellInsert = $(cellData).closest("tr").find(".productName");
	}
	try {
	    const response = await $.ajax({
	        url: `http://localhost:8080/api/product/${type}`,
	        method: 'GET',
	        data: {
	            [type === 'name' ? 'productCode' : 'productName']: data
	        }
	    });
	    cellInsert.text(response);
    } catch (error) {
        console.error('Error:', error);
    }
}

async function fillDataCustomer(cellData, type) {
    const data = $(cellData).text().trim();

	if(type == 'phone'){
		cellInsert = $(cellData).closest("tr").find(".phoneNumber");
	} else {
		cellInsert = $(cellData).closest("tr").find(".customerName");
	}
	try {
	    const response = await $.ajax({
	        url: `http://localhost:8080/api/customer/${type}`,
	        method: 'GET',
	        data: {
	            [type === 'name' ? 'phoneNumber' : 'customerName']: data
	        }
	    });
	
	    cellInsert.text(response);
    } catch (error) {
        console.error('Error:', error);
    }
}
