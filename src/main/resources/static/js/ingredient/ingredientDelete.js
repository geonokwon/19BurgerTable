function deleteIngredient(){
  const id = $("#ingredient-input").data("id");

  $.ajax({
    type: "POST",
    url: "/ingredient/delete",
    contentType: "application/json",
    dataType: "json",
    data: JSON.stringify({
      id: id
    }),
    success: (res) => {
      console.log(res)
      alert("삭제완료");
    },
    error: (err) => {
      console.log(err);
      alert("재료가 참조하고 있어 삭제 불가");
    }
  })



}