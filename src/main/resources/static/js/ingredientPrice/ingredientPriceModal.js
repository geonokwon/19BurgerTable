$("#ingredientPrice-Modal").on("hidden.bs.modal", function () {
  //모달 내부 콘텐츠 초기화
  menuEmpty();
  $("#ingredient-category").text("선택");
  $("#ingredient").text("선택");
  $("#ingredient-names").append(`<li><button class="dropdown-item">카테고리 선택</button></li>`);
  $("#total-quantity").val("");
  $("#supplier").val("");
  $("#price-date").val("");
  $("#total-price").val("");

});