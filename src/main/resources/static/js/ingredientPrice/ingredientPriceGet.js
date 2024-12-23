function ingredientListGet(category){
  menuEmpty();
  let categoryResult = $(category).text();
  $("#ingredient-category").text(categoryResult);

  $.ajax({
    type: "POST",
    url: "/ingredientPrice/getIngredientNames",
    contentType: "text/plain",
    data: categoryResult,
    success: (res) => {
      console.log(res);
      menuAdd(res);
    },
    error: (err) => {
      console.log(err);
      alert("카테고리 별 재료 불러오기 실패");
    }
  });
}

//Ajax 통신을 통해 받아온 List 재료 드롭다운 매뉴에 추가
function menuAdd(ingredientList) {
  if(ingredientList.length > 0){
    ingredientList.forEach((ingredient) => {
      $("#ingredient-names").append(`<li><button class="dropdown-item" onclick="ingredientNameGet(this)" id="${ingredient}">${ingredient}</button></li>`);
    })
  }
  else {
    $("#ingredient-names").append(`<li><button class="dropdown-item">해당 재료가 없음</button></li>`);
  }
}

//받아온 재료 초기화
function menuEmpty() {
  $("#ingredient-names").empty();
}
//선택된 재료이름으로 버튼 이름 변경
function ingredientNameGet(ingredientName) {
  let getIngredientName = $(ingredientName).text();
  $("#ingredient").text(getIngredientName);
}