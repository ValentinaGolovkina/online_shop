angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
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


    $scope.loadOrders = function () {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    }

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/orders',
            method: 'POST'
        }).then(function (response) {
            alert('Заказ создан');
            $scope.loadCart();
            $scope.loadOrders();
        });
    }
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

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.summerUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $scope.loadOrders();
                }
            }, function errorCallback(response) {
            });
    };

    $scope.clearUser = function () {
        delete $localStorage.summerUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.summerUser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.summerUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.summerUser.token;
        $scope.loadOrders();
    }

    $scope.loadPage();
    $scope.loadCart();
    $scope.loadOrders();
});