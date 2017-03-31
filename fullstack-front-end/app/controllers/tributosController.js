(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('tributosController', tributosController);

	tributosController.$inject = ['tributosService', 'cfpLoadingBar', '$scope', '$filter', '$timeout', '$window'];

	function tributosController(tributosService, cfpLoadingBar, $scope, $filter, $timeout, $window) {
		
		$scope.tributos = [];
		$scope.tributo;
		
		$scope.findAll = function(){
			findAll();
		}
		
		function findAll(){
			tributosService.findAll().success(function(result){
				$scope.tributos = result;
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