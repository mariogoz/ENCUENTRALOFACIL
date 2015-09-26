var LocationData = document.getElementById("lista");
alert(LocationData);
/*[
    [49.2812668, -123.1035942, "26 E Hastings St, Vancouver" ], 
    [49.2814064, -123.1025187, "71 E Hastings St, Vancouver" ], 
    [49.2812336, -123.1020622, "122 E Hastings St, Vancouver" ], 
    [49.2813564, -123.1012253, "138 E Hastings St, Vancouver" ], 
    [49.2811625, -123.0985032, "242 E Hastings St, Vancouver" ]
];*/
 
function initialize()
{
    var map = 
        new google.maps.Map(document.getElementById('map-canvas'));
    var bounds = new google.maps.LatLngBounds();
    var infowindow = new google.maps.InfoWindow();
     
    for (var i in LocationData)
    {
        var p = LocationData[i];
        var latlng = new google.maps.LatLng(p[0], p[1]);
        bounds.extend(latlng);
         
        var marker = new google.maps.Marker({
            position: latlng,
            map: map,
            title: p[2]
        });
     
        google.maps.event.addListener(marker, 'click', function() {
            infowindow.setContent(this.title);
            infowindow.open(map, this);
        });
    }
     
    map.fitBounds(bounds);
}
 
google.maps.event.addDomListener(window, 'load', initialize);

