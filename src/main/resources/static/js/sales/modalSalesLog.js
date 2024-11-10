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
      $("#cardSales").val(formatCurrency(res.result.cardSales));
      $("#cashSales").val(formatCurrency(res.result.cashSales));
      $("#simpleSales").val(formatCurrency(res.result.simpleSales));
      $("#baeminSales").val(formatCurrency(res.result.baeminSales));
      $("#coupangSales").val(formatCurrency(res.result.coupangSales));
      $("#yogiyoSales").val(formatCurrency(res.result.yogiyoSales));
      $("#naverSales").val(formatCurrency(res.result.naverSales));
      $("#tanyoSales").val(formatCurrency(res.result.tanyoSales));
      $("#totalSales").text(formatCurrency(res.result.totalSales));

      //수정버튼!
      //a태그로 href 로 주소 추가해서 보내기!

      //모달오픈 (나중에 API 반환 성공시)
      $("#salesModal").show();
    },
    error: (err) => {
      console.log("Error:", err);
    }
  })
}