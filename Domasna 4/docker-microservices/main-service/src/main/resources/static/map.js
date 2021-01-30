var mymap = L.map('mapid');
var zoom = 14;
var routingControl = L.routing.control({
    routeWhileDragging: true,
    draggableWaypoints: false,
    addWaypoints: false,
    router: L.Routing.mapbox('pk.eyJ1IjoicGFtYXBpIiwiYSI6ImNraWMyMWExZjBkc3AydHBvODJiODZrc3gifQ.9e-Ia8GJf19iraotSP3y6Q', { profile: 'mapbox/walking'}),
    createMarker: function (i, waypoint, n) {
        var marker_icon = destinationIcon
        var marker;
        console.log(waypoint);
        if(i==n-1){
            marker = L.marker(waypoint.latLng, {
                draggable: false,
                bounceOnAdd: false,
                bounceOnAddOptions: {
                    duration: 1000,
                    height: 800,
                },
                icon: marker_icon
            }).bindPopup("<div class='text-center p-1'><div>" + waypoint.name + "</div></div>");
        }
        return marker;
    }
}).addTo(mymap);

var destinationIcon = new L.Icon({
    iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

var startIcon = new L.Icon({
    iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

const queryString = new URLSearchParams(window.location.search);

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoicGFtYXBpIiwiYSI6ImNraWMyMWExZjBkc3AydHBvODJiODZrc3gifQ.9e-Ia8GJf19iraotSP3y6Q'
}).addTo(mymap);

function getMarkersAjax(){
    $.ajax({
        url: '/api/markers',
        success: function(result){
            for(var i=0;i<result.length;i++){
                var x = result[i].coordinates.x;
                var y = result[i].coordinates.y;
                var marker = L.marker([y, x]).bindPopup("<div class='text-center p-1'><div>" + result[i].name + "</div></br><div>" + result[i].type + "</div></br><button type='button' class='btn btn-primary btn-go' onclick='getRoute(this)' data='" + result[i].id +"'>Go</button></div>").addTo(mymap);
            }
        }
    });
}

getMarkersAjax();

getLocation()
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, deniedLocation);
    }
}

function deniedLocation(){
    mymap.setView([41.9936657, 21.4428736],zoom);
}

function showPosition(position) {
    mymap.setView([position.coords.latitude,position.coords.longitude],zoom)
    myCoords = [position.coords.latitude,position.coords.longitude]

    if(queryString.has('id')){
        var paramId = queryString.get('id');
        route(paramId);
    }
    L.marker([myCoords[0],myCoords[1]], { icon: startIcon}).bindPopup("My Location").addTo(mymap);
}

function getRoute(item){
    var objectId = item.getAttribute("data");
    mymap.closePopup();
    route(objectId);
}


function route(idParam){
    $.ajax({
        url: '/api/markers/' + idParam,
        success: function(result){
            routingControl.getPlan().setWaypoints([
                L.Routing.waypoint(L.latLng(myCoords[0],myCoords[1])),
                L.Routing.waypoint(L.latLng(result.coordinates.y, result.coordinates.x), '<div>' + result.name + '</div></br><div>' + result.type + '</div>')]);
        }
    })
}