function clearForm() {
    // Chọn tất cả các input trong form và xóa giá trị của chúng
    $('form input').val('');

    // Kiểm tra nếu các checkbox tồn tại và đặt trạng thái của chúng thành false
    if ($('#inlineCheckbox1').length) {
        $('#inlineCheckbox1').prop('checked', false);
    }
    if ($('#inlineCheckbox2').length) {
        $('#inlineCheckbox2').prop('checked', false);
    }
}
