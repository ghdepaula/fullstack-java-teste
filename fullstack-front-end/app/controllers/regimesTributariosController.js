(function () {
	'use strict';

	angular.module('nf_app').controller('regimesTributariosController', regimesTributariosController);

	regimesTributariosController.$inject = ['regimesTributariosService', '$scope', '$filter'];

	function regimesTributariosController(regimesTributariosService , $scope, $filter) {
		
		$scope.regimesTributarios = [];
		$scope.regimeTributario;
		
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