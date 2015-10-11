/**
 * Created by xavier on 21/07/15.
 */


helloApp.factory('CompanyService', function($resource, Company){
    var myfactory = {};
    var selected = null;


    myfactory.setSelected = function(id_company) {
        selected = Company.get({id: id_company});
    }

    myfactory.getSelected = function(){
        return selected;
    }

    return myfactory;
});


helloApp.controller('CreateCompanyController',['$scope', '$route', '$location', 'Company',
    function($scope, $route, $location, Company) {
        $scope.company = new Company();
        $scope.id = 1;
        $scope.buttonCaption = 'Crear nueva empresa';

        $scope.saveAction = function () {
            $scope.company.$save();
            $location.path('/');
        }
    }]);

helloApp.controller('ListCompanyController',['$scope', '$route', '$location', 'Company', 'CompanyService',
    function($scope, $route, $location, Company, CompanyService) {
        $scope.category = {};

        $scope.category.list = Company.query();

        $scope.showEdit = function(id_company){
            CategoryService.setSelected(id_company);
            $location.path('/editCategory');
        }

        $scope.delete = function (id_param) {
            Company.delete({id: id_param});
            $route.reload();
        }


        $scope.alta = function(){
            $location.path('/createCompany');
        }


    }]);

helloApp.controller('EditCompanyController',['$scope', '$route', '$location', 'Company', 'CompanyService',
    function($scope, $route, $location, Company, Service) {
        $scope.company = Service.getSelected();
        $scope.id = 1;
        $scope.buttonCaption = 'Guardar empresa';

        $scope.saveAction = function () {
            $scope.company.$update();
            $location.path('/');
        }
    }]);
