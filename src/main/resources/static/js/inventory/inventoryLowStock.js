let inventoryId = null;

$("#lowStock").on("input", function() {
  //현재 input 필드의 값에서 숫자 외의 문자는 제거
  $(this).val($(this).val().replace(/[^0-9.]/g, ''));
})


function setInventoryId(id) {
  inventoryId = parseInt(id.data('id'));
  console.log(inventoryId);
}

function setInventoryLowStock(){
  console.log($("#lowStock").val());

  $.ajax({
    type: "POST",
    url: "/inventory/lowStockAdd",
    contentType: "application/json",
    data: JSON.stringify({
      id: inventoryId,
      lowStock : $("#lowStock").val().trim()
    }),
    success: (res) => {
      alert("임계값 설정 완료")
    },
    error: (err) => {
      alert("임계값 설정 오류")
    }
  })
}


