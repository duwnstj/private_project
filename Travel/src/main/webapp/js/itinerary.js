document.addEventListener('DOMContentLoaded', function() {
    let map;
    let markers = [];
    function initMap() {
        // 초기 맵 중심 설정 (첫 번째 장소로 설정)
        let mapCenter = { lat: destinations[0].placeLatitude, lng: destinations[0].placeLongitude };
        // 맵 생성
        map = new google.maps.Map(document.getElementById('map'), {
            center: mapCenter,
            zoom: 10 // 초기 줌 레벨 설정
        });

        // 장소 데이터를 순회하면서 마커 추가
        destinations.forEach(function(destination) {
            let newMarker = new google.maps.Marker({
                position: { lat: destination.placeLatitude, lng: destination.placeLongitude },
                map: map,
                title: destination.placeName,
                icon: {
                    url: '../images/map-marker.png'
                }
            });

            // 마커를 클릭하여 제거할 수 있는 기능 추가
            newMarker.addListener('click', function() {
                removeMarker(newMarker); // 클릭한 마커 삭제
            });

            markers.push(newMarker); // 마커를 배열에 추가
        });
    }
    initMap();

    // 클릭한 마커를 삭제하는 함수
    function removeMarker(markerToRemove) {
        // markers 배열에서 클릭한 마커 찾기
        markers = markers.filter(function(marker) {
            if (marker === markerToRemove) {
                marker.setMap(null); // 지도에서 마커 제거
                
                // 해당 마커의 위치정보 가져오기
                const position= marker.getPosition();
                const latitude= position.lat();
                const longitude= position.lng();
                
                //해당 위치정보를 가진 <tr>태그 찾기 후 삭제
                $('#placeTable tr').each(function(){
					if (parseFloat($(this).attr('data-latitude')) === latitude && parseFloat($(this).attr('data-longitude')) === longitude) {
                    $(this).remove();
                    return false;
				}
			});
                return false; // 배열에서도 마커 제거
            }
            return true; // 제거한 마커 외 다른 마커 유지
        });
    }
    
    function addMarker(position){
	var newMarker= new google.maps.Marker({
		position: position,
		map: map,
		title: 'selected Place',
		icon:{
			url: '../images/map-marker.png'
		}
	});
	// 마커 클릭 이벤트로 마커 삭제 기능추가
	newMarker.addListener('click', function(){
		removeMarker(newMarker);// 클릭한 마커 삭제
	});// newMarker()
	
	markers.push(newMarker);// 배열에 마커 추가
    map.setCenter(position);// 지도 이동
    }
    
    const initAutoComplete = () => {
            const input = document.getElementById("placeSearch");
            const options = {
                componentRestrictions: { country: nationalCode },
                fields: ["address_components", "geometry", "name"],
            };

            const autocomplete = new google.maps.places.Autocomplete(input, options);

            autocomplete.addListener("place_changed", () => {
                const place = autocomplete.getPlace();
                if (!place.geometry) {
                    console.error("Place details not found for input: ", input.value);
                    return;
                }
                const lat = place.geometry.location.lat();
                const lng = place.geometry.location.lng();
                addMarker({lat: lat, lng: lng});
                map.setCenter({ lat, lng });
                
                const placeName= place.name;
                
                addPlaceToTable(placeName, lat, lng);
                
                input.value= '';
            });
        };// initAutoComplete()
        
initAutoComplete();

function addPlaceToTable(placeName, lat, lng) {
    // 새로운 <tr> 요소를 생성하고 ID를 부여합니다.
    let newRow = $('<tr>').attr('id', 'place_tr');

    // <td> 셀을 생성하고 장소 이름을 설정합니다.
    let newCell = $('<td>').text(placeName);
    
    let deleteButton = $('<button>').addClass('deleteBtn').html('<i class="fa fa-trash"></i>');
    let deleteCell = $('<td>').append(deleteButton);
    
    // <td>를 <tr>에 추가합니다.
    newRow.append(newCell, deleteCell);
    
    newRow.attr('data-latitude', lat);
    newRow.attr('data-longitude', lng);
    
    // #placeTable ID를 가진 테이블의 tbody에 새로운 <tr>을 추가합니다.
    $('#placeTable').append(newRow);
}

$(document).on('click', '.deleteBtn', function(){
	var $tr= $(this).closest('tr');
	var latitude= parseFloat($tr.attr("data-latitude"));
	var longitude= parseFloat($tr.attr("data-longitude"));
	removeMarkerByPosition(latitude, longitude);
	$tr.remove();
});// deleteBtn

function removeMarkerByPosition(latitude, longitude){
	markers= markers.filter(function(marker){
		if (marker.getPosition().lat() === latitude && marker.getPosition().lng() === longitude){
			
			marker.setMap(null);// 지도에서 마커를 제거한다.
			return false;// 배열에서도 마커를 제거한다.
		}
		return true;// 제거한 마커 외에 다른 마커는 유지
	});
};// removeMarkerByPostion

    $("#placeTable").on('click', '#placeTable td', function() {
            let latitude = parseFloat($(this).closest('tr').attr("data-latitude"));
            let longitude = parseFloat($(this).closest('tr').attr("data-longitude"));

            // 지도 이동 처리 (Google Maps API 예시)
            moveMap(latitude, longitude);
    });

    function moveMap(latitude, longitude) {
        // 구체적인 지도 이동 로직을 여기에 구현
        // 여기서는 Google Maps API를 사용한 예시
        let mapCenter = new google.maps.LatLng(latitude, longitude);
        map.setCenter(mapCenter);
        map.setZoom(13); // 원하는 줌 레벨로 조정
    }
    
$('#planModify').click(function(){
	let destinations= [];
	$('#placeTable tr').each(function(){
		let placeName= $(this).find('td:first').text();
		let latitude = $(this).data('latitude');
		let longitude = $(this).data('longitude');
		destinations.push({
                placeName: placeName,
                placeLatitude: latitude,
                placeLongitude: longitude
            });// push
	});// function()

	$.ajax({
	type: 'PUT',
	url: '/itinerary/edit/' + planNo,
	contentType: 'application/json',
	data: JSON.stringify(destinations),
	success: function(response){
		alert('일정 수정 완료!');
	},
	error: function(xhr, status, error){
		console.error('Error:', error);
		alert('일정 수정 실패');
	}
    });// ajax
});// planModify

$('#planDelete').click(function() {
    $.ajax({
        type: 'DELETE',
        url: '/itinerary/delete/' + planNo, // 삭제할 일정의 planNo를 포함한 URL 설정
        success: function(response) {
            alert(response);
            window.location.href= '/mytrip';
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
            alert('일정 삭제 실패');
        }
    });
});

});
