<template>
    <div class="card-group">
        <div id="app" class="card">
            <div id="map" class="sticky-top z-index-2 rounded active">
                <p id="result"></p>
            </div>
            <!-- <button @click="getLocation">내위치</button> -->
            
        </div>
        <div class="card">
            <table class="table table-striped table-bordered align-middle">
                <!-- 장소 정보 -->
                <thead>
                    <tr>
                        <th scope="col">장소명</th>
                        <th scope="col">주소</th>
                        <th scope="col">TEL</th>
                    </tr>
                </thead>
                <tbody id="place-info">

                </tbody>
            </table>
        </div>
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
        level: 4
    };
    map = new kakao.maps.Map(mapContainer, mapOptions);
    //   map.setMapTypeId(kakao.maps.MapTypeId.HYBRID); 실제지형
    // 마커가 표시될 위치입니다 
    let markerPosition = new kakao.maps.LatLng(37.5012942, 127.0396273);
    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
        position: markerPosition,
        image: new kakao.maps.MarkerImage(
            'src/components/api/map-pin.png',
            new kakao.maps.Size(31, 35)),
        zIndex: 1,
    });
    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);


    var iwContent = '<div class ="myPosition"><span class="left"></span><span class="center">현재 위치입니다.</span><span class="right"></span></div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

    // 인포윈도우를 생성합니다
    var customOverlay = new kakao.maps.CustomOverlay({
        position: markerPosition,
        content: iwContent,
        zIndex: 1,
    });

    // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    customOverlay.setMap(map);
    setMarker();
    // 마우스 드래그로 지도 이동이 완료되었을 때 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
    kakao.maps.event.addListener(map, 'dragend', resetMarker);
    kakao.maps.event.addListener(map, 'zoom_changed', resetMarker);
}

// 지도 확대,축소,이동 시 마커 재설정하기
const resetMarker = () => {
    closeInfoWindow();
    deleteMarkers();
    document.getElementById('place-info').innerHTML = '';

    var latlng = map.getCenter();

    var message = '변경된 지도 중심좌표는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';

    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;
    infowindowList = [];
    markerList = [];

    setMarker();
}


const setMarker = () => {
    // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
    infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    // 장소 검색 객체를 생성합니다
    ps = new kakao.maps.services.Places(map);
    // 키워드로 장소를 검색합니다
    ps.keywordSearch('헬스장', placesSearchCB, { useMapCenter: true });

}

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
const placesSearchCB = function (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        // var bounds = new kakao.maps.LatLngBounds();

        for (var i = 0; i < 3; i++) {
            for (var i = 0; i < data.length; i++) {
                displayMarker(data[i]);
            }
            pagination.nextPage();
        }
    }
}

// 마커 저장하는 배열
let markerList = [];
// 마커 전부 삭제
function deleteMarkers() {
    for (var idx = 0; idx < markerList.length; idx++) {
        markerList[idx].setMap(null);
    }
}
// 인포윈도우 저장하는 배열
let infowindowList = [];
// 인포윈도우 전부 닫기
function closeInfoWindow() {
    for (var idx = 0; idx < infowindowList.length; idx++) {
        infowindowList[idx].close();
    }
}

const displayMarker = function (place) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
    });

    const start = new kakao.maps.LatLng(37.5012942, 127.0396273).toCoords()
    const end = new kakao.maps.LatLng(place.y, place.x).toCoords()

    // var iwContent = `<div style="padding:5px;white-space:nowrap">${place.place_name}<br><a href="https://map.kakao.com/link/map/멀티캠퍼스 역삼,37.5012942,127.0396273" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/?sName=멀티캠퍼스 역삼&eName=${place.place_name}" style="color:blue" target="_blank">길찾기</a></div>`;
    var iwContent = `<div style="padding:5px;white-space:nowrap;"><h5>${place.place_name}</h5>
        <span>${place.road_address_name}</span><br>
        <span class="jibun gray">(${place.address_name})</span><br>
        <span class="tel">${place.phone}</span></div>
        <a href="https://map.kakao.com/link/map/멀티캠퍼스 역삼,37.5012942,127.0396273" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/?sX=${start.La}&sY=${start.Ma}&sName=${'역삼%20멀티캠퍼스'}&eX=${end.La}&eY=${end.Ma}&eName=${place.place_name}" style="color:blue" target="_blank">길찾기</a></div>`;
    // // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다


    // // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        position: marker.position,
        content: iwContent,
        zIndex: 2,
    });
    // 마커 저장
    markerList.push(marker);
    // 인포 윈도우 저장
    infowindowList.push(infowindow);

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function () {
        // 인포윈도우 켜진거 모두 닫기
        closeInfoWindow();
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        // // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(map, marker);

    })
    document.getElementById('place-info').appendChild(getInfo(place));
}


// 클릭한 마커 정보를 placeInfo에 반환하는 함수입니다
function getInfo(place) {

    var el = document.createElement('tr'),
        itemStr =
            `
            <th>${place.place_name}</th>
            <td>${place.road_address_name}<br>
                (${place.address_name})</td>
            <td>${place.phone ? place.phone : '-'}</td>
            <td> </td>
        `;

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
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
    // map = new kakao.maps.Map(container, options);
    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    const mapTypeControl = new kakao.maps.MapTypeControl();

    // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    const zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


    let marker = new kakao.maps.Marker({
        position: myCenter,
        image: new kakao.maps.MarkerImage(
            'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png',
            new kakao.maps.Size(31, 35)),
        zIndex: 1,
    });

    var iwContent = '<div style="padding:5px;">현재 위치</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        position: myCenter,
        content: iwContent
    });

    // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    infowiqndow.open(map, marker);
    setMarker();
}

</script>
  
<style>
#app {

}

#map {
    max-width: 800px;
    aspect-ratio: 4/3;
    margin: 25px;
    top: 25%;
    border-radius: 5%;
}

.myPosition {
    margin-bottom: 96px;
}

.myPosition * {
    display: inline-block;
    vertical-align: top;
}

.myPosition .left {
    background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png") no-repeat;
    display: inline-block;
    height: 24px;
    overflow: hidden;
    vertical-align: top;
    width: 7px;
}

.myPosition .center {
    background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png) repeat-x;
    display: inline-block;
    height: 24px;
    font-size: 12px;
    line-height: 24px;
}

.myPosition .right {
    background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px 0 no-repeat;
    display: inline-block;
    height: 24px;
    overflow: hidden;
    width: 6px;
}</style>