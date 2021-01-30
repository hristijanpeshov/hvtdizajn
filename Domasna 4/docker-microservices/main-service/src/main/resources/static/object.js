function redmap(item){
    var id = item.getAttribute('data-id');
    var type = item.getAttribute('data-type');
    location.href = '/navigate?id=' + id + "&type=" + type;
}


function listTable(result, show) {
    console.log(result);
    document.getElementById("t-body").innerHTML = "";
    if(result == '') {
        document.getElementById('errorFile').innerHTML = "No objects found!";
        return;
    }else if(result!='')
    {
        document.getElementById('errorFile').innerHTML = "";
    }
    console.log(result.length)
    for (var i = 0; i < result.length; i++) {
        var tr = document.createElement("tr");
        tr.setAttribute("class", "hoverable")
        tr.setAttribute("onClick", "redmap(this)")
        tr.setAttribute("data-id", result[i].moneyObject.id);
        tr.setAttribute("data-type", result[i].moneyObject.type);
        var name = document.createElement("td");
        name.innerHTML = result[i].moneyObject.name;
        tr.appendChild(name);

        // var type = document.createElement("td");
        // type.innerHTML = result[i].moneyObject.type;
        // tr.appendChild(type);

        var dist = document.createElement("td");
        if(show)
            dist.innerHTML = '-';
        else dist.innerHTML = result[i].distance;
        tr.appendChild(dist);
        document.getElementById("t-body").appendChild(tr);

    }
}

function handle(e){
    if(e.keyCode === 13){
        getLocation("search"); // Ensure it is only this code that runs
    }
}

function topTen(position){
    $.ajax({
        url: '/api/search/topTen',
        type: "POST",
        data: jQuery.param({ object: $('#object').val() ,lat: position.coords.latitude, lon : position.coords.longitude, search: $('#search').val()}),
        success: function(result){
            listTable(result, false);
        }
    })
}

function fail(){
    $.ajax({
        url: '/api/search/locationDenied',
        type: "POST",
        data: jQuery.param({ object: $('#object').val() , search: $('#search').val()}),
        success: function(result){
            listTable(result, true);
        }
    })
}

getLocation("search")
function getLocation(t) {
    if (navigator.geolocation) {
        if(t=="search")
            navigator.geolocation.getCurrentPosition(ajaxCall, fail)
        if(t=="topTen")
            navigator.geolocation.getCurrentPosition(topTen, fail)

    }
}

function ajaxCall(position){
    $.ajax({
        url: '/api/search',
        type: "POST",
        data: jQuery.param({ object: $('#object').val() ,lat: position.coords.latitude, lon : position.coords.longitude, search: $('#search').val()}),
        success: function(result){
            listTable(result, false);
        }
    })
}