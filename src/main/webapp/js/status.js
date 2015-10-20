/**
 * Created by anteastra on 20.10.2015.
 */

$(document).ready(function() {

    var interval = 500;   //number of mili seconds between each call
    var refresh;
    refresh = function () {
        $.ajax({
            url: "http://localhost:8080/status"
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
    refresh();
});
