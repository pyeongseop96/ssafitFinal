<template>
    <div id="app">
        <div id="map"></div>
        <button @click="getLocation">내위치</button>
    </div>
</template>
  
<script setup>

import { onMounted } from 'vue';

let map = null;
let ps = null;
let infowindow = null;

onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        initMap();
    } else {
        const script = document.createElement('script');
        /* global kakao */
        script.onload = () => kakao.maps.load(initMap);
        script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY}&libraries=services`;
        document.head.appendChild(script);
    }
})

const initMap = () => {
    let mapContainer = document.getElementById('map');
    let mapOptions = {
        center: new kakao.maps.LatLng(37.5012942, 127.0396273),
        level: 6
    };
    map = new kakao.maps.Map(mapContainer, mapOptions);
    //   map.setMapTypeId(kakao.maps.MapTypeId.HYBRID); 실제지형
    // 마커가 표시될 위치입니다 
    let markerPosition = new kakao.maps.LatLng(37.5012942, 127.0396273);
    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
        position: markerPosition
    });
    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);


    var iwContent = '<div style="padding:5px;">현재 위치</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        position: markerPosition,
        content: iwContent
    });

    // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    infowindow.open(map, marker);
    setMarker();
}

const setMarker = () => {
    // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
    infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    // 장소 검색 객체를 생성합니다
    ps = new kakao.maps.services.Places(map);
    // 키워드로 장소를 검색합니다
    ps.keywordSearch('헬스장', placesSearchCB);
}

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
const placesSearchCB = function (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        // var bounds = new kakao.maps.LatLngBounds();
        for (var i = 0; i < data.length; i++) {
            displayMarker(data[i]);
            // bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        // map.setBounds(bounds);
    }
}

const displayMarker = function (place) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x)
    });
    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다

        var iwContent = `<div style="padding:5px;white-space:nowrap">${place.place_name}<br><a href="https://map.kakao.com/link/map/멀티캠퍼스 역삼,37.5012942,127.0396273" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/?sName=멀티캠퍼스 역삼&eName=${place.place_name}" style="color:blue" target="_blank">길찾기</a></div>`;
        // // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

        // // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            position: marker.position,
            content: iwContent
        });

        // // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(map, marker);
    });
}

const getLocation = () => {
    let myCenter = new kakao.maps.LatLng(37.5012942, 127.0396273);
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;
            myCenter = new kakao.maps.LatLng(lat, lon);
            new kakao.maps.Marker({
                map,
                position: myCenter,
            });
            map.setCenter(myCenter);
        });
    }
    const container = document.getElementById('map');
    const options = {
        center: myCenter,
        level: 3,
    }; // 지도 객체를 등록합니다.
    map = new kakao.maps.Map(container, options);
    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    const mapTypeControl = new kakao.maps.MapTypeControl();

    // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    const zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    var iwContent = '<div style="padding:5px;">현재 위치</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        position: markerPosition,
        content: iwContent
    });

    // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    infowindow.open(map, marker);
    setMarker();
}

</script>
  
<style>
#map {
    width: 800px;
    height: 600px;
}
</style>