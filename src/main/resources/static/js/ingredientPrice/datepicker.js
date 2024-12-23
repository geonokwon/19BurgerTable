$(document).ready(function () {
  $('#price-date')
    .datepicker({

      format: 'yyyy-mm-dd', //데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
      endDate: '0d', //달력에서 선택 할 수 있는 가장 느린 날짜. 이후로 선택 불가 ( d : 일 m : 달 y : 년 w : 주)
      autoclose: true, //사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
      disableTouchKeyboard: false, //모바일에서 플러그인 작동 여부 기본값 false 가 작동 true가 작동 안함.
      templates: {
        leftArrow: '&laquo;',
        rightArrow: '&raquo;',
      }, //다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징
      showWeekDays: true, // 위에 요일 보여주는 옵션 기본값 : true
      title: '작성날짜', //캘린더 상단에 보여주는 타이틀
      todayHighlight: true, //오늘 날짜에 하이라이팅 기능 기본값 :false
      toggleActive: true, //이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
      weekStart: 0, //달력 시작 요일 선택하는 것 기본값은 짜인 일요일
      language: 'ko', //달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
    })
    .on('changeDate', function (e) {
      $('#price-date').val(e.format('yyyy-mm-dd'));

    });
});
