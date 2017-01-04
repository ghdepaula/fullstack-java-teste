var app = angular.module('nf_app', ['ngRoute']);

app.directive('ngMask', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {	
			element.mask(attrs.ngMask, {});
		}
	};
});

app.config(function($routeProvider) {
	  
	$routeProvider.when('/clientes', {
	    templateUrl : 'views/clientes/clientes.html',
	    controller  : 'clientesController'
	})
	
	$routeProvider.when('/nota-fiscal', {
	    templateUrl : 'views/nota-fiscal/nota-fiscal.html',
	    controller  : 'clientesController'
	})
	
	$routeProvider.when('/calculo-imposto', {
	    templateUrl : 'views/nota-fiscal/calculo-imposto.html',
	})
	
	
	$routeProvider.otherwise({redirectTo: '/'});
	
});