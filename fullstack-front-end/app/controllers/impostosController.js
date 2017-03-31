(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('impostosController', impostosController);

	impostosController.$inject = ['clienteService','impostosService', 'notaFiscalService', 'alertsService', 'cfpLoadingBar', '$scope', '$filter', '$timeout', '$window'];

	function impostosController(clienteService, impostosService, notaFiscalService, alertsService, cfpLoadingBar, $scope, $filter, $timeout, $window) {
		
		$scope.dadosImpostos;
		$scope.imposto;
		$scope.impostos = [];
		$scope.clientes = [];
		$scope.dtFilterImpostos = null;
		$scope.showModalConfirm = false;
		$scope.showModalCancel = false;
		$scope.showCalcular = false;
		$scope.showAlert = false;
		$scope.loadIntro = true;
		
	    $scope.startLoading = function() {
	    	cfpLoadingBar.start();
		};

		$scope.completeLoading = function () {
		    cfpLoadingBar.complete();
		};

	    //Intro loading bar
	    $scope.startLoading();
	    $timeout(function() {
		    $scope.completeLoading();
		    $scope.loadIntro = false;
	    }, 1250);
		
	    //Search operations
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
			}).error(function(error, status){
				
			});
		}
		
		//Insert/Update operations
		$scope.calculateImpostos = function(dadosImpostos){
			
			cfpLoadingBar.start();
			
			var msg;
			
			impostosService.calcular(dadosImpostos).success(function (data) {
				
				msg = alertsService.alertSuccess("Cáculo dos tributos mensais realizado com sucesso !");
				showAlert(msg);
				clearDadosImpostos();
				removeAlert();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao realizar o cáculo dos tributos mensais", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}
		
		$scope.confirmar = function (imposto) {
			
			cfpLoadingBar.start();
			
			var msg;
			imposto.statusPagamento = true;
			
			impostosService.update(imposto).success(function (data) {
				$scope.showModalConfirm = false;
				msg = alertsService.alertSuccess("Pagamento do imposto confirmado !");
				showAlert(msg);
				clearImposto();
				removeAlert();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao confirmar o pagamento", status);
				showAlert(msg);
				removeAlert();
			});
			
			cfpLoadingBar.complete();
		}
		
		$scope.cancelar = function (imposto) {
			
			cfpLoadingBar.start();
			
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
			
			cfpLoadingBar.complete();
		}
		
		/*==========================================================================================*/
		/*==========================================================================================*/
		/*	FORM EVENTS AND VALIDATIONS
		/*==========================================================================================*/
		/*==========================================================================================*/
		$scope.onSelectCliente = function(codCliente){
			
			cfpLoadingBar.start();
			
			$scope.dtFilterImpostos = null;
			
			if(codCliente){
				findByCodCliente(codCliente);
				$scope.showCalcular = true;
			}else{
				$scope.impostos = [];
				$scope.imposto = null;
				$scope.showCalcular = false;
			}
			
			cfpLoadingBar.complete();
		}
		
		$scope.onChangeDtFilterImpostos = function(codCliente){
			
			cfpLoadingBar.start();
			
			var mesAno = $scope.dtFilterImpostos;
			
			if(codCliente && mesAno){
				impostosService.findByCodClienteMes(codCliente, mesAno).success(function (result) {
					$scope.impostos = result;
				});
			}else if(codCliente && !mesAno){
				findByCodCliente(codCliente);
			}else{
				$scope.impostos = [];
			}
			
			cfpLoadingBar.complete();
		}
		
		$scope.validateImpostosMes = function(){
			
			var msg;
			var codCli = $scope.dadosImpostos.codCliente;
			var periodo = $scope.dadosImpostos.yearMonth;
			
			if(codCli && periodo){
				impostosService.findByCodClienteMes(codCli, periodo).success(function (result) {
					if(result.length > 1){
						msg = alertsService.alertWarning("Impostos referentes a esse mês já foram calculados.");
						showAlert(msg);
						$scope.calculaImpostoForm.dtBaseImposto.$setValidity("dtBaseImposto", false);
					}else{
						removeAlert();
						$scope.calculaImpostoForm.dtBaseImposto.$setValidity("dtBaseImposto", true);
					}
				});
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
					removeAlert();
				}, 5000);
			}
		}
		
		function removeAlert(){
			$scope.showAlert = false;
			$scope.alertMessage = null;
		}
	}
})();