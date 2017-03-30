(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('impostosController', impostosController);

	impostosController.$inject = ['clienteService','impostosService', 'notaFiscalService', 'alertsService', '$scope', '$filter', '$timeout', '$window'];

	function impostosController(clienteService, impostosService, notaFiscalService, alertsService, $scope, $filter, $timeout, $window) {
		
		$scope.dadosImpostos;
		$scope.imposto;
		$scope.impostos = []
		$scope.clientes = [];
		$scope.showModalConfirm = false;
		$scope.showModalCancel = false;
		$scope.showCalcular = false;
		$scope.showAlert = false;
		
		$scope.calculateImpostos = function(dadosImpostos){
			
			var msg;
			
			impostosService.calcular(dadosImpostos).success(function (data) {
				
				msg = alertsService.alertSuccess("Cáculo dos tributos mensais realizado com sucesso !");
				showAlert(msg);
				clearDadosImpostos();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao realizar o cáculo dos tributos mensais", status);
				showAlert(msg);
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
			
			var msg;
			imposto.statusPagamento = true;
			
			impostosService.update(imposto).success(function (data) {
				$scope.showModalConfirm = false;
				msg = alertsService.alertSuccess("Pagamento do imposto confirmado !");
				showAlert(msg);
				clearImposto();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao confirmar o pagamento", status);
				showAlert(msg);
			});
		}
		
		$scope.cancelar = function (imposto) {
			
			var msg;
			imposto.statusPagamento = false;
			
			impostosService.update(imposto).success(function (data) {
				$scope.showModalCancel = false;
				msg = alertsService.alertSuccess("Pagamento do imposto cancelado !");
				showAlert(msg);
				clearImposto();
			})
			.error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao cancelar o pagamento", status);
				showAlert(msg);
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
				if(result.length < 1){
					
				}
			}).error(function(error, status){
				
			});
		}
		
		$scope.onCheckStatusPagamento = function(statusPagamento){
			if(statusPagamento){
				$scope.showModalConfirm = true;
			}
		}
		
		$scope.validateImpostosMes = function(){
			
			var msg = {};
			var codCli = $scope.dadosImpostos.codCliente;
			var periodo = $scope.dadosImpostos.yearMonth;
			
			if(codCli && periodo){
				impostosService.findByCodClienteMes(codCli, periodo).success(function (result) {
					if(result.length > 1){
						console.log('ERRO IMPOSTO');
						msg = alertsService.alertWarning("Impostos referentes a esse mês já foram calculados.");
						showAlert(msg);
						$scope.calculaImpostoForm.dtBaseImposto.$setValidity("dtBaseImposto", false);
					}else{
						console.log('OK IMPOSTO');
						hideAlert();
						$scope.calculaImpostoForm.dtBaseImposto.$setValidity("dtBaseImposto", true);
					}
				});
				
				notaFiscalService.findByCodClienteMes(codCli, periodo).success(function (result) {
					if(result.length < 1){
						console.log('ERRO NOTAS');
						msg = alertsService.alertInfo("Nenhuma nota cadastrada no mês informado.");
						showAlert(msg);
						$scope.calculaImpostoForm.dtBaseImposto.$setValidity("dtBaseImposto", false);
					}else{
						console.log('OK NOTAS');
						hideAlert();
						$scope.calculaImpostoForm.dtBaseImposto.$setValidity("dtBaseImposto", true);
					}
				});
			}
		}
		
		$scope.onSelectCliente = function(codCliente){
			if(codCliente){
				findByCodCliente(codCliente);
				$scope.showCalcular = true;
			}else{
				$scope.impostos = [];
				$scope.imposto = null;
				$scope.showCalcular = false;
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
		
		function showAlert(alertMessage) {
			
			$scope.alertMessage = {};
			$scope.alertMessage = alertMessage;
			$scope.showAlert = true;
			$window.scrollTo(0, 0);
			
			if(alertMessage.typeMessage === 'SUCCESS'){
				$timeout(function(){
					hideAlert();
				}, 5000);
			}
		}
		
		function hideAlert(){
			$scope.showAlert = false;
			$scope.alertMessage = null;
		}
	}
})();