let inventoryId = null;

$("#lowStock").on("input", function() {
  //현재 input 필드의 값에서 숫자 외의 문자는 제거
  $(this).val($(this).val().replace(/[^0-9.]/g, ''));
})


function setInventoryId(id) {
  inventoryId = parseInt($(id).data('id'));
}

function setInventoryLowStock(){

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
      location.href="/inventory/list"
    },
    error: (err) => {
      alert("임계값 설정 오류")
    }
  })
}

$('#inventory-Modal').on('hidden.bs.modal', function () {
  //input 필드 초기화
  $("#lowStock").val('');
});


