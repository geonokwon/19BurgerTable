function ingredientPriceDelete() {
  $.ajax({
    type: "POST",
    url: '/ingredientPrice/delete',
    contentType: 'application/json',

  })
}