$(document).ready(function () {
  //데이터 피커
  // 서버에서 전달된 기존 날짜 가져오기
  let selectedDate = $('#salesDate').val(); // input 요소의 value 값 읽기
  console.log(selectedDate);

  //시간 제거 후 데이터 피커가 읽을 수 있는 형식으로 변환
  if (selectedDate) {
    selectedDate = selectedDate.split(' ')[0]; // "2024-11-13 09:00:00.0" → "2024-11-13"
  }

  $('#datepickerContainer')
    .datepicker({
      format: 'yyyy-mm-dd', // 날짜 형식 설정
      endDate: '0d', // 선택 가능한 최대 날짜를 오늘로 설정
      //다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징
      showWeekDays: true, // 위에 요일 보여주는 옵션 기본값 : true
      title: '작성날짜', //캘린더 상단에 보여주는 타이틀
      toggleActive: true, //이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
      weekStart: 0, //달력 시작 요일 선택하는 것 기본값은 짜인 일요일
      language: 'ko' //달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
    }).datepicker('setDate', selectedDate || new Date())
      .on('changeDate', function (e) {
        $('#salesDate').val(e.format('yyyy-mm-dd'));
      });



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
      dataObject.salesId = $("#sales-input").data("pk");
      console.log($("#sales-input").data("pk"));


      console.log(dataObject);

      //Ajax 요청
      $.ajax({
        type: "POST",
        url: "/sales/update",
        contentType: "application/json",
        data: JSON.stringify(dataObject),
        success: (res) => {
          console.log("Response:", res);
          alert("수정완료!");
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