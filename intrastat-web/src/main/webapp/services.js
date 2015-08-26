/**
 * Created by xavier on 9/07/15.
 */

'use strict';

/* Services */


helloApp.factory('Proveedor', function($resource){
        return $resource('resources/proveedores/:id', {}, {
            update: { method: 'PUT' }
        });
    });


helloApp.factory('Category', function($resource) {
    return $resource('resources/category/:id', {}, {
        update: { method: 'PUT' }
    })
});


helloApp.factory('Item', function($resource) {
    return $resource('resources/item/:id', {}, {
        update: { method: 'PUT' }
    })
});
