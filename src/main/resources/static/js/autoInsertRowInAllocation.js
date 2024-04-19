// Lấy tất cả các dòng trong allocationForm
var rows = document.querySelectorAll('#allocationForm .row');

// Lặp qua từng dòng để kiểm tra xem có ô nào đã nhập dữ liệu không
var hasValue = false;
rows.forEach(function(row) {
    var inputs = row.querySelectorAll('input');
    inputs.forEach(function(input) {
        if (input.value.trim() !== '' && input.id != 'stt') {
            hasValue = true;
        }
    });
});

// Nếu có ít nhất một ô đã nhập dữ liệu, tạo thêm một dòng mới
if (hasValue) {
    var newRow = document.createElement('div');
    newRow.classList.add('row', 'mb-3', 'justify-content-center');

    var col = document.createElement('div');
        col.classList.add('col-xl-1', 'col-1');
	var input = document.createElement('input');
        input.classList.add('input-control', 'w-100');
        input.value = colValue;
        col.appendChild(input);
        newRow.appendChild(col);
    document.getElementById('allocationForm').appendChild(newRow);
}
