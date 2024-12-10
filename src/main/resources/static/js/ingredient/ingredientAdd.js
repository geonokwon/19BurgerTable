function addIngredient() {
  $.ajax({
    type: "POST",
    url: "/ingredient/add",
    contentType: "application/json",
    data: JSON.stringify({
      name: $("#ingredientName").val(),
      unit: $("#ingredientUnit").val(),
      category: $("#ingredientCategory").val()
    }),
    success: (result) => {
      console.log(result)
      location.href = "/ingredient/list"
    },
    error: (err) => {
      console.log("Error:", err);
    }
  })
}

