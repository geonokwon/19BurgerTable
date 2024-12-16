function ingredientGet(data){
  $("#createIngredient").hide();
  $("#deleteIngredient").removeClass("d-none").show();
  $("#updateIngredient").removeClass("d-none").show();

  $(".modal-title").text("재료 수정/삭제");

  const id = parseInt(data.getAttribute("data-id"));
  console.log(id);

  $.ajax({
    type: "POST",
    url: "/ingredient/get",
    contentType: "application/json",
    data: JSON.stringify(id),
    success: (res) => {
      console.log(res)
      //여기 가져와서 Modal에 맞게 데이터 파싱
      $("#ingredient-input").attr("data-id", res.id);
      $("#ingredientName").val(res.name);
      $("#ingredientUnit").val(res.unit);
      $("#ingredientCategory").val(res.category);
    },
    error: (err) => {
      console.log(err)
    }
  })

  //공용 모달으로 수정 시에는 수정 버튼 및 타이틀 변경 (모달 닫힐시 원상태 복구)
  $("#ingredient-Modal").on("hide.bs.modal", function () {
    $("#createIngredient").show();
    $("#deleteIngredient").addClass("d-none").hide();
    $("#updateIngredient").addClass("d-none").hide();
    $(".modal-title").text("재료 추가");

    $("#ingredientName").val("");
    $("#ingredientUnit").val("");
    $("#ingredientCategory").val("");
  })


}