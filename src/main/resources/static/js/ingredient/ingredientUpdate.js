function updateIngredient(){

  $.ajax({
    type: "POST",
    url: "/ingredient/update",
    contentType: "application/json",
    data: JSON.stringify({
      id : $("#ingredient-input").data("id"),
      name : $("#ingredientName").val().trim(),
      unit : $("#ingredientUnit").val().trim(),
      category: $("#ingredientCategory").val().trim(),
    }),
    success: (res) => {
      console.log(res);
      alert("수정 완료");
      location.href = "/ingredient/list";
    },
    error: (err) => {
      console.log("Error:", err);
      alert(err.message);
    }
  })

}