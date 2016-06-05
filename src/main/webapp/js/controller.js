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

var searchFunction = function ($scope, $http) {
    $scope.data = [];
    $scope.viewby = 10;
    $scope.currentPage = 1;
    $scope.itemsPerPage = $scope.viewby;
    $scope.maxSize = 5; //Number of pager buttons to show
    $scope.orderByField = 'count';
    $scope.reverseSort = true;
    $scope.search = function () {
        console.log("Search :" + $scope.query)
        $http.post('/search', $scope.query)
            .then(function (response) {
                $scope.data = response.data;
                $scope.totalItems = $scope.data.length;
            });
    };
    $scope.insertRecords=function () {
        console.log("Inserting Records");
        $http.post('/insertRecords');
    };
    $scope.indexRecords=function () {
        console.log("Indexing Records");
        $http.post('/indexRecords');
    };

    $scope.pageChanged = function () {
        console.log('Page changed to: ' + $scope.currentPage);
    };
    $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
    };
    $scope.setItemsPerPage = function (num) {
        $scope.itemsPerPage = num;
        $scope.currentPage = 1; //reset to first paghe
    };

};