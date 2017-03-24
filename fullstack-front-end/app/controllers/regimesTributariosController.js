(function () {
	'use strict';

	angular.module('nf_app').controller('regimesTributariosController', regimesTributariosController);

	regimesTributariosController.$inject = ['regimesTributariosService', 'tributosService', '$scope', '$filter', '$timeout', '$window'];

	function regimesTributariosController(regimesTributariosService, tributosService, $scope, $filter, $timeout, $window) {
		
		$scope.regimesTributarios = [];
		
		$scope.findAll = function(){
			findAll();
		}
		
		function findAll(){
			regimesTributariosService.findAll().success(function(result){
				$scope.regimesTributarios = result;
			});
		}
		
	}
})();