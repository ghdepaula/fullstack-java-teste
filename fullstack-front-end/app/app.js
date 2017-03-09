var app = angular.module('nf_app', ['ngRoute']);

app.directive('ngMask', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {	
			
			var options = {};
			var mask = attrs.ngMask;
			
			if(attrs.ngMaskReverse){
				options.reverse = attrs.ngMaskReverse; 	
			}
			
			element.mask(mask, options);
		}
	};
});

app.directive('ngDatePicker', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			
			var dateFormat = attrs.ngDatePicker;
			
			if(!dateFormat){
				dateFormat = "dd/mm/yyyy";
			}
			
			var options = {
				format: dateFormat,
			    language: "pt-BR",
			    autoclose: true,
			    todayHighlight: true
			};
			
			element.datepicker(options);
		}
	};
});

app.directive('ngMonthYearPicker', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			
			var options = {
				format: "MM/yyyy",
			    startView: 1,
			    minViewMode: 1,
			    maxViewMode: 1,
			    language: "pt-BR",
			    autoclose: true
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
	    templateUrl : 'views/nota-fiscal/calculo-impostos.html',
	    controller  : 'impostosController'
	})
	
	$routeProvider.otherwise({redirectTo: '/'});
	
});