angular.module('app', []).controller('indexController', function ($scope, $http) {
    $scope.loadPage = function (pageIndex = 1) {
        $http({
            url: 'http://localhost:8080/shop/products',
            method: 'GET',
            params: {
                'p': pageIndex
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.navList = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
            console.log(response.data);
        });
    };

    $scope.showProductInfo = function (productIndex) {
        $http({
            url: 'http://localhost:8080/shop/products/' + productIndex,
            method: 'GET'
        }).then(function (response) {
            alert(response.data.title);
        });
    };

    $scope.deleteProduct = function (productIndex) {
        $http({
            url: 'http://localhost:8080/shop/products/delete/' + productIndex,
            method: 'GET'
        }).then(function (response) {
            console.log(response);
            $scope.loadPage();
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadPage();
});