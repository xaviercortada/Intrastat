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


helloApp.factory('facturaService', function($resource, Factura){
    var myfactory = {
        id : undefined,
        selected : undefined
    };

    var selected = {};

    var createFactura = $resource('resources/factura/', {}, {'create': {method: 'PUT'}});
    var updateFactura = $resource('resources/factura/', {}, {'update': {method: 'POST'}});
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
        this.selected = Factura.get({id: id_param});
/*
            .$promise.then(function(data){
                //selected = data;
                data.fecha = new Date(data.fecha);
                return data;
            });
*/
    }

    myfactory.getSelected = function() {
        return this.selected;
    }

    myfactory.create = function(dto){
        createFactura.create(dto);
    }

    myfactory.update = function(dto){
        updateFactura.update(dto);
    }

    return myfactory;
});


helloApp.controller('CreateFacturaController',['$scope', '$route', '$location', 'Factura', 'facturaService',
    function($scope, $route, $location, Factura, service) {
        $scope.factura = new Factura();
        $scope.factura.materiales = new Array();
        $scope.id = 1;
        $scope.buttonCaption = 'Crear nuevo factura';

        $scope.proveedores = service.proveedores();
        $scope.categories = service.categories();
        $scope.paises = service.paises();
        $scope.transportes = service.transportes();


        $scope.saveAction = function () {
            $scope.factura.pais = JSON.parse($("#pais").val());

            $scope.factura.$save();
            $location.path('/');
        }

        $scope.addMaterial = function () {
            $scope.factura.materiales.push({
                id: undefined,
                importe: 0,
                peso: 0,
                unidades: 0
            });
        }

    }]);

helloApp.controller('ListFacturaController',['$scope', '$route', '$location', 'Factura', 'facturaService',
    function($scope, $route, $location, factura, Service) {
        $scope.factura = {};

        $scope.factura.list = factura.query();

        $scope.showEdit = function(id_param){
            Service.setSelected(id_param);
            $location.path('/editFactura');
        }

        $scope.delete = function (id_param) {
            factura.delete({id: id_param});
            $route.reload();
        }


        $scope.alta = function(){
            $location.path('/createFactura');
        }

    }]);

helloApp.controller('EditFacturaController',['$scope', '$route', '$location', 'Factura', 'Material','facturaService',
    function($scope, $route, $location, factura, material, Service) {

        $scope.factura = Service.getSelected();
        $scope.id = 1;

        $scope.buttonCaption = 'Guardar factura';
        $scope.proveedores = Service.proveedores();
        $scope.categories = Service.categories();
        $scope.paises = Service.paises();
        $scope.transportes = Service.transportes();

        $scope.saveAction = function () {
            $scope.factura.pais = JSON.parse($("#pais").val());

            $scope.factura.$update();
            $location.path('/');
        }

        $scope.addMaterial = function () {
            $scope.factura.materiales.push({
                id: undefined,
                importe: 0,
                peso: 0,
                unidades: 0
            });
        }

        $scope.deleteMaterial = function(index){
            if($scope.factura.materiales[index].id != undefined){
                material.delete(
                    {
                        idFactura: $scope.factura.id,
                        idMaterial: $scope.factura.materiales[index].id}
                    );
            }
            $scope.factura.materiales.splice(index,1);
        }
    }]);


