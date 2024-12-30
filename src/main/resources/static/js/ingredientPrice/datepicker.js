$(document).ready(function () {
  // datepicker 초기화
  $(document).on('focus', '.price-date', function () { // 동적 요소에 대한 이벤트 바인딩
    $(this).datepicker({
      format: 'yyyy-mm-dd', // 데이터 포맷 형식
      endDate: '0d', // 가장 늦은 날짜 제한
      autoclose: true, // 클릭 시 자동 닫힘
      disableTouchKeyboard: false, // 모바일 키보드 설정
      templates: {
        leftArrow: '&laquo;',
        rightArrow: '&raquo;',
      }, // 화살표 커스텀
      showWeekDays: true, // 요일 표시
      title: '작성날짜', // 캘린더 상단 제목
      todayHighlight: true, // 오늘 날짜 하이라이트
      toggleActive: true, // 이미 선택된 날짜 클릭 시 해제
      weekStart: 0, // 시작 요일 설정 (0: 일요일)
      language: 'ko', // 언어 설정
    }).on('changeDate', function (e) {
      // 현재 클릭한 .price-date에 값을 설정
      $(this).val(e.format('yyyy-mm-dd'));
    });
  });
});