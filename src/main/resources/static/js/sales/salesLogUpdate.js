function setSalesLogUpdate(){
  const pk = $(".modalSalesData").data("pk");
  location.href = "/sales/salesUpdate?pageNum=" + pk;
}