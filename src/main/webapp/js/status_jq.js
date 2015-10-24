/**
 * Created by anteastra on 20.10.2015.
 */

$(document).ready(function() {

    var interval = 500;   //number of mili seconds between each call
    var refresh;
    var isRefreshOn = true;

    refresh = function () {
        if (!isRefreshOn) {
            return;
        }
        $.ajax({
            url: "https://localhost:8081/status"
        }).then(function (data, status, jqxhr) {
            $('.status-ip').text(data.ip);
            $('.status-cpuUsage').text(data.cpuUsage);
            $('.status-freeMemory').text(data.freeMemory);
            $('.status-totalMemory').text(data.totalMemory);
            console.log(jqxhr);
            setTimeout(function () {
                refresh();
            }, interval);
        });
    };

    $( "#refresh_btn" ).click(function() {
        $( "#refresh_btn" ).toggleClass( "active" );
        if ($( "#refresh_btn" ).hasClass( "active" )) {
            isRefreshOn = true;
            refresh();
        } else {
            isRefreshOn = false;
        }
    });

    refresh();
    isRefreshOn = false;
});
