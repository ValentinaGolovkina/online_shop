angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/shop/api/v1';
    $scope.loadPage = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
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

    $scope.makeOrder = function () {
        console.log("Заказ отправлен");
        $http({
            url: contextPath + '/cart/order',
            method: 'GET'
        }).then(function (response) {
            alert("Заказ принят. Номер вашего заказа: " + response.data);
        });
    }

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.deleteProduct = function (productIndex) {
        $http({
            url: contextPath + '/products/' + productIndex,
            method: 'DELETE'
        }).then(function (response) {
            console.log(response);
            $scope.loadPage();
        });
    };

    $scope.deleteProductFromCart = function (productIndex) {
        $http({
            url: contextPath + '/cart/' + productIndex,
            method: 'DELETE'
        }).then(function (response) {
            console.log(response);
            $scope.loadCart();
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/cart',
            method: 'DELETE'
        }).then(function (response) {
            console.log(response);
            $scope.loadCart();
        });
    };

    $scope.incQuantity = function (productId) {
        $http({
            url: contextPath + '/cart/inc/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.decQuantity = function (productId) {
        $http({
            url: contextPath + '/cart/dec/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }


    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadPage();
    $scope.loadCart();
});