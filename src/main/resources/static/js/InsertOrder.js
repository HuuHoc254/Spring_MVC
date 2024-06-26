  // Lấy bảng và hàng cuối cùng
 var table = $("table");
 $(document).ready(function() {
	numbered();
	loadEditedOrders();
    createNewRow();
    
    // Sự kiện khi người dùng thay đổi dữ liệu trong hàng mới
    table.on("blur", "td[contenteditable=true]", function() {
	    $(this).removeClass("bg-danger");
	     $(this).removeAttr("title");
	     $(this).removeAttr("data-original-title");
        createNewRow();
    });
    loadInsertOrders();
    displayErrorFromLocalStorage();
    });
    
// Hàm để tạo hàng mới
function createNewRow() {
    var lastRow = table.find("tr:last");
    
    // Kiểm tra nếu người dùng đã nhập ít nhất một ô trong hàng cuối cùng
    var isRowNotEmpty = false;
    lastRow.find("td:gt(1):lt(6)").each(function() {
        if ($(this).text().trim()) {
            isRowNotEmpty = true;
            return false; // Thoát vòng lặp khi gặp ô không rỗng
        }
    });
    if(table.find("tr").length == 1){
    	 isRowNotEmpty = true;
    }
    // Nếu hàng cuối cùng không rỗng, tạo một hàng mới với số thứ tự tiếp theo
    if (isRowNotEmpty) {
        var newRow = $("<tr>").appendTo(table);
       /* var nextIndex = (table.find("tr").length - 1); // Lấy số thứ tự tiếp theo*/
       var stt = lastRow.find(".index").text() || '0';
         var nextIndex = parseInt(stt) + 1;
        // Thêm số thứ tự vào hàng mới
        $("<td>", {
            "text": nextIndex,
            "class": "text-center index"
        }).appendTo(newRow);
        
        // Thêm các ô có thể chỉnh sửa vào hàng mới
        for (var i = 0; i < 10; i++) {
            var cellClass = "";
            var edit = false;
            switch (i) {
                case 1:
                    cellClass = "productCode";
                    edit = true;
                    break;
                case 2:
                    cellClass = "productName";
                    edit = true;
                    break;
                case 4:
                    cellClass = "quantity text-center";
                    edit = true;
                    break;
                case 5:
                    cellClass = "customerName";
                    edit = true;
                    break;
                case 6:
                    cellClass = "phone";
                    edit = true;
                    break;
            }
            $("<td>", {
                "class": cellClass,
                "contenteditable": edit,
            }).appendTo(newRow);
        }
    }
}