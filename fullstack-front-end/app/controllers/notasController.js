(function () {
	'use strict';

	angular.module('contabilizeiApp').controller('notasController', notasController);

	notasController.$inject = ['clienteService','anexoService', 'notaFiscalService', 'alertsService', 'cfpLoadingBar', '$scope', '$filter', '$timeout', '$window'];

	function notasController(clienteService, anexoService, notaFiscalService, alertsService, cfpLoadingBar, $scope, $filter, $timeout, $window) {
		
		//Var initialize
		$scope.notas = [];
		$scope.nota = {};
		$scope.clientes = [];
		$scope.anexos = [];
		$scope.dtFiltroNotas = null; 
		$scope.hideAdd = false;
		$scope.showAlert = false;
		$scope.clienteDisabled = true;

		
		//Data Load
		$scope.findByCodCliente = function(codCliente) {
			findByCodCliente(codCliente);
		}
		
		$scope.findClientes = function(){
			findClientes();
		}
		
		$scope.findAnexos = function(){
			findAnexos();
		}
		
		function findByCodCliente(codCliente) {
			if(codCliente){
				notaFiscalService.findByCodCliente(codCliente).success(function(result){
					$scope.notas = result;
				});
			}
		}
		
		function findClientes() {
			
			var msg;
			
			clienteService.findAll().success(function(result){
				
				if(result.length > 0){
					$scope.clientes = result;
				}else{
					msg = alertsService.alertInfo("Nenhum cliente cadastrado, realize o cadastro de um cliente para lançar suas notas fiscais.");
					showAlert(msg);
				}

			}).error(function(){
				msg = alertsService.alertError("Ocorreu um errro ! Não foi possível pesquisar os clientes", status);
				showAlert(msg);
			});
		}
		
		function findAnexos(){
			anexoService.findAll().success(function(result){
				$scope.anexos = result;
			});
		}
		
		//Form Events
		$scope.loadForm = function (idNotaFiscal) {
			
			cfpLoadingBar.start();
			
			notaFiscalService.findById(idNotaFiscal).success(function (data) {
				$scope.nota = data;
				$scope.hideAdd = true;
				
				if(data.clienteDTO.anexos.length > 0){
					$scope.anexos = data.clienteDTO.anexos; 
				}else{
					findAnexos();
				}
			});
			
			cfpLoadingBar.complete();
		}
		
		$scope.onSelectCliente = function(codCliente){
			
			cfpLoadingBar.start();
			
			if(codCliente){
				
				$scope.dtFiltroNotas = null;

				clienteService.findById(codCliente).success(function (data) {
					if(data.anexos.length > 0){
						$scope.anexos = data.anexos;
					}else{
						findAnexos();
					}
				});
				
				findByCodCliente(codCliente);
			}
			
			cfpLoadingBar.complete();
		}
		
		$scope.onChangeDtFiltroNotas = function(codCliente){
			
			cfpLoadingBar.start();
			
			var mesAno = $scope.dtFiltroNotas;
			
			if(codCliente && mesAno){
				notaFiscalService.findByCodClienteMes(codCliente, mesAno).success(function (result) {
					$scope.notas = result;
				});
			}else if(codCliente && !mesAno){
				findByCodCliente(codCliente);
			}else{
				$scope.notas = [];
			}
			
			cfpLoadingBar.complete();
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
			
			$scope.nota.idNotaFiscal = null;
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
			
			cfpLoadingBar.start();
			
			var msg;
			
			notaFiscalService.insert(nota).success(function (data) {
				
				msg = alertsService.alertSuccess("Nota fiscal cadastrada com sucesso !");
				showAlert(msg);
				clearData();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao atualizar a nota fiscal", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}

		$scope.update = function (nota) {
			
			cfpLoadingBar.start();
			
			var msg;
			
			notaFiscalService.update(nota).success(function (data) {
				
				msg = alertsService.alertSuccess("Nota fiscal atualizada com sucesso !");
				showAlert(msg);
				clearData();
				
			}).error(function (error, status) {
				msg = alertsService.alertError("Ocorreu um problema ao atualizar a nota fiscal", status);
				showAlert(msg);
			});
			
			cfpLoadingBar.complete();
		}

		function showAlert(alertMessage) {
			
			$scope.alertMessage = {};
			$scope.alertMessage = alertMessage;
			$scope.showAlert = true;
			
			if(alertMessage.typeMessage === 'SUCCESS'){
				$window.scrollTo(0, 0);
				$timeout(function(){
					removeAlert();
				}, 5000);
			}
		}
		
		function removeAlert(){
			$scope.showAlert = false;
			$scope.alertMessage = null;
		}
		
	    $scope.start = function() {
	       cfpLoadingBar.start();
	    };

	    $scope.complete = function () {
	       cfpLoadingBar.complete();
	    };

	    $scope.start();
	    $scope.loadIntro = true;
	    $timeout(function() {
		    $scope.complete();
		    $scope.loadIntro = false;
	    }, 1250);
		
	}

})();