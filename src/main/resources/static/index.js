angular.module('app', []).controller('indexController', function ($scope, $http) {
    $scope.loadProducts = function () {
        $http({
            url: 'http://localhost:8080/shop/products',
            method: 'GET',
            params: {}
        }).then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    };

    $scope.loadPage = function (pageIndex = 1) {
        $http({
            url: 'http://localhost:8080/shop/products_page',
            method: 'GET',
            params: {
                'p': pageIndex
            }
        }).then(function (response) {
            console.log(response);
        });
    };

    $scope.counterValue = 1;

    $scope.clickIncrementButton = function () {
        $scope.counterValue += 1;
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
        });
    };

    $scope.showProductInfoWithoutRequest = function (productIndex) {
        alert($scope.products[productIndex - 1].title); // Сильно упрощенный пример, в реальности работать не будет..
    };

    $scope.loadProducts();
});