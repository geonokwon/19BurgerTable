function ingredientPriceUpdate() {

  $.ajax({
    type: "POST",
    url: "/ingredientPrice/update",
    contentType: "application/json",
    data: JSON.stringify({
      id: parseInt($("#ingredientPrice-input").data("id")),
      totalQuantity: parseFloat($("#total-quantity").val().replace(/,/g, "")),
      priceDate: $("#price-date").val().trim(),
      supplier: $("#supplier").val().trim(),
      totalPrice: parseInt($("#total-price").val().replace(/,/g, ""), 10)
    }),
    success: (res) => {
      console.log(res);
      location.href = "/ingredientPrice/list";
    },
    error: (err) => {
      console.log(err);
    }

  })

}