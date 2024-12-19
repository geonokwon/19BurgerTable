function ingredientListGet(category){
  let categoryResult = $(category).text();
  $("#ingredient-category").text(categoryResult);

  $.ajax({
    type: "POST",
    url: "/api/ingredients/get",
    contentType: "application/json",
    data: JSON.stringify({
      category: category,
    }),
    success: (res) => {
      console.log(res)
    }
  })

}