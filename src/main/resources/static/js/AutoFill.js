
  async function fillDataProduct(cellData,cellInsert, type) {
    const data = cellData.textContent.trim();
    const response = await fetch(`http://localhost:8080/api/product/${type}?${type === 'name' ? 'productCode' : 'productName'}=${encodeURIComponent(data)}`);
    if (response.ok) {
      const newData = await response.text();
      cellInsert.textContent = newData;
    } else {
      console.error(`Lỗi khi gửi yêu cầu lấy ${type === 'name' ? 'tên' : 'mã'} sản phẩm:`, response.statusText);
    }
  }
  
  async function fillDataCustomer(cellData,cellInsert, type) {
    const data = cellData.textContent.trim();
    const response = await fetch(`http://localhost:8080/api/customer/${type}?${type === 'name' ? 'phoneNumber' : 'customerName'}=${encodeURIComponent(data)}`);
    if (response.ok) {
      const newData = await response.text();
      cellInsert.textContent = newData;
    } else {
      console.error(`Lỗi khi gửi yêu cầu lấy ${type === 'name' ? 'tên ' : 'số điện thoại của '} khách hàng:`, response.statusText);
    }
  }
