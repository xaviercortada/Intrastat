/**
 * Created by xavier on 26/07/15.
 */

helloApp.factory('authDialogFactory', function($modal){
    var myObject = {};

    myObject.dialog = function() {
        return $modal.open({
            templateUrl: 'loginModal.html',
            controller: 'AuthDialogController',
            resolve: {
                aTitle: function () {
                    return 'Login en Intrastat';
                }
            }
        });
    }

    return myObject;
});

helloApp.factory('signinDialogFactory', function($modal){
    var myObject = {};

    myObject.dialog = function() {
        return $modal.open({
            templateUrl: 'signinModal.html',
            controller: 'AuthDialogController',
            resolve: {
                aTitle: function () {
                    return 'Signin en Intrastat';
                }
            }
        });
    }

    return myObject;
});

helloApp.controller('AuthDialogController',function($scope, $modalInstance, aTitle) {
    $scope.aTitle = aTitle;
    $scope.auth = {};

    $scope.close = function () {
        $modalInstance.dismiss();
    };

    $scope.ok = function () {
        $modalInstance.close($scope.auth);
    };
});

helloApp.factory('registerService',['$resource', function($resource){
    var registerResource = $resource('resources/register/',{},{
        register: {method: 'POST'},
        activation: {method: 'POST', params: {dest:"activation"}}
    });

    return registerResource;
}]);


helloApp.factory('activationService',['$resource', function($resource){
    var registerResource = $resource('resources/register/activation',{},{
        activation: {method: 'POST'}
    });

    return registerResource;

}]);

helloApp.factory('authService',['$resource', function($resource) {
    var authResource = $resource('resources/auth/',{},{
        login: {method: 'POST'}
    });

    return authResource;
}]);


helloApp.factory('userService',['$resource', function($resource) {
    return $resource('resources/user/finder/:username', {username: '@username'});

}]);

helloApp.factory('loginFactory',['$rootScope', '$location','$log', 'authFactory', 'authService','registerService','$localStorage',
    function($rootScope, $location, $log, authFactory, authService, registerService, $localStorage ) {

    var myfactory = {};


    myfactory.login2 = function(data){
        authService.login(data).$promise.
            then(function(retvalue){
                authFactory.setAuthData(retvalue);
            }).
            catch(function(response){
                $log.info("Modal dismissed at" + new Date());
            });
    };

    myfactory.login = function(data) {
        authService.login(data, function (retvalue) {
            var authData = {
                username: data.username,
                token: retvalue.token
            };

            authFactory.setAuthData(authData);
            $localStorage.authorizationData = authData;
            $location.path("/");
        }, function (result) {
            $location.path("/invalidActivationCode");
        });
    };


    myfactory.signin = function(data){
        registerService.register({
            email: data.username,
            firstName: data.firstname,
            lastName: data.lastname,
            password: data.password,
            passwordConfirmation: data.password});
    };

    return myfactory;

}]);


helloApp.factory('userFactory', function($rootScope, $resource, $log, Account, authFactory) {
        var userResource = $resource('resources/user/periodo/',{},{update: {method: 'POST'} });


        var myfactory = {
            user : undefined
        };

        $rootScope.$on('authChanged', function(){

            myfactory.user = undefined;

            Account.get({username: authFactory.authData.username}).$promise.
                then(function(retvalue) {
                    myfactory.user =  retvalue;
                    $rootScope.$broadcast('userChanged');
                }).
                catch(function(response){
                    $log.info("User name" + response);
                });


        });

         myfactory.getUser = function(){
             return this.user;
         }

        myfactory.setPeriodo = function(periodo){
            this.user.periodo = periodo;
            return userResource.update(this.user);
        }


        return myfactory;

});

helloApp.factory('authFactory',['$rootScope', '$log', '$localStorage',
    function($rootScope, $log) {

    var authFactory = {
        authData: undefined,
        user :undefined
    };

    authFactory.setAuthData = function (data) {
        this.authData = data;

        $rootScope.$broadcast('authChanged');

    };

    authFactory.getAuthData = function () {
        return this.authData;
    };

    authFactory.isAuthenticated = function () {
        return !angular.isUndefined(this.getAuthData());
    };

    authFactory.getUser = function(){
        return this.user;
    }

    authFactory.logout = function(){
        delete $localStorage.token;
        this.authData = undefined;
        this.user = undefined;
    }

    return authFactory;
}]);

helloApp.controller('AuthController', function($scope, $log, $route, $location, authService,  authFactory, authDialogFactory, registerService) {
        $scope.auth = {};

        $scope.modalInstance = authDialogFactory;//service.getAuth();


        $scope.modalInstance.result.then(function(data){

            registerService.save({password: 'aaaaaaa'});

            authService.login(data).$promise.
                then(function(retvalue){
                    $localStorage.authorizationData = {token: data.token} ;
                    authFactory.setAuthData(retvalue);
                }).
                catch(function(response){
                    $log.info("Modal dismissed at" + new Date());
                });

            $location.path('/');
        },function(){
            $log.info("Modal dismissed at" + new Date());
        });


    });



helloApp.controller('ActivationCtrl', function($scope, $routeParams, activationService, $location, $localStorage) {
    var ac = $routeParams.activationCode;

    $scope.activate = function() {
        //activationService.activation(JSON.stringify(ac), function(data) {
        activationService.activation(ac, function(data) {
            $localStorage.authorizationData = {token: data.token} ;
            $location.path( "/login" );
        }, function(result) {
            $location.path( "/invalidActivationCode" );
        });

    };

    $scope.activate();
});