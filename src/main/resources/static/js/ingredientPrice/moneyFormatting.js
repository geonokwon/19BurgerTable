$(document).ready(function () {
  $("#total-price, #total-quantity").on("input", function () {
    let value = $(this).val().replace(/[^0-9]/g, "");
    value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    $(this).val(value);
  })
})