$("#ingredientPrice-save-btn").click(function () {
  console.log("저장로직 실행");
  const dataList = [];
  let isValid = true;

  $(".modalIngredientPriceData").each(function () {
    const $group = $(this)
    const ingredientName = $group.find(".ingredient").text().trim();
    const quantity = $group.find(".total-quantity").val().trim();
    const date = $group.find(".price-date").val().trim();
    const supplier = $group.find(".supplier").val().trim();
    const totalPrice = $group.find(".total-price").val().trim();

    //유효성 검사
    if (!ingredientName || !quantity || !date || !supplier || !totalPrice) {
      console.log("유효성 검사");
      alert("모든 필드를 입력해 주세요!");
      isValid = false;
    }

    dataList.push({
      ingredientName: ingredientName,
      totalQuantity: parseFloat(quantity.replace(/,/g, "")),
      priceDate: date,
      supplier: supplier,
      totalPrice: parseInt(totalPrice.replace(/,/g, ""), 10),
    });
  });

  if (isValid) {
    $.ajax({
      type: "POST",
      url: "/ingredientPrice/add",
      contentType: "application/json",
      data: JSON.stringify(dataList),
      success: (res) => {
        console.log(res);
        alert("저장 완료");
        location.href = "/ingredientPrice/list"
      },
      error: (err) => {
        console.log(err);
        alert("저장 실패");
      }
    })
  }

})