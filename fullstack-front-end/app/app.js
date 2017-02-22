var app = angular.module('nf_app', ['ngRoute']);

app.directive('ngMask', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {	
			
			var options = {};
			
			if(attrs.ngMaskReverse){
				options.reverse = attrs.ngMaskReverse; 	
			}
			
			element.mask(attrs.ngMask, options);
		}
	};
});

app.directive('ngDatePicker', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {	
			
			var options = {
				format: "dd/mm/yyyy",
			    language: "pt-BR",
			    todayHighlight: true
			};
			
			element.datepicker(options);
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
	    controller  : 'notasController'
	})
	
	$routeProvider.when('/calculo-imposto', {
	    templateUrl : 'views/nota-fiscal/calculo-imposto.html',
	})
	
	
	$routeProvider.otherwise({redirectTo: '/'});
	
});