/**
 * Created by xavier on 21/07/15.
 */

helloApp.factory('materialService', function($resource, material){
    var myfactory = {};

    var selected = {};

    var createMaterial = $resource('resources/material/', {}, {'create': {method: 'PUT'}});
    var updateMaterial = $resource('resources/material/', {}, {'update': {method: 'POST'}});
    var listCategories = $resource('resources/category/', {}, {'query': {method: 'GET', isArray: true}});


    myfactory.categories = function(){
        return listCategories.query({}, function(data){
            return data;
        });
    }

    myfactory.setSelected = function(id_param) {
        selected = material.get({id: id_param});
    }

    myfactory.getSelected = function() {
        return selected;
    }

    myfactory.create = function(dto){
        createMaterial.create(dto);
    }

    myfactory.update = function(dto){
        updateMaterial.update(dto);
    }

    return myfactory;
});


helloApp.controller('CreateMaterialController',['$scope', '$route', '$location', 'material', 'materialService',
    function($scope, $route, $location, material, service) {
        $scope.material = new material();
        $scope.id = 1;
        $scope.buttonCaption = 'Crear nuevo material';

        $scope.categories = service.categories();


        $scope.saveAction = function () {

            $scope.material.$save();
            $location.path('/');
        }
    }]);

helloApp.controller('ListMaterialController',['$scope', '$route', '$location', 'material', 'materialService',
    function($scope, $route, $location, material, Service) {
        $scope.material = {};

        $scope.material.list = material.query();

        $scope.showEdit = function(id_param){
            Service.setSelected(id_param);
            $location.path('/editmaterial');
        }

        $scope.delete = function (id_param) {
            material.delete({id: id_param});
            $route.reload();
        }


        $scope.alta = function(){
            $location.path('/creatematerial');
        }

    }]);

helloApp.controller('EditMaterialController',['$scope', '$route', '$location', 'material', 'materialService',
    function($scope, $route, $location, material, Service) {
        $scope.material = Service.getSelected();
        $scope.id = 1;

        $scope.buttonCaption = 'Guardar material';
        $scope.categories = Service.categories();

        $scope.saveAction = function () {

            $scope.material.$update();
            $location.path('/');
        }
    }]);


