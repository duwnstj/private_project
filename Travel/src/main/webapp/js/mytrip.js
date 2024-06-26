document.addEventListener('DOMContentLoaded', function() {

var btn1 = document.getElementById("btn_1");
var btn2 = document.getElementById("btn_2");
var btn3 = document.getElementById("btn_3");
var btn4 = document.getElementById("btn_4");

    btn1.addEventListener("click", function() {
        window.location.assign("addschedule");
    });

    btn2.addEventListener("click", function() {
        window.location.assign("mytrip");
    });

    btn3.addEventListener("click", function() {
        window.location.assign("");
    });

    btn4.addEventListener("click", function() {
        window.location.assign("");
    });
    var sharePlanBtn = document.querySelectorAll('.sharePlanBtn');
     // 일정공유 버튼에 대한 클릭 이벤트 리스너
    sharePlanBtn.forEach(function(btn) {
        btn.addEventListener('click', function() {
            var form = btn.closest('form'); // 클릭된 버튼이 속한 폼을 찾음
            form.action = '/share'; // action 변경
            // 폼 제출
            form.submit();
        });
    });
    
$(document).ready(function(){
    $('.viewPlanBtn').click(function(){
        let planData = $(this).data('plan-no');
        $.ajax({
            type: 'GET',
            url: '/itinerary/',
            data: {planNo: planData},
            success: function(response){
				window.location.href = '/itinerary?planNo=' + planData;
            },
            error: function(xhr, status, error){
                alert('요청에 실패했습니다.');
            }
        }); // ajax
    }); // click()
}); // ready()
});