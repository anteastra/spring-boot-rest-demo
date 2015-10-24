/**
 * Created by anteastra on 21.10.2015.
 */

var statusApp = angular.module("statusApp", []);

statusApp.controller("statusController", function ($scope, $http) {

    $scope.isRefreshOn = true;
    var interval = 500;

    var refresh = function() {
        if (!$scope.isRefreshOn) {
            return;
        }
        $http.get("https://localhost:8081/status").success(function(data) {
            $scope.ip = data.ip;
            $scope.cpuUsage = data.cpuUsage;
            $scope.freeMemory = data.freeMemory;
            $scope.totalMemory = data.totalMemory;
            setTimeout(function () {
                refresh();
            }, interval);
        });
    }

    refresh();
    $scope.isRefreshOn = false;

    $scope.refresh_btn = function($event){
        $(event.target).toggleClass("active");
        if ($(event.target).hasClass( "active" )) {
            $scope.isRefreshOn = true;
            refresh();
        } else {
            $scope.isRefreshOn = false;
        }
    };
});