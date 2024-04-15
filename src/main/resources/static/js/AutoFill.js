
  async function fillData(cellData,cellInsert, type) {
    const data = cellData.textContent.trim();
    const response = await fetch(`http://localhost:8080/api/product/${type}?${type === 'name' ? 'productCode' : 'productName'}=${encodeURIComponent(data)}`);
    if (response.ok) {
      const newData = await response.text();
      cellInsert.textContent = newData;
    } else {
      console.error(`Lỗi khi gửi yêu cầu lấy ${type === 'name' ? 'tên' : 'mã'} sản phẩm:`, response.statusText);
    }
  }
