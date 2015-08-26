/**
 * Created by xavier on 21/07/15.
 */


helloApp.factory('CategoryService', function($resource, Category){
    var myfactory = {};
    var selected = null;


    myfactory.setSelected = function(id_category) {
        selected = Category.get({id: id_category});
    }

    myfactory.getSelected = function(){
        return selected;
    }

    return myfactory;
});


helloApp.controller('CreateCategoryController',['$scope', '$route', '$location', 'Category',
    function($scope, $route, $location, Category) {
        $scope.category = new Category();
        $scope.id = 1;
        $scope.buttonCaption = 'Crear nueva categoria';

        $scope.saveAction = function () {
            $scope.category.$save();
            $location.path('/');
        }
    }]);

helloApp.controller('ListCategoryController',['$scope', '$route', '$location', 'Category', 'CategoryService',
    function($scope, $route, $location, Category, CategoryService) {
        $scope.category = {};

        $scope.category.list = Category.query();

        $scope.showEdit = function(id_category){
            CategoryService.setSelected(id_category);
            $location.path('/editCategory');
        }

        $scope.delete = function (id_param) {
            Category.delete({id: id_param});
            $route.reload();
        }


        $scope.alta = function(){
            $location.path('/createCategory');
        }


    }]);

helloApp.controller('EditCategoryController',['$scope', '$route', '$location', 'Category', 'CategoryService',
    function($scope, $route, $location, Category, Service) {
        $scope.category = Service.getSelected();
        $scope.id = 1;
        $scope.buttonCaption = 'Guardar categoria';

        $scope.saveAction = function () {
            $scope.category.$update();
            $location.path('/');
        }
    }]);
