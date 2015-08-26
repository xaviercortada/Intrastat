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

helloApp.controller('AuthDialogController',function($scope, $modalInstance, aTitle) {
    $scope.aTitle = aTitle;
    $scope.auth = {
        username:"",
        password :""};

    $scope.close = function () {
        $modalInstance.dismiss();
    };

    $scope.ok = function () {
        $modalInstance.close($scope.auth);
    };
});

helloApp.factory('authService',['$resource', function($resource) {
    var authResource = $resource('resources/auth/',{},{
        login: {method: 'POST'}
    });

    return authResource;
}]);


helloApp.factory('userService',['$resource', function($resource) {
    return $resource('resources/user/finder/:username', {username: '@username'});

}]);

helloApp.factory('loginFactory',['$rootScope', '$log', 'authFactory', 'authService',
    function($rootScope, $log, authFactory, authService ) {

    var myfactory = {}


    myfactory.login = function(data){
        authService.login(data).$promise.
            then(function(retvalue){
                authFactory.setAuthData(retvalue);
            }).
            catch(function(response){
                $log.info("Modal dismissed at" + new Date());
            });
    }

    return myfactory;

}]);


helloApp.factory('userFactory', function($rootScope, $resource, $log, userService, authFactory) {
        var userResource = $resource('resources/user/periodo/',{},{update: {method: 'POST'} });


        var myfactory = {
            user : undefined
        };

        $rootScope.$on('authChanged', function(){

            myfactory.user = undefined;

            userService.get({username: authFactory.authData.authId}).$promise.
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

helloApp.factory('authFactory',['$rootScope', '$log',
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
        this.authData = undefined;
        this.user = undefined;
    }

    return authFactory;
}]);

helloApp.controller('AuthController',['$scope', '$log', '$route', '$location', 'authService', 'authFactory','AuthDialogFactory',
    function($scope, $log, $route, $location, authService,  authFactory, authDialogFactory) {
        $scope.auth = {};

        $scope.modalInstance = authDialogFactory;//service.getAuth();

        $scope.modalInstance.result.then(function(data){
            authService.login(data).$promise.
                then(function(retvalue){
                    authFactory.setAuthData(retvalue);
                }).
                catch(function(response){
                    $log.info("Modal dismissed at" + new Date());
                });

            $location.path('/');
        },function(){
            $log.info("Modal dismissed at" + new Date());
        });


    }]);



