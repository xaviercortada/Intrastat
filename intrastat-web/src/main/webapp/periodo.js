/**
 * Created by xavier on 25/07/15.
 */

helloApp.factory('PeriodoFactory', function($rootScope, $resource, $modal, $route, $location, userFactory) {
    var resource = $resource('resources/periodo/:id', {id:'@id'});

    var perFactory = {
        periodo : null
    };

    perFactory.setPeriodo = function(month, year){

        resource.get({month : month, year : year}).$promise.
            then(function(data) {
                perFactory.periodo = data;
                $rootScope.$broadcast('periodoChanged');

            }).
            catch(function(response){
                //$log.info("Modal dismissed at" + new Date());
            });

    };



    perFactory.getPeriodo = function(){
        if(this.periodo == undefined){
            $modal.open({
                templateUrl: 'modal.html',
                controller: 'DialogController',
                resolve: {
                    aTitle: function () {
                        return 'Alta de materiales';
                    },
                    aText: function () {
                        return 'Debe seleccionar un periodo';
                    }
                }
            });
            $location.path('/');
        }
        return this.periodo;
    };

    perFactory.getRawPeriodo = function() {
        return this.periodo;
    };

    return perFactory;

    });

helloApp.controller('PeriodoController',['$rootScope','$scope','$route', '$location', 'PeriodoFactory',
    function($rootScope, $scope, $route, $location, service) {
        $scope.perfactory = service;
        $scope.periodo = $scope.perfactory.getRawPeriodo();
        $scope.buttonCaption = 'Seleccionar periodo';

        $scope.saveAction = function () {
            $scope.perfactory.setPeriodo($scope.periodo.month, $scope.periodo.year);
            $location.path('/');
        };
    }]);

