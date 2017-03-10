(function () {
	'use strict';

	angular.module('nf_app').controller('impostosController', impostosController);

	impostosController.$inject = ['clienteService','impostosService', 'notaFiscalService', '$scope', '$filter'];

	function impostosController(clienteService, impostosService, notaFiscalService, $scope, $filter) {
		
		$scope.dadosImpostos;
		$scope.imposto;
		$scope.impostos = []
		$scope.clientes = [];
		$scope.showModalConfirm = false;
		$scope.showModalCancel = false;
		
		$scope.calcularImpostos = function(dadosImpostos){
			impostosService.calcular(dadosImpostos).success(function (data) {
				clearDadosImpostos();
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
		
		$scope.confirmar = function (imposto) {
			
			imposto.statusPagamento = true;
			
			impostosService.update(imposto).success(function (data) {
				$scope.showModalConfirm = false;
				clearImposto();
			})
			.error(function (data) {
				showError(data);
			});
		}
		
		$scope.cancelar = function (imposto) {
			
			imposto.statusPagamento = false;
			
			impostosService.update(imposto).success(function (data) {
				$scope.showModalCancel = false;
				clearImposto();
			})
			.error(function (data) {
				showError(data);
			});
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
		
		$scope.onCheckStatusPagamento = function(statusPagamento){
			if(statusPagamento){
				$scope.showModalConfirm = true;
			}
		}
		
		$scope.onSelectCliente = function(codCliente){
			if(codCliente){
				findByCodCliente(codCliente);
			}
		}
		
		$scope.loadModal = function(idImposto){
			impostosService.findById(idImposto).success(function (data) {
				$scope.imposto = data;
				
				if(!data.statusPagamento){
					$scope.showModalConfirm = true;
				}else{
					$scope.showModalCancel = true;
				}
			}).error(function (data) {
				showError(data);
			});
		}
		
		$scope.cancel = function () {
			clearDadosImpostos();
		}
		
		function clearImposto(){
			
			var codCliente = $scope.imposto.codCliente;
			findByCodCliente(codCliente);
			
			console.log($scope.impostos)

			$scope.imposto = null;
		}
		
		function clearDadosImpostos() {
			
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