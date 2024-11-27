//포맷팅 함수 정의
function formatCurrency(value) {
  return Number(value).toLocaleString('ko-KR');
}

function getSalesLog(id) {

  const pk = parseInt(id.getAttribute("data-pk"));

  // 필요한 추가 작업 수행
  $.ajax({
    type: "POST",
    url: "/sales/getSalesLog",
    contentType: "application/json",
    data: JSON.stringify(pk),
    success: (res) => {
      console.log("Response:", res);
      const date = new Date(res.result.salesDate);
      //년-월-일 포맷팅
      const formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;

      $("#salesModal .modal-title").text("매출 상세 기록 " + " (" + formattedDate + ")")
      $(".modalSalesData").attr("data-pk", pk.toString());
      $("#cardSales").val(formatCurrency(res.result.cardSales));
      $("#cashSales").val(formatCurrency(res.result.cashSales));
      $("#simpleSales").val(formatCurrency(res.result.simpleSales));
      $("#baeminSales").val(formatCurrency(res.result.baeminSales));
      $("#coupangSales").val(formatCurrency(res.result.coupangSales));
      $("#yogiyoSales").val(formatCurrency(res.result.yogiyoSales));
      $("#naverSales").val(formatCurrency(res.result.naverSales));
      $("#tanyoSales").val(formatCurrency(res.result.tanyoSales));
      $("#totalSales").text(formatCurrency(res.result.totalSales));

      //모달오픈 (나중에 API 반환 성공시)
      $("#salesModal").show();
    },
    error: (err) => {
      console.log("Error:", err);
    }
  })
}