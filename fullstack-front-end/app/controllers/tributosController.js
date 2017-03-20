(function () {
	'use strict';

	angular.module('nf_app').controller('tributosController', tributosController);

	tributosController.$inject = ['tributosService', '$scope', '$filter'];

	function tributosController(tributosService , $scope, $filter) {
		
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