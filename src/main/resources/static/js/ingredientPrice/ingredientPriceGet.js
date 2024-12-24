function ingredientListGet(categoryButton){
  const $categoryButton = $(categoryButton);
  const $container = $categoryButton.closest(".modalIngredientPriceData");
  const categoryResult = $categoryButton.text();

  $container.find(".ingredient-category").text(categoryResult);

  $.ajax({
    type: "POST",
    url: "/ingredientPrice/getIngredientNames",
    contentType: "text/plain",
    data: categoryResult,
    success: (res) => {
      console.log(res);
      const $ingredientDropdown = $container.find(".ingredient-names");
      $ingredientDropdown.empty();

      if (res.length > 0) {
        res.forEach((ingredient) => {
          $ingredientDropdown.append(
            `<li><button class="dropdown-item" onclick="ingredientNameGet(this)" data-value="${ingredient}">${ingredient}</button></li>`
          );
        });
      }
      else{
        $ingredientDropdown.append(`<li><button class="dropdown-item">해당 재료가 없음</button></li>`)
      }
    },
    error: (err) => {
      console.log(err);
      alert("카테고리 별 재료 불러오기 실패");
    }
  });
}


//선택된 재료이름으로 버튼 이름 변경 및 unit 가져오기
function ingredientNameGet(ingredientButton) {
  const $ingredientButton = $(ingredientButton); // 클릭된 재료 버튼
  const $container = $ingredientButton.closest(".modalIngredientPriceData"); // 버튼이 속한 행
  const ingredientName = $ingredientButton.text(); // 선택된 재료 이름

  // 해당 행의 재료 버튼 텍스트 업데이트
  $container.find(".ingredient").text(ingredientName);

  // AJAX 요청으로 단위 가져오기
  $.ajax({
    type: "POST",
    url: "/ingredientPrice/getIngredientUnit",
    contentType: "text/plain",
    data: ingredientName,
    success: (res) => {
      console.log(res);
      $container.find(".ingredient-unit").text(res); // 단위 업데이트
    },
    error: (err) => {
      console.log(err);
    },
  });
}

