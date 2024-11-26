$(document).ready(function () {
  $("#collapseThree input").each(function (){
    if($(this).val() !== " "){
      $(this).prop("readOnly", true);
    }
    else $(this).prop("readOnly", false);
  })
})
saveMonthPure = function(){
  //모든 input 필드 값을 포함하는 객체 생성
  let serializedDataArray = $("#collapseThree input").serializeArray();
  // 각 필드 값에서 쉼표 제거
  let totalMonthPure = 0;
  $.each(serializedDataArray, function (i, field) {
    field.value = field.value.replace(/,/g, ''); // 쉼표 제거

    // 숫자인 경우만 합계에 추가 (숫자가 아닐 경우 NaN 방지)
    if (!isNaN(field.value) && field.value.trim() !== '') {
      totalMonthPure += parseFloat(field.value);
    }
  });
  let dataObject = {};

  $.each(serializedDataArray, function (i, field) {
    dataObject[field.name] = field.value;
  });

  const month = $("#collapseThree").data("month");

  //추가 데이터 포함
  dataObject.month = month;
  dataObject.totalMonthPure = totalMonthPure;

  //각 필드 readOnly 처리
  $("#collapseThree input").prop("readOnly", true);
  //버튼 막기
  $(".salesMonthPureSave").prop("disabled", true);

  console.log(dataObject);

  //Ajax 요청
  $.ajax({
    type: "POST",
    url: "/sales/setMonthPure",
    contentType: "application/json",
    data: JSON.stringify(dataObject),
    success: (res) => {
      console.log("Response:", res);
      $("#cardMonthPure").prop("readOnly", true);
      $("#simpleMonthPure").prop("readOnly", true);
      $("#baeminMonthPure").prop("readOnly", true);
      $("#coupangMonthPure").prop("readOnly", true);
      $("#yogiyoMonthPure").prop("readOnly", true);
      $("#naverMonthPure").prop("readOnly", true);
      $("#tanyoMonthPure").prop("readOnly", true);
      alert("저장완료 및 수수료 계산 완료")
      location.href="/sales/salesLog";
    },
    error: (err) => {
      console.log("Error:", err);
      $("#collapseThree input").prop("readOnly", false);
      $(".salesMonthPureSave").prop("disabled", false);
      alert("에러발생!" + err);
    }
  });
}

modifyMonthPure = function(){
  //저장 버튼 활성화
  $(".salesMonthPureSave").prop("disabled", false);
  $("#cardMonthPure").prop("readOnly", false);
  $("#baeminMonthPure").prop("readOnly", false);
  $("#simpleMonthPure").prop("readOnly", false);
  $("#coupangMonthPure").prop("readOnly", false);
  $("#yogiyoMonthPure").prop("readOnly", false);
  $("#naverMonthPure").prop("readOnly", false);
  $("#tanyoMonthPure").prop("readOnly", false);

}