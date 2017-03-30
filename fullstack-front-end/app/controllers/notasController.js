(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('notasController', notasController);

	notasController.$inject = ['clienteService','anexoService', 'notaFiscalService', 'alertsService', '$scope', '$filter', '$timeout', '$window'];

	function notasController(clienteService, anexoService, notaFiscalService, alertsService, $scope, $filter, $timeout, $window) {
		
		//Var initialize
		$scope.notas = [];
		$scope.nota;
		$scope.clientes = [];
		$scope.anexos = [];
		$scope.hideAdd = false;
		$scope.showAlert = false;
		$scope.clienteDisabled = true;

		
		//Data Load
		$scope.findAll = function () {
			findAll();	
		}
		
		$scope.findClientes = function(){
			findClientes();
		}
		
		$scope.findAnexos = function(){
			findAnexos();
		}
		
		function findAll() {
			notaFiscalService.findAll().success(function(result){
				$scope.notas = result;
			});
		}
		
		function findClientes() {
			clienteService.findAll().success(function(result){
				$scope.clientes = result;
			});
		}
		
		function findAnexos(){
			anexoService.findAll().success(function(result){
				$scope.anexos = result;
			});
		}
		
		//Form Events
		$scope.loadForm = function (numNotaFiscal) {
			notaFiscalService.findById(numNotaFiscal).success(function (data) {
				$scope.nota = data;
				$scope.hideAdd = true;
				
				if(data.clienteDTO.anexos.length > 0){
					$scope.anexos = data.clienteDTO.anexos; 
				}else{
					findAnexos();
				}
			});
		}
		
		$scope.onSelectCliente = function(codCliente){
			if(codCliente){
				clienteService.findById(codCliente).success(function (data) {
					if(data.anexos.length > 0){
						$scope.anexos = data.anexos;
					}else{
						findAnexos();
					}
				});
			}
		}
		
		$scope.validateNumNota = function(numNotaFiscal){
			
			var msg;
			
			if(numNotaFiscal){
				notaFiscalService.findById(numNotaFiscal).success(function (data) {
					if(data.numNotaFiscal){
						$scope.notaFiscalForm.numNotaFiscal.$setValidity("numNotaFiscal", false);
						msg = alertsService.alertWarning("Número da nota fiscal já cadastrado", status);
						showAlert(msg);
					}else{
						$scope.notaFiscalForm.numNotaFiscal.$setValidity("numNotaFiscal", true);
						hideAlert();
					}
				});
			}
		}
		
		function clearData() {
			var codCliente = $scope.nota.codCliente;
			resetNotaFiscal();
			findAll();
			$scope.hideAdd = false;
		}
		
		$scope.cancel = function () {
			clearData();
		}

		function resetNotaFiscal() {
			
			$scope.nota.numNotaFiscal = null;
			$scope.nota.descricaoNotaFiscal = null;
			$scope.nota.codAnexo = null;
			$scope.nota.valorNotaFiscal = null;
			$scope.nota.dataEmissao = null;
			
			$('#numNotaFiscal').val('');
			$('#dtEmissaoNota').val('')
			$('#anexoNotaFiscal').val('');
			$('#descrNotaFiscal').val('');
			$('#vlrNotaFiscal').val('');
		}

		//Form operations
		$scope.insert = function (nota) {
			
			var msg;
			
			notaFiscalService.insert(nota).success(function (data) {
				
				msg = alertsService.alertSuccess("Nota fiscal cadastrada com sucesso !");
				showAlert(msg);
				clearData();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao atualizar a nota fiscal", status);
				showAlert(msg);
			});
		}

		$scope.update = function (nota) {
			
			var msg;
			
			notaFiscalService.update(nota).success(function (data) {
				
				msg = alertsService.alertSuccess("Nota fiscal atualizada com sucesso !");
				showAlert(msg);
				clearData();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao atualizar a nota fiscal", status);
				showAlert(msg);
			});
		}

		function showAlert(alertMessage) {
			
			$scope.alertMessage = {};
			$scope.alertMessage = alertMessage;
			$scope.showAlert = true;
			
			if(alertMessage.typeMessage === 'SUCCESS'){
				$window.scrollTo(0, 0);
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