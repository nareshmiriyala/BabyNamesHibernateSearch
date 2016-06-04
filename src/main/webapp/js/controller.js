/**
 * Created by nareshm on 6/11/2015.
 */

var blogController = function (/* $scope, $location, $http */) {
    console.log("Blog Controller reporting for duty.");
};


var pageController = function (/* $scope, $location, $http */) {
    console.log("Page Controller reporting for duty.");

    // Activates the Carousel
    $('.carousel').carousel({
        interval: 5000
    });

    // Activates Tooltips for Social Links
    $('.tooltip-social').tooltip({
        selector: "a[data-toggle=tooltip]"
    })
};

var searchFunction = function ($scope,$http) {
    $scope.searchResp = {};

    $scope.search = function () {
        console.log("Search :" + $scope.query)
        $http.post('/search', $scope.query)
            .then(function (response) {
                $scope.searchResp = response.data;
                console.log($scope.searchResp);
            });
    };
};