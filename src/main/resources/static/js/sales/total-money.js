
$(document).ready(function () {

  $("#collapseThree input, #sales-input input").on("input", function () {

    //현재 input 필드의 값에서 숫자 외의 문자는 제거
    let value = $(this).val().replace(/[^0-9]/g, '');

    //3자리마다 쉼표 추가
    value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    //인풋 필드에 포맷된 값 다시 설정
    $(this).val(value);

    let total = 0;
    //모든 input 요소를 순회하며 값 합산
    $("#sales-input input").each(function () {
      //빈 값은 0 으로 처리 , 쉼표 제거
      const totalResult =  parseFloat($(this).val().replace(/,/g, '')) || 0;
      total += totalResult;
    });

    //합계 업데이트(한국 원화 표기법 사용)
    $("#totalSales").text(total.toLocaleString('ko-KR'));

  })
})

