$(document).ready(function () {
  //필드 유효성 체크 함수
  function checkValidity() {
    let total = $("#totalSales").text().trim(); // `#totalSales`에서 텍스트 가져오기
    let salesDate = $("#salesDate").val().trim();
    let userName = $("#userName").val().trim();

    //total이 0이 아니고, salesDate와 userName이 비어있지 않을 때만 버튼 활성화
    console.log("Total:", total);
    console.log("Sales Date:", salesDate);
    console.log("User Name:", userName);

    if (total && total !== "0" && salesDate && userName) {
      $("#saveButton").prop("disabled", false);
    } else {
      $("#saveButton").prop("disabled", true);
    }
  }

  //각 필드의 변경 감지 이벤트
  $("#datepickerContainer").on('changeDate', checkValidity)

  //#totalSales 변경 감지 설정
  const targetNode = document.getElementById('totalSales');
  //이거 알아봐야함
  const observer = new MutationObserver(checkValidity);
  observer.observe(targetNode, { childList: true, characterData: true, subtree: true });

  //Save 버튼 클릭 시 실행되는 함수
  $("#saveButton").on("click", function () {
    let total = $("#totalSales").text().trim().replace(/,/g, ''); // 쉼표 제거
    let salesDate = $("#salesDate").val()?.trim();
    let userName = $("#userName").val()?.trim();

    if (total !== "0" && salesDate && userName) {
      //모든 input 필드 값을 포함하는 객체 생성
      let serializedDataArray = $("#sales-input input").serializeArray();
      // 각 필드 값에서 쉼표 제거
      $.each(serializedDataArray, function (i, field) {
        field.value = field.value.replace(/,/g, ''); // 쉼표 제거
      });
      let dataObject = {};

      $.each(serializedDataArray, function (i, field) {
        dataObject[field.name] = field.value;
      });

      //추가 데이터 포함
      dataObject.totalSales = total;
      dataObject.salesDate = salesDate;
      dataObject.userName = userName;


      console.log(dataObject);

      //Ajax 요청
      $.ajax({
        type: "POST",
        url: "/sales/save",
        contentType: "application/json",
        data: JSON.stringify(dataObject),
        success: (res) => {
          console.log("Response:", res);
          alert("저장완료!");
          location.href="/sales/salesLog"
        },
        error: (err) => {
          console.log("Error:", err);
          alert("에러발생!" + err);
        }
      });
    }
  });
  //초기 유효성 체크 실행
  checkValidity();
});