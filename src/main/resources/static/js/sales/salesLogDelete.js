function setSalesLogDelete(){

    const pk = $(".modalSalesData").data("pk");

    $.ajax({
      type: "POST",
      url: "/sales/salesDelete",
      contentType: "application/json",
      data: JSON.stringify(pk),
      success: (res) => {
        console.log("Response:", res);
        alert(res)
        location.href="/sales/salesLog"
      },
      error: (err) => {
        console.log("Error:", err);
        alert(err);
      }
    })
}