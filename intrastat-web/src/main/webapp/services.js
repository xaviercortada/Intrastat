/**
 * Created by xavier on 9/07/15.
 */

'use strict';

/* Services */

helloApp.factory('Account', function($resource){
    return $resource('resources/account/finder/:username',
        {
            username: '@username'
        },
        {
            update: { method: 'PUT' }
        }
    );
});


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


helloApp.factory('Factura', function($resource) {
    return $resource('resources/factura/:id', {}, {
        update: { method: 'PUT' }
    })
});


helloApp.factory('Material', function($resource) {
    return $resource('resources/material/:idFactura/:idMaterial', {
            idFactura: '@idFactura',
            idMaterial: '@idMaterial'
        },
        {
            update: {method: 'PUT'}
        }
    );
});
