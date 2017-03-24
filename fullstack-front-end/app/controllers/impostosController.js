(function () {
	'use strict';

	angular.module('nf_app').controller('impostosController', impostosController);

	impostosController.$inject = ['clienteService','impostosService', 'notaFiscalService', '$scope', '$filter', '$timeout', '$window'];

	function impostosController(clienteService, impostosService, notaFiscalService, $scope, $filter, $timeout, $window) {
		
		$scope.dadosImpostos;
		$scope.imposto;
		$scope.impostos = []
		$scope.clientes = [];
		$scope.showModalConfirm = false;
		$scope.showModalCancel = false;
		$scope.showAlert = false;
		
		$scope.calculateImpostos = function(dadosImpostos){
			impostosService.calcular(dadosImpostos).success(function (data) {
				clearDadosImpostos();
				
				console.log(data);
				
				showAlert(status);
			})
			.error(function (error, status) {
				showAlert(status);
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
				showAlert(status);
			})
			.error(function (error, status) {
				showAlert(status);
			});
		}
		
		$scope.cancelar = function (imposto) {
			
			imposto.statusPagamento = false;
			
			impostosService.update(imposto).success(function (data) {
				$scope.showModalCancel = false;
				clearImposto();
			})
			.error(function (error, status) {
				showAlert(status);
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
			}).error(function (error, status) {
				showAlert(status);
			});
		}
		
		$scope.cancel = function () {
			clearDadosImpostos();
		}
		
		function clearImposto(){
			var codCliente = $scope.imposto.codCliente;
			findByCodCliente(codCliente);
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
		
		function showAlert(status) {
			
			var alertMessage = {};
			
			if(status === 404){
				alertMessage.status = status;
				alertMessage.typeAlert = 'alert-danger';
				alertMessage.typeMessage = 'ERRO:'
				alertMessage.message = 'Serviço indisponível no momento, tente novamente !.'
			}else if (status === 500){
				alertMessage.status = status;
				alertMessage.typeAlert = 'alert-danger';
				alertMessage.typeMessage = 'ERRO:'
				alertMessage.message = 'Ocorreu um erro ao realizar essa operação, tente novamente !.'
			}else{
				alertMessage.typeAlert = 'alert-success';
				alertMessage.typeMessage = 'SUCESSO:'
				alertMessage.message = 'Operação realizada com sucesso !.'
			}
			
			$scope.alertMessage = alertMessage;
			$scope.showAlert = true;
			$window.scrollTo(0, 0);
			
			$timeout(function(){
				$scope.showAlert = false;
				$scope.alertMessage = null;
			}, 5000);
			
		}
		
	}
})();