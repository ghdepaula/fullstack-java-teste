(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('tributosController', tributosController);

	tributosController.$inject = ['tributosService', '$scope', '$filter', '$timeout', '$window'];

	function tributosController(tributosService , $scope, $filter, $timeout, $window) {
		
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
		
	}
})();