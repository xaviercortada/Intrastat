/**
 * Created by xavier on 21/07/15.
 */

helloApp.directive('ngElementReady', ['$timeout',function ($timeout) {
    return {
        priority: -1000, // a low number so this directive loads after all other directives have loaded.
        restrict: "EA", // attribute only
        link: {
            post: function ($scope, $element, $attributes) {
                $element.myfunc();

                if ($scope.$last) {
                    $timeout(function () {
                        $(".my-option").hide();

                        $(".my-selected").mypointer();
                        $(".my-pointer").mypointer();

                    }, 0);


                }
                // do what you want here.
            }

        }
    };
}]);

helloApp.directive('myOption', function factory() {
    return {
        restrict: 'E',
        templateUrl: 'my-option.html'
    };
});


helloApp.factory('ItemService', function($resource, Item){
    var myfactory = {};

    var selected = {};

    var createItem = $resource('resources/item/', {}, {'create': {method: 'PUT'}});
    var updateItem = $resource('resources/item/', {}, {'update': {method: 'POST'}});
    var listProveedores = $resource('resources/proveedores/',{}, {'query': {method: 'GET', isArray: true}});
    var listCategories = $resource('resources/category/', {}, {'query': {method: 'GET', isArray: true}});
    var listPaises = $resource('resources/pais/', {}, {'query': {method: 'GET', isArray: true}});
    var listTransportes = $resource('resources/transporte/', {}, {'query': {method: 'GET', isArray: true}});


    myfactory.proveedores = function(){
        return listProveedores.query({}, function(data){
            return data;
        });
    }

    myfactory.categories = function(){
        return listCategories.query({}, function(data){
            return data;
        });
    }

    myfactory.paises = function(){
        return listPaises.query({}, function(data){
            return data;
        });
    }

    myfactory.transportes = function(){
        return listTransportes.query({}, function(data){
            return data;
        });
    }

    myfactory.setSelected = function(id_param) {
        selected = Item.get({id: id_param});
    }

    myfactory.getSelected = function() {
        return selected;
    }

    myfactory.create = function(dto){
        createItem.create(dto);
    }

    myfactory.update = function(dto){
        updateItem.update(dto);
    }

    return myfactory;
});


helloApp.controller('CreateItemController',['$scope', '$route', '$location', 'Item', 'ItemService',
    function($scope, $route, $location, Item, service) {
        $scope.item = new Item();
        $scope.id = 1;
        $scope.buttonCaption = 'Crear nuevo item';

        $scope.proveedores = service.proveedores();
        $scope.categories = service.categories();
        $scope.paises = service.paises();
        $scope.transportes = service.transportes();


        $scope.saveAction = function () {
            $scope.item.pais = JSON.parse($("#pais").val());

            $scope.item.$save();
            $location.path('/');
        }
    }]);

helloApp.controller('ListItemController',['$scope', '$route', '$location', 'Item', 'ItemService',
    function($scope, $route, $location, Item, Service) {
        $scope.item = {};

        $scope.item.list = Item.query();

        $scope.showEdit = function(id_param){
            Service.setSelected(id_param);
            $location.path('/editItem');
        }

        $scope.delete = function (id_param) {
            Item.delete({id: id_param});
            $route.reload();
        }


        $scope.alta = function(){
            $location.path('/createItem');
        }

    }]);

helloApp.controller('EditItemController',['$scope', '$route', '$location', 'Item', 'ItemService',
    function($scope, $route, $location, Item, Service) {
        $scope.item = Service.getSelected();
        $scope.id = 1;

        $scope.buttonCaption = 'Guardar item';
        $scope.proveedores = Service.proveedores();
        $scope.categories = Service.categories();
        $scope.paises = Service.paises();
        $scope.transportes = Service.transportes();

        $scope.saveAction = function () {
            $scope.item.pais = JSON.parse($("#pais").val());

            $scope.item.$update();
            $location.path('/');
        }
    }]);


