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

var searchController = function ($http) {
    var self = this;
    self.searchResp = {};

    self.search = function () {
        console.log("Search :" + self.query)
        $http.post('/search', self.query)
            .then(function (response) {
                self.rateResp = response.data;
                console.log(self.searchResp);
            });
    };
};