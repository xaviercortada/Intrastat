/**
 * Created by xavier on 8/07/15.
 */

'use strict';

/* App Module */

var helloApp = angular.module('helloApp', [
    'ngResource','ngRoute', 'ngStorage', 'ui.bootstrap']);


helloApp.factory("peridoService", function(){
    var myfactory = {
        selected : undefined
    }

    return myfactory;

});

helloApp.factory('authHttpRequestInterceptor', function ($rootScope, $injector, authFactory, peridoService, $localStorage) {
    var authHttpRequestInterceptor = {
        request: function ($request) {
            var authFactory = $injector.get('authFactory');
            if($localStorage.authorizationData && authFactory.isAuthenticated()) {
                $request.headers['auth-id'] = $localStorage.authorizationData.username;
                //$request.headers['auth-token'] = authFactory.getAuthData().authToken;
                $request.headers.Authorization = 'Bearer ' + $localStorage.authorizationData.token;
            }
            if(peridoService.selected != undefined){
                $request.headers['per-id'] = peridoService.selected.id;
            }
            return $request;
        }};

    return authHttpRequestInterceptor;
});


helloApp.controller('indexController',['$scope','$log', 'loginFactory', 'authFactory', 'authDialogFactory', 'userFactory','PeriodoFactory','peridoService','signinDialogFactory',
    function($scope, $log, loginFactory, authFactory, authDialogFactory, userFactory, periodoFactory, peridoService, signinDialogFactory) {

    $scope.user = undefined;
    $scope.authData = undefined;
    $scope.loginCaption = 'login';
    $scope.action = '#/login';
    $scope.perTitle = 'periodo no sel.';
    $scope.periodo_style = 'periodo-invalid';

    $scope.logged_in = false;

    $scope.periodo = undefined;

    $scope.$on('periodoChanged', function(){
        peridoService.selected = periodoFactory.getRawPeriodo();

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

    };

    $scope.$on('userChanged', function(){
        $scope.authData = authFactory                                                                                   .getAuthData();

        $scope.user = userFactory.user;

        $scope.loginCaption = 'logout';

        $scope.logged_in = true;

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
    };

    $scope.logout = function(){
        $scope.authData = undefined;
        $scope.user = undefined;
        $scope.loginCaption = 'login';

        $scope.logged_in = false;

        $scope.action = '#/login';
        authFactory.logout();

        peridoService.selected = undefined;

        periodoChanged();


    };

    $scope.login = function(){
        var modalInstance = authDialogFactory.dialog();

        modalInstance.result.then(function(data){
            loginFactory.login(data);
        },function(){
            $log.info("Modal dismissed at" + new Date());
        });

    };

    $scope.signin = function(){
        var modalInstance = signinDialogFactory.dialog();

        modalInstance.result.then(function(data){
            loginFactory.signin(data);
        },function(){
            $log.info("Modal dismissed at" + new Date());
        });

    };

    $scope.show_options = function(){
            return !$scope.logged_in;
    }

}]);

function helloRouteConfig($routeProvider) {
    $routeProvider.
        when('/', {
            controller: 'indexController',
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
        when('/listFactura', {
            controller: 'ListFacturaController',
            templateUrl: 'listFactura.html'
        }).
        when('/editFactura', {
            controller: 'EditFacturaController',
            templateUrl: 'editFactura.html'
        }).
        when('/createFactura', {
            controller: 'CreateFacturaController',
            templateUrl: 'editFactura.html'
        }).
        when('/listMaterial', {
            controller: 'ListMaterialController',
            templateUrl: 'listMaterial.html'
        }).
        when('/editMaterial', {
            controller: 'EditMaterialController',
            templateUrl: 'editMaterial.html'
        }).
        when('/createMaterial', {
            controller: 'CreateMaterialController',
            templateUrl: 'editMaterial.html'
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
        when('/activate/:activationCode', {
            templateUrl : 'default.html',
            controller : 'ActivationCtrl',
            access : {
                isFree: true
            }
        }).
        otherwise({
            redirectTo: '/'
        });
}

var regexIso8601 = /^(\d{4}|\+\d{6})(?:-(\d{2})(?:-(\d{2})(?:T(\d{2}):(\d{2}):(\d{2})\.(\d{1,})(Z|([\-+])(\d{2}):(\d{2}))?)?)?)?$/;

function convertDateStringsToDates(input) {
    // Ignore things that aren't objects.
    if (typeof input !== "object") return input;

    for (var key in input) {
        if (!input.hasOwnProperty(key)) continue;

        var value = input[key];
        var match;
        // Check for string properties which look like dates.
        if (typeof value === "string" && (match = value.match(regexIso8601)) && (match[2] != undefined)) {
            var milliseconds = Date.parse(match[0])
            if (!isNaN(milliseconds)) {
                input[key] = new Date(milliseconds);
            }
        } else if (typeof value === "object") {
            // Recurse into object
            convertDateStringsToDates(value);
        }
    }
}

helloApp.config(function ($httpProvider) {
    $httpProvider.interceptors.push('authHttpRequestInterceptor');

    $httpProvider.defaults.transformResponse.push(function(responseData){
        convertDateStringsToDates(responseData);
        return responseData;
    });
})

helloApp.config(helloRouteConfig);
