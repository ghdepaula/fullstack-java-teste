(function () {
	'use strict';

	angular.module('nf_app').controller('impostosController', impostosController);

	impostosController.$inject = ['clienteService','impostosService', 'notaFiscalService', '$scope', '$filter'];

	function impostosController(clienteService, impostosService, notaFiscalService, $scope, $filter) {
		
		$scope.dadosImpostos;
		$scope.impostos = []
		$scope.clientes = [];
		
		$scope.calcularImpostos = function(dadosImpostos){
			impostosService.calcular(dadosImpostos).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}
		
		$scope.findClientes = function(){
			findClientes();
		}
		
		$scope.findByCodCliente = function(codCliente){
			if(codCliente){
				findByCodCliente(codCliente);
			}
		}
		
		function findClientes() {
			clienteService.findAll().success(function(result){
				$scope.clientes = result;
			});
		}
		
		function findByCodCliente(codCliente){
			impostosService.findByCodCliente(codCliente).success(function(result){
				$scope.impostos = result;
			});
		}
		
		$scope.onChangeMonthYear = function(periodo){
		}
		
		$scope.onSelectCliente = function(codCliente){
			if(codCliente){
				findByCodCliente(codCliente);
			}
		}
		
		$scope.cancel = function () {
			clearData();
		}
		
		function clearData() {
			
			var codCliente = $scope.dadosImpostos.codCliente;
			resetDadosImpostos();
			
			findByCodCliente(codCliente);
		}
		
		function resetDadosImpostos() {
			$scope.dadosImpostos.yearMonth = null;
			$('#dtBaseImposto').val('');
		}
		
		function showError(data) {
			alert("Infelizmente ocorreu um erro. Verifique seus dados e tente novamente mais tarde");
		}
		
	}
})();