/**
 * Created by xavier on 21/07/15.
 */


helloApp.factory('ExcelResource', function($resource) {
    return $resource('resources/report/',{}, {
        'query': {
            method: 'GET',
            responseType: 'arraybuffer',
            transformResponse: function(data, header, status, config) {
                return {content : data };
        }}});

});



helloApp.controller('ExcelController',['$scope', '$route', '$location', 'ExcelResource',
    function($scope, $route, $location, ExcelResource) {
        $scope.report = {};

        ExcelResource.query().$promise.
            then(function(retvalue){
                var a = document.createElement('a');
                a.style = 'display : none';
                var objectUrl = window.URL.createObjectURL(new Blob([retvalue.content], {type : 'application/vnd.oasis.opendocument.spreadsheet'}));
                a.href = objectUrl;
                a.download = 'intrastat.xls';
                a.click();
                window.URL.revokeObjectURL(objectUrl);
                //window.open(objectUrl);
        }).
            catch(function(response){

            });

        $scope.download = function() {
            ExcelResource.query().$promise.
                then(function (data) {
                    var blob = new Blob([data], {
                        type: "text/plain"
                        //type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                    });
                    var objectUrl = URL.createObjectURL(blob);
                    window.open(objectUrl);
                }).
                catch(function (data, status, headers, config) {
                    //upload failed
                });
        }

    }]);

