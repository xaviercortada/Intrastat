/**
 * Created by xavier on 8/07/15.
 */

'use strict';

/* App Module */

var helloApp = angular.module('helloApp', [
    'ngResource','ngRoute', 'ui.bootstrap']);


helloApp.factory("peridoService", function(){
    var myfactory = {
        selected : undefined
    }

    return myfactory;

});

helloApp.factory('authHttpRequestInterceptor', function ($rootScope, $injector, authFactory, peridoService) {
    var authHttpRequestInterceptor = {
        request: function ($request) {
            var authFactory = $injector.get('authFactory');
            if (authFactory.isAuthenticated()) {
                $request.headers['auth-id'] = authFactory.getAuthData().authId;
                $request.headers['auth-token'] = authFactory.getAuthData().authToken;
            }
            if(peridoService.selected != undefined){
                $request.headers['per-id'] = peridoService.selected.id;
            }
            return $request;
        }};

    return authHttpRequestInterceptor;
});


helloApp.controller('indexController',['$scope','$log', 'loginFactory', 'authFactory', 'authDialogFactory', 'userFactory','PeriodoFactory','peridoService',
    function($scope, $log, loginFactory, authFactory, authDialogFactory, userFactory, periodoFactory, peridoService) {

    $scope.user = undefined;
    $scope.authData = undefined;
    $scope.loginCaption = 'login';
    $scope.action = '#/login';
    $scope.perTitle = 'periodo no sel.';
    $scope.periodo_style = 'periodo-invalid';

    $scope.periodo = undefined;

    $scope.$on('periodoChanged', function(){
        peridoService.setPeriodo(periodoFactory.getRawPeriodo());

        periodoChanged();
    });

    var periodoChanged = function(){
        if(peridoService.selected == undefined){
            $scope.perTitle = 'periodo no sel.';
            $scope.periodo_style = 'periodo-invalid';
        }else {
            $scope.perTitle = peridoService.selected.year + '/' + peridoService.selected.month;
            $scope.periodo_style = 'periodo-valid';
        }

    }

    $scope.$on('userChanged', function(){
        $scope.authData = authFactory.getAuthData();

        $scope.user = userFactory.getUser();

        $scope.loginCaption = 'logout';

        $scope.action = '#/logout';

        if($scope.user.periodo != undefined){
            $scope.periodo = $scope.user.periodo;
            peridoService.selected = $scope.periodo;

            periodoChanged();
        }


    });

    $scope.doAction = function(){
        if($scope.authData == undefined){
            $scope.login();
        }else{
            $scope.logout();
        }
    }

    $scope.logout = function(){
        $scope.authData = undefined;
        $scope.user = undefined;
        $scope.loginCaption = 'login';

        $scope.action = '#/login';
        authFactory.logout();

        peridoService.selected = undefined;

        periodoChanged();


    }

    $scope.login = function(){
        var modalInstance = authDialogFactory.dialog();

        modalInstance.result.then(function(data){
            loginFactory.login(data);
        },function(){
            $log.info("Modal dismissed at" + new Date());
        });

    }

}]);

function helloRouteConfig($routeProvider) {
    $routeProvider.
        when('/', {
            //controller: 'ListController',
            templateUrl: 'default.html'
        }).
        when('/listProveedor', {
            controller: 'ListController',
            templateUrl: 'listProveedores.html'
        }).
        when('/editProveedor', {
            controller: 'EditController',
            templateUrl: 'editProveedor.html'
        }).
        when('/createProveedor', {
            controller: 'CreateController',
            templateUrl: 'editProveedor.html'
        }).
        when('/listCategory', {
            controller: 'ListCategoryController',
            templateUrl: 'listCategory.html'
        }).
        when('/editCategory', {
            controller: 'EditCategoryController',
            templateUrl: 'editCategory.html'
        }).
        when('/createCategory', {
            controller: 'CreateCategoryController',
            templateUrl: 'editCategory.html'
        }).
        when('/listItem', {
            controller: 'ListItemController',
            templateUrl: 'listItem.html'
        }).
        when('/editItem', {
            controller: 'EditItemController',
            templateUrl: 'editItem.html'
        }).
        when('/createItem', {
            controller: 'CreateItemController',
            templateUrl: 'editItem.html'
        }).
        when('/periodo', {
            controller: 'PeriodoController',
            templateUrl: 'periodo.html'
        }).
        when('/login', {
            controller: 'AuthController',
            templateUrl: 'default.html'
        }).
        when('/excel', {
            controller: 'ExcelController',
            templateUrl: 'default.html'
        }).
        otherwise({
            redirectTo: '/'
        });
}


helloApp.config(function ($httpProvider) {
    $httpProvider.interceptors.push('authHttpRequestInterceptor');
})

helloApp.config(helloRouteConfig);
