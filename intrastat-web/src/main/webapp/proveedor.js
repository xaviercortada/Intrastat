/**
 * Created by xavier on 8/07/15.
 */

helloApp.factory('ProveedorService',['Proveedor', function(Proveedor){
    var myfactory = {};
    var selected = null;


    myfactory.setSelected = function(id_proveedor){
        selected = Proveedor.get({id : id_proveedor});  //esA.find({id :id_proveedor}, function(data){

    }

    myfactory.getSelected = function(){
        return selected;
    }

    return myfactory;
}]);


helloApp.controller('ListController',['$scope', '$route', '$location', 'Proveedor','ProveedorService',
    function($scope, $route, $location, Proveedor, ProveedorService){
        $scope.proveedores = {};

        $scope.proveedores.list = Proveedor.query();

        $scope.showEdit = function(id_proverdor){
            ProveedorService.setSelected(id_proverdor);
            $location.path('/editProveedor');
        }

        $scope.deleteProveedor = function (id_param) {
            Proveedor.delete({id: id_param});
            $route.reload();
        }

        $scope.alta = function(){
            $location.path('/createProveedor');
        }

    }]);

helloApp.controller('CreateController',['$scope', '$route', '$location', 'Proveedor',
    function($scope, $route, $location, Proveedor) {
        $scope.proveedor = new Proveedor() ;
        $scope.id = 1;
        $scope.buttonCaption = 'Crear nuevo proveedor';

        $scope.saveAction = function () {
            $scope.proveedor.$save();
            $location.path('/');
        }
    }]);

helloApp.controller('EditController',['$scope', '$route', '$location', 'ProveedorService',
    function($scope, $route, $location, ProveedorService) {
        $scope.proveedor = ProveedorService.getSelected();
        $scope.id = 1;
        $scope.buttonCaption = 'Guardar proveedor';

        $scope.saveAction = function () {
            $scope.proveedor.$update();
            $location.path('/');
        }
    }]);

