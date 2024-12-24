let groupIndex = 1;

$(document).ready(function () {

  $("#ingredientPrice-add-btn").click(function () {
    console.log(groupIndex);
    if (groupIndex <= 10) {
      groupIndex++;

      //템플릿 복제
      const $newGroup = $("#ingredientPrice-input")
        .clone()
        .removeAttr("id")
        .attr("data-index", groupIndex);

      //필드 초기화
      $newGroup.find(".ingredient").text("선택");
      $newGroup.find(".ingredient-category").text("선택");
      $newGroup.find(".ingredient-names").empty().append(
        `<li><button class="dropdown-item">카테고리 선택</button></li>`
      );
      $newGroup.find("input").val("");
      $newGroup.find(".ingredient-unit").text("-");

      //새로운 그룹 추가
      $("#ingredientPrice-input").parent().append($newGroup);
    }
  })

})