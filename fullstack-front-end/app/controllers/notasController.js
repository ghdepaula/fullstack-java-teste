(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('notasController', notasController);

	notasController.$inject = ['clienteService','anexoService', 'notaFiscalService', '$scope', '$filter', '$timeout', '$window'];

	function notasController(clienteService, anexoService, notaFiscalService, $scope, $filter, $timeout, $window) {
		
		//Var initialize
		$scope.notas = [];
		$scope.nota;
		$scope.clientes = [];
		$scope.anexos = [];
		$scope.hideAdd = false;
		$scope.showAlert = false;
		$scope.clienteDisabled = true;

		
		//Data Load
		$scope.findByCodCliente = function (codCliente) {
			if(codCliente){
				findByCodCliente(codCliente);	
			}
		}
		
		$scope.findClientes = function(){
			findClientes();
		}
		
		$scope.findAnexos = function(){
			findAnexos();
		}
		
		function findByCodCliente(codCliente) {
			notaFiscalService.findByCodCliente(codCliente).success(function(result){
				(result);
				$scope.notas = result;
			});
		}
		
		function findClientes() {
			clienteService.findAll().success(function(result){
				(result);
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
				
				findByCodCliente(codCliente);
				
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
			if(numNotaFiscal){
				notaFiscalService.findById(numNotaFiscal).success(function (data) {
					if(data.numNotaFiscal){
						$scope.notaFiscalForm.numNotaFiscal.$setValidity("numNotaFiscal", false);
					}else{
						$scope.notaFiscalForm.numNotaFiscal.$setValidity("numNotaFiscal", true);
					}
				});
			}
		}
		
		function clearData() {
			var codCliente = $scope.nota.codCliente;
			resetNotaFiscal();
			findByCodCliente(codCliente);
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
			notaFiscalService.insert(nota).success(function (data) {
				clearData();
				showAlert(status);
			})
			.error(function (error, status) {
				showAlert(status);
			});
		}

		$scope.update = function (nota) {
			notaFiscalService.update(nota).success(function (data) {
				clearData();
				showAlert(status);
			})
			.error(function (error, status) {
				showAlert(status);
			});
		}

		$scope.remove = function (numNotaFiscal) {
			notaFiscalService.remove(numNotaFiscal).success(function (data) {				
				clearData();
				showAlert(status);
			})
			.error(function (error, status) {
				showAlert(status);
			});
		}
		
		function showAlert(status) {
			
			var alertMessage = {};
			
			if(status === 404){
				alertMessage.status = status;
				alertMessage.alertIcon = 'fa fa-close text-danger';
				alertMessage.typeAlert = 'alert-danger';
				alertMessage.typeMessage = 'ERRO:'
				alertMessage.message = 'Serviço indisponível no momento, tente novamente !.'
			}else if (status === 500){
				alertMessage.status = status;
				alertMessage.alertIcon = 'fa fa-close text-danger';
				alertMessage.typeAlert = 'alert-danger';
				alertMessage.typeMessage = 'ERRO:'
				alertMessage.message = 'Ocorreu um erro ao realizar essa operação, tente novamente !.'
			}else{
				alertMessage.alertIcon = 'fa fa-check text-success'
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