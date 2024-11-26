$(document).ready(function () {
  const selectedYearMonth = $('#datepickerContainer').data("year-month");
  console.log(selectedYearMonth);

  $('#datepickerContainer')
    .datepicker({
      format: 'yyyy-mm', // 년-월 형식으로 설정
      startView: 1, // 월 단위로 선택
      minViewMode: 1, // 최소 선택 모드를 '월'로 설정 (0: 일, 1: 월, 2: 년)
      todayHighlight: false, // 오늘 날짜 강조 끔
      endDate: '0d', // 선택 가능한 최대 날짜를 오늘로 설정
      language: 'ko', // 한국어 지원
      title: '작성 월 선택', // 캘린더 상단 타이틀
      templates: {
        leftArrow: '&laquo;',
        rightArrow: '&raquo;',
      }, //다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징
      weekStart: 0, //달력 시작 요일 선택하는 것 기본값은 짜인 일요일
  }).datepicker('setDate', selectedYearMonth || new Date())
    .on('changeDate', function (e) {
      const yearMonth = e.format('yyyy-mm');
      window.location.href = "/sales/salesLog?yearMonth=" + yearMonth; // 서버로 GET 요청 전송

    /* 이벤트의 종류 */
    //show : datePicker가 보이는 순간 호출
    //hide : datePicker가 숨겨지는 순간 호출
    //clearDate: clear 버튼 누르면 호출
    //changeDate : 사용자가 클릭해서 날짜가 변경되면 호출 (개인적으로 가장 많이 사용함)
    //changeMonth : 월이 변경되면 호출
    //changeYear : 년이 변경되는 호출
    //changeCentury : 한 세기가 변경되면 호출 ex) 20세기에서 21세기가 되는 순간

    });
  // 선택된 날짜에 커스텀 하이라이트 적용
  const $datepicker = $('#datepickerContainer');
  const highlightSelectedDate = () => {
    $datepicker.find('.datepicker-days td.active').removeClass('active'); // 기존 active 제거
    $datepicker
      .find(`td.day[data-date="${selectedYearMonth}"]`)
      .addClass('active'); // 선택된 날짜만 active 클래스 추가
  };
  highlightSelectedDate();
});
