function ingredientPriceDelete() {
  $.ajax({
    type: "POST",
    url: '/ingredientPrice/delete',
    contentType: 'application/json',
    data: JSON.stringify(parseInt($("#ingredientPrice-input").data("id"))),
    success: (res) => {
      console.log(res);
      alert("삭제 완료");
      location.href = "/ingredientPrice/list";
    },
    error: (err) => {
      console.log(err.body);
      alert("삭제 실패!");
    }
  })
}