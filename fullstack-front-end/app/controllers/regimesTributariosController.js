(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('regimesTributariosController', regimesTributariosController);

	regimesTributariosController.$inject = ['regimesTributariosService', 'tributosService', 'cfpLoadingBar', '$scope', '$filter', '$timeout', '$window'];

	function regimesTributariosController(regimesTributariosService, tributosService, cfpLoadingBar, $scope, $filter, $timeout, $window) {
		
		$scope.regimesTributarios = [];
		
		$scope.findAll = function(){
			findAll();
		}
		
		function findAll(){
			regimesTributariosService.findAll().success(function(result){
				$scope.regimesTributarios = result;
			});
		}
		
	    $scope.start = function() {
	    	cfpLoadingBar.start();
		};

		$scope.complete = function () {
		    cfpLoadingBar.complete();
		};


	    // fake the initial load so first time users can see the bar right away:
	    $scope.start();
	    $scope.loadIntro = true;
	    $timeout(function() {
		    $scope.complete();
		    $scope.loadIntro = false;
	    }, 1250);
		
	}
})();