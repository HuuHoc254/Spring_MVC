
var editedOrders = [];
function saveEditedOrder(index) {
    var editedOrder = {};
    var row = document.querySelector('tr[data-index="' + index + '"]');
    editedOrder.orderId = row.getAttribute('data-order-id');
    editedOrder.productCode = row.querySelector('.productCode').innerText;
    editedOrder.quantity = row.querySelector('.quantity').innerText;
    editedOrder.phoneNumberCustomer = row.querySelector('.phoneNumberCustomer').innerText;

    // Check if order is already in the editedOrders array
    var existingOrderIndex = editedOrders.findIndex(function(order) {
        return order.orderId === editedOrder.orderId;
    });

    if (existingOrderIndex !== -1) {
        // Update existing order
        editedOrders[existingOrderIndex] = editedOrder;
    } else {
        // Add new order
        editedOrders.push(editedOrder);
    }
}

document.querySelector('.editable').forEach(function(cell) {
    cell.addEventListener('blur', function() {
        var rowIndex = this.parentElement.getAttribute('data-index');
        saveEditedOrder(rowIndex);
    });
});

console.log(editedOrders);